package arprast.qiyosq.services;

import java.util.ArrayList;
import java.util.List;

import arprast.qiyosq.ref.MessageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import arprast.qiyosq.beans.AuthorizationMapper;
import arprast.qiyosq.dao.AuthorizationDao;
import arprast.qiyosq.dao.AuthorizationDaoImpl;
import arprast.qiyosq.dao.MenusDao;
import arprast.qiyosq.dao.RolesDao;
import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.model.AuthorizationModel;
import arprast.qiyosq.model.MenusModel;
import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.util.LogUtil;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	private static final TypeReference<List<AuthorizationDto>> typeRef = new TypeReference<List<AuthorizationDto>>() {
	};
	private static final ObjectWriter authorizationWriter = jsonMapper.writerFor(typeRef);

	@Autowired
	AuthorizationMapper authorizationMapper;

	@Autowired
	AuthorizationDao authorizationDao;
	
	@Autowired
	AuthorizationDaoImpl authorizationDaoImpl;

	@Autowired
	RolesDao dsSysRoles;

	@Autowired
	MenusDao dsSysMenuDao;

	public int updateAuthorization(Long id, AuthorizationDto authorizationDto) {
		LogUtil.logDebugType(logger, true, ActionType.VIEW, "{}", authorizationDto.toString());
		return authorizationDao.updateAuthorization(id, authorizationDto.isInsert(), authorizationDto.isUpdate(),
				authorizationDto.isDelete(), authorizationDto.isDisabled());
	}

	public void deleteAuthorization(Long id) {
		LogUtil.logDebugType(logger, true, ActionType.DELETE, "Delete menu {}", id);
		authorizationDao.delete(id);
	}

	public void viewSysRoles(Long idRoles) {
		// model.addAttribute("selectRoleValue", idRoles);
		// List<RolesModel> listAllSysRole = (List<RolesModel>)
		// dsSysRoles.findAll();
		// model.addAttribute("listRoles", listAllSysRole);
	}

	public List<MenusModel> listMenu() {
		return (List<MenusModel>) dsSysMenuDao.findAll();
	}

	public List<AuthorizationModel> listMenuAuthorization(Long idRole) {
		return authorizationDao.getForScreenMenu(idRole);
	}

	public AuthorizationDto getAuthorizationList(int offset, int limit, String keySearch) {
		authorizationDao.findAll();
		return null;
	}

	/**
	 * @deprecated
	 */
	public String getAuthorizationJson(Long idRole) {
		RequestData requestData = new RequestData();
		requestData.setId(idRole);
		try {
			return authorizationWriter.writeValueAsString(getAuthorizationList(requestData));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<AuthorizationDto> getAuthorizationList(RequestData requestData) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("{}", requestData.toString());
		}
		
		if (requestData == null || requestData.getId() == null) {
			return null;
		}
		List<AuthorizationModel> sysAuthorizationList = getDataMenu(requestData);
		if (sysAuthorizationList == null) {
			return null;
		}
		List<AuthorizationDto> sysAuthorizationDtoList = new ArrayList<AuthorizationDto>();
		for (AuthorizationModel sysAuthorization : sysAuthorizationList) {
			AuthorizationDto sysAuthorizationDto = new AuthorizationDto();
			sysAuthorizationDto.setId(sysAuthorization.getId());
			sysAuthorizationDto.setMenuName(sysAuthorization.getSysMenu().getMenusName());
			sysAuthorizationDto.setCreatedTime(sysAuthorization.getCreatedTime());
			sysAuthorizationDto.setModifiedTime(sysAuthorization.getModifiedTime());
			sysAuthorizationDto.setInsert(sysAuthorization.isIsInsert());
			sysAuthorizationDto.setUpdate(sysAuthorization.isIsUpdate());
			sysAuthorizationDto.setRead(sysAuthorization.isIsRead());
			sysAuthorizationDto.setDelete(sysAuthorization.isIsDelete());
			sysAuthorizationDto.setDisabled(sysAuthorization.isDisabled());
			sysAuthorizationDtoList.add(sysAuthorizationDto);
		}
		return sysAuthorizationDtoList;
	}


	private List<AuthorizationModel> getDataMenu(RequestData requestData) {
		List<AuthorizationModel> SysAuthorities = (List<AuthorizationModel>) authorizationDaoImpl.listAllAuthorizationMenu(requestData);
		List<AuthorizationModel> SysAuthoritiesNew = new ArrayList<>();

		StringBuilder parentSign = new StringBuilder();
		long idParent = 0;
		int levelMenu = 0;
		for (AuthorizationModel sysAuthority : SysAuthorities) {

			idParent = (sysAuthority.getParent() == null) ? 0 : sysAuthority.getParent().getId();
			levelMenu = recursifMethodCountParentId(sysAuthority.getId());

			/*LogUtil.logDebugType(logger, true, ActionType.VIEW, "result count parent id={}, Level menu={}, Id={}",
					sysAuthority.getId(), levelMenu, idParent);*/

			parentSign.delete(0, parentSign.length());
			for (int a = 0; a < levelMenu; a++) {
				parentSign.append("&nbsp&nbsp&nbsp");
			}

			MenusModel sysMenu = new MenusModel();
			sysMenu.setMenusName(parentSign.toString() + sysAuthority.getSysMenu().getMenusName());
			sysAuthority.setSysMenu(sysMenu);
			SysAuthoritiesNew.add(sysAuthority);
		}
		return SysAuthoritiesNew;
	}

	private int recursifMethodCountParentId(long id) {
		Long a = authorizationDao.getParentId(id);
		if (a == null) {
			return 0;
		}
		return recursifMethodCountParentId(a) + 1;
	}

	public AuthorizationDto saveMenu(AuthorizationDto authorizationDto) {

		LogUtil.logDebugType(logger, true, ActionType.SAVE, "{}", authorizationDto.toString());
		if (authorizationDto.getRoleId() == null) {
			authorizationDto.setMessageStatus(MessageStatus.SAVE_ERROR);
			authorizationDto.setMessage(MessageStatus.NULL_VALUE.stringValue);
			return authorizationDto;
		}

		AuthorizationModel authorizationModel = authorizationMapper.asAuthorizationModel(authorizationDto);
		authorizationModel = authorizationDao.save(authorizationModel);

		if (authorizationModel == null || authorizationModel.getId() == null) {
			authorizationDto.setMessageStatus(MessageStatus.SAVE_ERROR);
			authorizationDto.setMessage(MessageStatus.SAVE_AUTHORIZATION_ERROR.stringValue);
			return authorizationDto;
		}

		authorizationDto.setId(authorizationModel.getId());
		authorizationDto.setMessageStatus(MessageStatus.SAVE_SUCCEED);
		authorizationDto.setMessage(MessageStatus.SAVE_SUCCEED.stringValue);

		return authorizationDto;
	}

	@Override
	public long countAuthorization(long roleId) {
		return authorizationDao.countByRole(roleId);
	}

	@Override
	public List<String> getButtonActionAcl(final String username, final String menuName, final String roleName) {
		return authorizationDaoImpl.buttonAcionACL(username, menuName, roleName);
	}
}
