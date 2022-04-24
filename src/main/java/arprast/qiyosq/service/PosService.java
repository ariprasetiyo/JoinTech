package arprast.qiyosq.service;

import arprast.qiyosq.dao.DaoImpl;
import arprast.qiyosq.dto.*;
import arprast.qiyosq.http.POSHeaderTmpRequest;
import arprast.qiyosq.http.Request;
import arprast.qiyosq.http.Response;
import arprast.qiyosq.model.MasterItemModel;
import arprast.qiyosq.model.POSHeaderTmpModel;
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

    public ResponseEntity<Response> addItemTmp(Request<POSHeaderTmpRequest> request) {
        Response responseDto = Util.buildResponse(request);

        final MasterItemModel masterItem = daoImpl.getMasterItem(request.getRequestData().getItems().getItemCode());
        if(request.getRequestData().getItems().getQty() > masterItem.getStock()){
            responseDto.setStatusCode(StatusCode.POS_TMP_NOT_ENOUGH_STOCK);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

        final POSHeaderTmpModel pOSHeader = daoImpl.getPOSHeaderTmp(request.getUsername(), request.getRequestId());
        if(pOSHeader == null){
            final POSHeaderTmpModel headerModel = new POSHeaderTmpModel();
            headerModel.setRequestId(request.getRequestId());
            headerModel.setUsername(request.getRequestId());
            headerModel.setPhoneNumber(request.getRequestData().getPhoneNumber());
            headerModel.setCustomerName(request.getRequestData().getCustomerName());
            headerModel.setPaymentMethod(request.getRequestData().getPaymentMethod());
            headerModel.setAddress(request.getRequestData().getAddress());
            headerModel.setCustomerId(Util.generateCustomerId());
            headerModel.setTotalTrxAmount(masterItem.getSellPrice());
            headerModel.setTotalDiscountAmount(masterItem.getPriceDetail().getDiscountDetail().getDiscountAmount());
            headerModel.setTotalPaidAmount(masterItem.getSellPrice());
            daoImpl.insertPOSHeaderTmp(headerModel);

//            daoImpl.insertItemTmpPOS();
        }

//        POSHeaderTmpModel posHeaderTmp = new POSHeaderTmpModel();
//        posHeaderTmp.setAddress();
//        posHeaderTmp.setCustomerName();
//        posHeaderTmp.setCreatedTime();
//        posHeaderTmp.setPaymentMethod();
//        posHeaderTmp.setRequestId();
//        posHeaderTmp.setPhoneNumber();
//        posHeaderTmp.setTotalTrxAmount();
//        posHeaderTmp.setTotalDiscountAmount();
//        posHeaderTmp.setTotalPaidAmount();
//        posHeaderTmp.setUsername();
//
//        POSDetailTmpModel POSItemTmp = new POSDetailTmpModel();
//        POSItemTmp.setRequestId();
//        POSItemTmp.setItemCode();
//        POSItemTmp.setItemCodeLabel();
//        POSItemTmp.setItemName();
//        POSItemTmp.setDescription();
//        POSItemTmp.setQty();
//        POSItemTmp.setSellPrice();
//        POSItemTmp.setPriceDetail();
//        POSItemTmp.setBasicPrice();
//        POSItemTmp.setItemType();



        final ResponseData responseData = new ResponseData();
        responseData.setTotalRecord(0);
        responseData.setData("");
        responseDto.setResponseData(responseData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    private void POSItemTmpModel() {
    }
}
