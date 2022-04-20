package arprast.qiyosq.controller.rest;

import arprast.qiyosq.http.POSHeaderTmpRequest;
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
    @SuppressWarnings("unchecked")
    public final ResponseEntity<Response> getMasterItems(@RequestBody @Valid final Request<RequestData> masterItemRequest) {
        return LogUtil.logResponse(masterItemService.getClass(), masterItemService.getMasterItem( initRequest(masterItemService.getClass(), masterItemRequest)));
    }

    @RequestMapping(value = "/admin/v1/api/pos/item/add", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    @SuppressWarnings("unchecked")
    public final ResponseEntity<Response> addPosItemTmp(@RequestBody @Valid final Request<POSHeaderTmpRequest> addItemTmpRequest) {
        return LogUtil.logResponse(posService.getClass(), posService.addItemTmp( initRequest(posService.getClass(), addItemTmpRequest)));
    }

    private static final Request initRequest(final Class aClass,final Request request){
        request.setUsername(getUserName());
        LogUtil.logRequest(aClass, request);
        return request;
    }

    private static final String getUserName(){
        final Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        return auth2.getName();
    }

}
