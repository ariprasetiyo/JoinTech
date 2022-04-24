package arprast.qiyosq.service;

import java.util.List;

import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.model.AuthorizationModel;
import arprast.qiyosq.model.MenusModel;

public interface AuthorizationService {

	public void viewSysRoles(Long idRoles);

	public List<MenusModel> listMenu();

	public List<AuthorizationModel> listMenuAuthorization(Long idRole);

	public AuthorizationDto getAuthorizationList(int offset, int limit, String keySearch);

	public String getAuthorizationJson(Long idRole);
	
	public List<AuthorizationDto> getAuthorizationList(RequestData requestData);

/*	public void viewDataMenu(Model model, Long idRole);*/

	public AuthorizationDto saveMenu(AuthorizationDto authorizationDto);

	int updateAuthorization(Long id, AuthorizationDto authorizationDto);

	void deleteAuthorization(Long id);
	
	long countAuthorization(long roleId);

	List<String> getButtonActionAcl(final String username, final String menuName, final String roleName);
}
