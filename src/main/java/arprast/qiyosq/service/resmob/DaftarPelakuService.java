package arprast.qiyosq.service.resmob;

import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.dto.DaftarPelakuDto;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.model.AuthorizationModel;
import arprast.qiyosq.model.MenusModel;

import java.util.List;

public interface DaftarPelakuService {

	public void viewSysRoles(Long idRoles);

	public List<MenusModel> listMenu();

	public List<AuthorizationModel> listMenuAuthorization(Long idRole);

	public AuthorizationDto getAuthorizationList(int offset, int limit, String keySearch);

	public String getAuthorizationJson(Long idRole);
	
	public List<DaftarPelakuDto> getDaftarPelakuList(RequestData requestData);

/*	public void viewDataMenu(Model model, Long idRole);*/

	public DaftarPelakuDto saveDaftarPelaku(DaftarPelakuDto daftarPelakuDto);

	int updateAuthorization(Long id, AuthorizationDto authorizationDto);

	void deleteAuthorization(Long id);
	
	long countAuthorization(long roleId);

	List<String> getButtonActionAcl(final String username, final String menuName, final String roleName);
}
