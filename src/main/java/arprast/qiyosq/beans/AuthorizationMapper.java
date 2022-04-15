package arprast.qiyosq.beans;

import org.springframework.stereotype.Component;
import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.model.AuthorizationModel;

@Component
public class AuthorizationMapper {

	public AuthorizationModel asAuthorizationModel(AuthorizationDto authorizationDto) {
		AuthorizationModel authorizationModel = new AuthorizationModel();
		authorizationModel.setSysMenu(authorizationDto.getId());

		if (authorizationDto.getParentId() != null) {
			authorizationModel.setParent(authorizationDto.getParentId());
		}

		authorizationModel.setSysRoles(authorizationDto.getRoleId());
		authorizationModel.setIsDelete(authorizationDto.isDelete());
		authorizationModel.setIsInsert(authorizationDto.isInsert());
		authorizationModel.setIsUpdate(authorizationDto.isUpdate());
		authorizationModel.setDisabled(authorizationDto.isDisabled());
		authorizationModel.setIsRead(true);

		return authorizationModel;
	}

}
