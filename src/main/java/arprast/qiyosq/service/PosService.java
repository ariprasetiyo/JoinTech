package arprast.qiyosq.service;

import arprast.qiyosq.dao.DaoImpl;
import arprast.qiyosq.dto.*;
import arprast.qiyosq.http.POSHeaderTmpRequest;
import arprast.qiyosq.http.Request;
import arprast.qiyosq.http.Response;
import arprast.qiyosq.model.MasterItemModel;
import arprast.qiyosq.model.POSDetailTmpModel;
import arprast.qiyosq.model.POSHeaderTmpModel;
import arprast.qiyosq.ref.ItemType;
import arprast.qiyosq.ref.StatusCode;
import arprast.qiyosq.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        }

        final POSHeaderTmpModel pOSHeader = daoImpl.getPOSHeaderTmp(request.getUsername(), request.getRequestId());
        StatusCode statusCode = StatusCode.FAILED;
        if(pOSHeader == null){
            final int insertResult = insertPOSHeaderTmp(request, masterItem);
            if(insertResult > 0){
                statusCode = (insertPOSItemTmp(request, masterItem) > 0 ? StatusCode.SUCCESS : StatusCode.FAILED );
            }
        }else{
            statusCode = (insertPOSItemTmp(request, masterItem) > 0 ? StatusCode.SUCCESS : StatusCode.FAILED );
        }

        final ResponseData responseData = new ResponseData();
        responseData.setTotalRecord(0);
        responseData.setData("");
        responseDto.setResponseData(responseData);
        responseDto.setStatusCode(statusCode);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
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
        return daoImpl.insertPOSHeaderTmp(headerModel);
    }

    private int insertPOSItemTmp(final Request<POSHeaderTmpRequest> request, final MasterItemModel masterItem) {
        final POSDetailTmpModel POSItemTmp = new POSDetailTmpModel();
        POSItemTmp.setRequestId(request.getRequestId());
        POSItemTmp.setItemCode(request.getRequestData().getItems().getItemCode());
        POSItemTmp.setItemCodeLabel(masterItem.getItemCodeLabel());
        POSItemTmp.setItemName(masterItem.getItemName());
        POSItemTmp.setDescription(masterItem.getDescription());
        POSItemTmp.setQty(request.getRequestData().getItems().getQty());
        POSItemTmp.setSellPrice(masterItem.getSellPrice());
        POSItemTmp.setPriceDetail(masterItem.getPriceDetail());
        POSItemTmp.setBasicPrice(masterItem.getBasicPrice());
        POSItemTmp.setItemType(masterItem.getItemType());
        return daoImpl.insertItemTmpPOS(POSItemTmp);
    }

}
