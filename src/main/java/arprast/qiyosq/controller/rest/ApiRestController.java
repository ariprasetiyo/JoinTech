package arprast.qiyosq.controller.rest;

import arprast.qiyosq.dto.*;
import arprast.qiyosq.services.InventoryMasterItemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ApiRestController {

    private static final Logger logger = LoggerFactory.getLogger(ApiRestController.class);

    @Autowired
    InventoryMasterItemServiceImpl inventoryMasterItemService;

    @RequestMapping(value = "/admin/v1/api/inventory/master/items", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public final ResponseEntity<ResponseDto> getMasterItems(@RequestBody @Valid final RequestDto<RequestData> masterItemRequest) {
        if(logger.isDebugEnabled()){
            logger.debug("getMasterItems {}", masterItemRequest.toString());
        }
        final List<MasterItemDto> masterItems = inventoryMasterItemService.getMasterItems(masterItemRequest);
        final ResponseDto responseDto = new ResponseDto();
        final ResponseData responseData = new ResponseData();
        responseData.setTotalRecord(masterItems.size());
        responseData.setData(masterItems);
        responseDto.setResponseData(responseData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
