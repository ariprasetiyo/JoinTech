package arprast.qiyosq.services;

import arprast.qiyosq.dao.DaoImpl;
import arprast.qiyosq.dto.*;
import arprast.qiyosq.http.POSHeaderTmpRequest;
import arprast.qiyosq.http.Request;
import arprast.qiyosq.http.Response;
import arprast.qiyosq.model.MasterItemModel;
import arprast.qiyosq.ref.MessageStatus;
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

        request.getUsername();

        final MasterItemModel masterItem = daoImpl.getMasterItem(request.getRequestData().getItems().getItemCode());
        if(request.getRequestData().getItems().getQty() > masterItem.getStock()){
            responseDto.setMessageStatus(MessageStatus.POS_TMP_NOT_ENOUGH_STOCK);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
//
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
