package arprast.qiyosq.service;

import arprast.qiyosq.model.UserRolesModel;

public interface UserRolesService {
	public UserRolesModel save(UserRolesModel sysUserRoles);
	public int deleteByUserId(long userId);
}
