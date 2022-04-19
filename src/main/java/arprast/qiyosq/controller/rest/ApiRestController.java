package arprast.qiyosq.controller.rest;

import arprast.qiyosq.dto.RequestAddItemTmp;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.http.Request;
import arprast.qiyosq.http.Response;
import arprast.qiyosq.services.InventoryMasterItemService;
import arprast.qiyosq.services.PosService;
import arprast.qiyosq.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public final ResponseEntity<Response> getMasterItems(@RequestBody @Valid final Request<RequestData> masterItemRequest) {
        LogUtil.logRequest(masterItemService.getClass(), masterItemRequest);
        return LogUtil.logResponse(posService.getClass(), masterItemService.getMasterItem(masterItemRequest));
    }

    @RequestMapping(value = "/admin/v1/api/pos/item/add", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public final ResponseEntity<Response> addPosItemTmp(@RequestBody @Valid final Request<RequestAddItemTmp> masterItemRequest) {
        LogUtil.logRequest(posService.getClass(), masterItemRequest);
        final String username = getUserName();
        return LogUtil.logResponse(posService.getClass(), posService.addItemTmp(masterItemRequest));
    }

    private static final String getUserName(){
        final Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        return auth2.getName();
    }


}
