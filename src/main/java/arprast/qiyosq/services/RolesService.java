package arprast.qiyosq.services;

import java.util.List;

import arprast.qiyosq.model.RolesModel;

public interface RolesService {
	
	public List<RolesModel> viewSysRoleAll();

	public List<RolesModel> getListIdByName(List<String> listAuthorities);
}
