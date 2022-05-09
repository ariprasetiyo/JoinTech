package arprast.qiyosq.service;

import arprast.qiyosq.dao.DaoImpl;
import arprast.qiyosq.dto.*;
import arprast.qiyosq.http.*;
import arprast.qiyosq.model.MasterItemModel;
import arprast.qiyosq.model.POSDetailTmpModel;
import arprast.qiyosq.model.POSHeaderTmpModel;
import arprast.qiyosq.model.POSItemTmpModel;
import arprast.qiyosq.ref.ItemType;
import arprast.qiyosq.ref.StatusCode;
import arprast.qiyosq.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosService {

    private static final Logger logger = LoggerFactory.getLogger(PosService.class);

    @Autowired
    DaoImpl daoImpl;

    public ResponseEntity<Response> addItemTmp(final Request<POSHeaderTmpRequest> request) {
        final Response responseDto = Util.buildResponse(request);

        final MasterItemModel masterItem = daoImpl.getMasterItem(request.getRequestData().getItems().getItemCode());
        if(masterItem == null || masterItem.getItemType() != ItemType.SERVICE
                && ( request.getRequestData().getItems().getQty() > masterItem.getStock())){
            responseDto.setStatusCode(StatusCode.POS_TMP_NOT_ENOUGH_STOCK);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }else if(masterItem.getItemType() == ItemType.SERVICE && request.getRequestData().getItems().getQty() > 1){
            responseDto.setStatusCode(StatusCode.POS_TMP_MAX_QTY_ONLY_ONE);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

        final POSHeaderTmpModel pOSHeader = daoImpl.getPOSHeaderTmp(request.getUsername(), request.getRequestId());
        StatusCode statusCode = StatusCode.FAILED;
        if(pOSHeader == null){
            final int insertResult = insertPOSHeaderTmp(request, masterItem);
            if(insertResult > 0){
                statusCode = StatusCode.SUCCESS;
            }
        }else{
            final int insertResult = insertUpdatePOSHeaderTmp(request, masterItem, pOSHeader);
            if(insertResult > 0){
                statusCode = StatusCode.SUCCESS;
            }
        }

        final ResponseData responseData = new ResponseData();
        responseData.setTotalRecord(0);
        responseDto.setResponseData(responseData);
        responseDto.setStatusCode(statusCode);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    public final ResponseEntity<Response> getPosItemList(final Request<RequestData> request) {
        final Response responseDto = Util.buildResponse(request);

        final List<POSItemTmpModel> posItemList = daoImpl.getPosItemList(request);
        final ResponseData responseData = new ResponseData();
        responseData.setTotalRecord(posItemList.size());
        responseData.setData(posItemList);
        responseDto.setResponseData(responseData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    public final ResponseEntity<Response> getPosTemporaryTransaction(final Request request) {
        final Response responseDto = Util.buildResponse(request);

        final POSHeaderTmpModel headerTmpTrx = daoImpl.getPOSHeaderTmp(request.getUsername(), request.getRequestId());
        if(headerTmpTrx == null){
            responseDto.setStatusCode(StatusCode.POS_TMP_HEADER_TRX_NOT_FOUND);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        final List<POSItemTmpModel> posItemList = daoImpl.getPosTemporaryTransactionListByRequestId(request);
        if(posItemList.isEmpty()){
            responseDto.setStatusCode(StatusCode.POS_TMP_TRX_NOT_FOUND);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

        final GetPosTransactionTmpResponse response = buildPosTransactionTmpResponse(headerTmpTrx, posItemList);
        final ResponseData responseData = new ResponseData();
        responseData.setTotalRecord(response.getDetailTrxList().size());
        responseData.setData(response);
        responseDto.setResponseData(responseData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    public final ResponseEntity<Response> getPosHeaderTemporaryTransactionList(final Request<RequestData> request) {
        final Response responseDto = Util.buildResponse(request);

        final List<POSHeaderTmpModel> posItemList = daoImpl.getPosHeaderTemporaryTransactionList(request);
        final ResponseData responseData = new ResponseData();
        responseData.setTotalRecord(posItemList.size());
        responseData.setData(posItemList);
        responseDto.setResponseData(responseData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    private GetPosTransactionTmpResponse buildPosTransactionTmpResponse( final POSHeaderTmpModel headerTmpTrx, final List<POSItemTmpModel> posItemList){
        GetPosTransactionTmpResponse response = new GetPosTransactionTmpResponse();
        response.setHeaderTrx(headerTmpTrx);
        response.setDetailTrxList(posItemList);
        return response;
    }

    private int insertPOSHeaderTmp(final Request<POSHeaderTmpRequest> request, final MasterItemModel masterItem){
        final POSHeaderTmpModel headerModel = new POSHeaderTmpModel();
        headerModel.setRequestId(request.getRequestId());
        headerModel.setUsername(request.getUsername());
        headerModel.setPhoneNumber(request.getRequestData().getPhoneNumber());
        headerModel.setCustomerName(request.getRequestData().getCustomerName());
        headerModel.setPaymentMethod(request.getRequestData().getPaymentMethod());
        headerModel.setAddress(request.getRequestData().getAddress());
        headerModel.setCustomerId(Util.generateCustomerId());
        headerModel.setTotalTrxAmount(masterItem.getSellPrice());
        headerModel.setTotalDiscountAmount(masterItem.getPriceDetail().getDiscountDetail().getDiscountAmount());
        headerModel.setTotalPaidAmount(masterItem.getSellPrice());

        final POSDetailTmpModel detail = new POSDetailTmpModel();
        detail.setRequestId(request.getRequestId());
        detail.setItemCode(request.getRequestData().getItems().getItemCode());
        detail.setItemCodeLabel(masterItem.getItemCodeLabel());
        detail.setItemName(masterItem.getItemName());
        detail.setDescription(masterItem.getDescription());
        detail.setQty(request.getRequestData().getItems().getQty());
        detail.setSellPrice(masterItem.getSellPrice());
        detail.setTotalSellPrice(request.getRequestData().getItems().getQty() * masterItem.getSellPrice() );
        detail.setPriceDetail(masterItem.getPriceDetail());
        detail.setBasicPrice(masterItem.getBasicPrice());
        detail.setItemType(masterItem.getItemType());

        return daoImpl.insertPOSHeaderTmp(headerModel, detail);
    }

    private int insertUpdatePOSHeaderTmp(final Request<POSHeaderTmpRequest> request, final MasterItemModel masterItem, final POSHeaderTmpModel currentHeader){

        final float discountItem = masterItem.getPriceDetail().getDiscountDetail().getDiscountAmount();
        final float totalSellPriceItem = request.getRequestData().getItems().getQty() * masterItem.getSellPrice();

        final float totalDiscount = currentHeader.getTotalDiscountAmount() + discountItem;
        final float totalTrxAmount = currentHeader.getTotalTrxAmount() + totalSellPriceItem;
        final float totalPaid = currentHeader.getTotalPaidAmount() + totalSellPriceItem - totalDiscount;

        final POSHeaderTmpModel headerModel = new POSHeaderTmpModel();
        headerModel.setRequestId(request.getRequestId());
        headerModel.setUsername(request.getUsername());
        headerModel.setTotalTrxAmount(totalTrxAmount);
        headerModel.setTotalDiscountAmount(totalDiscount);
        headerModel.setTotalPaidAmount(totalPaid);

        final POSDetailTmpModel detail = new POSDetailTmpModel();
        detail.setRequestId(request.getRequestId());
        detail.setItemCode(request.getRequestData().getItems().getItemCode());
        detail.setItemCodeLabel(masterItem.getItemCodeLabel());
        detail.setItemName(masterItem.getItemName());
        detail.setDescription(masterItem.getDescription());
        detail.setQty(request.getRequestData().getItems().getQty());
        detail.setSellPrice(masterItem.getSellPrice());
        detail.setTotalSellPrice(request.getRequestData().getItems().getQty() * masterItem.getSellPrice() );
        detail.setPriceDetail(masterItem.getPriceDetail());
        detail.setBasicPrice(masterItem.getBasicPrice());
        detail.setItemType(masterItem.getItemType());

        return daoImpl.insertUpdateHeadItemTmpPOS(headerModel, detail);
    }

}
