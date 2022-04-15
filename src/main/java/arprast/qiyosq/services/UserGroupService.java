package arprast.qiyosq.services;

import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.ResponseData;
import arprast.qiyosq.dto.RolesDto;

public interface UserGroupService {

	ResponseData listUserGroup(RequestData requestData);

	ResponseData saveUserGroup(RolesDto rolesDto);

	ResponseData editUserGroup(RolesDto rolesDto);

}
