package arprast.qiyosq.controller.rest;

import arprast.qiyosq.dto.RequestAddItemTmp;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.RequestDto;
import arprast.qiyosq.dto.ResponseDto;
import arprast.qiyosq.services.InventoryMasterItemService;
import arprast.qiyosq.services.PosService;
import arprast.qiyosq.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ApiRestController {

    @Autowired
    InventoryMasterItemService masterItemService;

    @Autowired
    PosService posService;

    @RequestMapping(value = "/admin/v1/api/inventory/master/item/list", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public final ResponseEntity<ResponseDto> getMasterItems(@RequestBody @Valid final RequestDto<RequestData> masterItemRequest) {
        LogUtil.logRequest(masterItemService.getClass(), masterItemRequest);
        return LogUtil.logResponse(posService.getClass(), masterItemService.getMasterItem(masterItemRequest));
    }

    @RequestMapping(value = "/admin/v1/api/pos/item/add", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public final ResponseEntity<ResponseDto> addPosItemTmp(@RequestBody @Valid final RequestDto<RequestAddItemTmp> masterItemRequest) {
        LogUtil.logRequest(posService.getClass(), masterItemRequest);
        return LogUtil.logResponse(posService.getClass(), posService.addItemTmp(masterItemRequest));
    }


}
