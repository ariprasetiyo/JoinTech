package arprast.qiyosq.service;

import arprast.qiyosq.dao.DaoImpl;
import arprast.qiyosq.dto.*;
import arprast.qiyosq.http.Request;
import arprast.qiyosq.http.Response;
import arprast.qiyosq.model.MasterItemModel;
import arprast.qiyosq.util.LogUtil;
import arprast.qiyosq.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryMasterItemService extends LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(InventoryMasterItemService.class);

    @Autowired
    DaoImpl daoImpl;

    public final ResponseEntity<Response> getMasterItem(final Request<RequestData> request) {
        final Response responseDto = Util.buildResponse(request);

        final List<MasterItemDto> masterItems = getMasterItems(request);
        final ResponseData responseData = new ResponseData();
        responseData.setTotalRecord(masterItems.size());
        responseData.setData(masterItems);
        responseDto.setResponseData(responseData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    private final List<MasterItemDto> getMasterItems(final Request<RequestData> request) {
        final String search = request.getRequestData().getSearch();
        final int offset = request.getRequestData().getOffset();
        final int limit = request.getRequestData().getLimit();
        return masterItemDtos(daoImpl.getMasterItems(search, offset, limit));
    }

    private List<MasterItemDto> masterItemDtos(final List<MasterItemModel> models) {
        final List<MasterItemDto> dtos = new ArrayList<>();
        for (MasterItemModel model : models) {
            dtos.add(toMasterItemDto(model));
        }
        return dtos;
    }

    private MasterItemDto toMasterItemDto(final MasterItemModel model) {
        final MasterItemDto dto = new MasterItemDto();
        dto.setItemCode(model.getItemCode());
        dto.setItemCodeLabel(model.getItemCodeLabel());
        dto.setItemName(model.getItemName());
        dto.setItemType(model.getItemType());
        dto.setDescription(model.getDescription());
        dto.setActive(model.isActive());
        dto.setSellPrice(model.getSellPrice());
        dto.setModifiedTime(model.getModifiedTime().getTime());
        return dto;
    }

}
