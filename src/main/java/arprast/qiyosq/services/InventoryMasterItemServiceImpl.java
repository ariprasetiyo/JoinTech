package arprast.qiyosq.services;

import arprast.qiyosq.dao.DaoImpl;
import arprast.qiyosq.dto.MasterItemDto;
import arprast.qiyosq.model.MasterItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryMasterItemServiceImpl {
    @Autowired
    DaoImpl daoImpl;

    public List<MasterItemDto> getMasterItems() {
        return masterItemDtos(daoImpl.getMasterItems());
    }

    private List<MasterItemDto> masterItemDtos(final List<MasterItemModel> models) {
        final List dtos = new ArrayList<MasterItemDto>();
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
