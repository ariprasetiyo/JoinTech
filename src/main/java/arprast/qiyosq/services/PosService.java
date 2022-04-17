package arprast.qiyosq.services;

import arprast.qiyosq.dao.DaoImpl;
import arprast.qiyosq.dto.*;
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

    public ResponseEntity<ResponseDto> addItemTmp(RequestDto<RequestAddItemTmp> request) {
        if (logger.isDebugEnabled()) {
            logger.debug("getMasterItems {}", request.toString());
        }
        final ResponseDto responseDto = new ResponseDto();
        final ResponseData responseData = new ResponseData();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
