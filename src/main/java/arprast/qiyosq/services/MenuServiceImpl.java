package arprast.qiyosq.services;

import java.util.ArrayList;
import java.util.List;

import arprast.qiyosq.ref.MessageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import arprast.qiyosq.dao.AuthorizationDao;
import arprast.qiyosq.dao.MenusDao;
import arprast.qiyosq.dao.MenusDaoImpl;
import arprast.qiyosq.dto.MenuDto;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.ResponseData;
import arprast.qiyosq.dto.ScreenMenuDto;
import arprast.qiyosq.model.MenusModel;
import arprast.qiyosq.model.RolesModel;

@Service
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	private static final String HTML_0 = "<li class=\"treeview\">\n<a href=\"";
	private static final String HTML_1 = "\">\n<i></i> <span>";
	private static final String HTML_2 = "</span>\n<span class=\"pull-right-container\">\n<i class=\"fa fa-angle-left pull-right\"></i>\n</span>\n</a>\n";
	private static final String HTML_3 = "<ul class=\"treeview-menu\">\n";
	private static final String HTML_4 = "</ul></li>\n";
	private static final String HTML_5 = "<li>\n<a href=\"";
	private static final String HTML_6 = "\"><i class=\"fa fa-circle-o\"></i>";
	private static final String HTML_7 = "\n<span class=\"pull-right-container\">\n<i class=\"fa fa-angle-left pull-right\"></i>\n</span>\n</a>\n<ul class=\"treeview-menu\">\n";
	private static final String HTML_8 = "</a></li>\n";

	@Autowired
	private RolesService rolesService;

	@Autowired
	private MenusDao menusDao;

	@Autowired
	private MenusDaoImpl menusDaoEM;

	@Autowired
	private AuthorizationDao authorizationDao;

	public String getScreenMenu(List<String> listAuthoritiesString) {
		// Looking for id from sys_roles
		List<RolesModel> listId = rolesService.getListIdByName(listAuthoritiesString);
		// convert SysRoles.getId to List Long
		List<Long> listLongId = getListAuthorities(listId);

		StringBuilder tmpScript = new StringBuilder();
		String resultMenus = getScreenMenu(listLongId, 0, tmpScript).toString();
		tmpScript.delete(0, tmpScript.length());
		return resultMenus;
	}

	private StringBuilder getScreenMenu(List<Long> sysRolesId, long parentId, StringBuilder tmpScript) {

		// looking for parent menus with sign parentid have values null/zero
		List<ScreenMenuDto> listMenu = authorizationDao.listScreenMenu(sysRolesId, parentId);

		for (ScreenMenuDto menu : listMenu) {
			if (menu.getParentId() == 0) {
				tmpScript.append(HTML_0);
				tmpScript.append(menu.getPatternDispatcherUrl());
				tmpScript.append(HTML_1);
				tmpScript.append(menu.getMenuName());
				tmpScript.append(HTML_2);
			}

			if (menu.getCounts() > 0 && menu.getParentId() == 0) {
				tmpScript.append(HTML_3);
				getScreenMenu(sysRolesId, menu.getId(), tmpScript);
				tmpScript.append(HTML_4);
			} else if (menu.getCounts() > 0 && menu.getParentId() != 0) {
				tmpScript.append(HTML_5);
				tmpScript.append(menu.getPatternDispatcherUrl());
				tmpScript.append(HTML_6);
				tmpScript.append(menu.getMenuName());
				tmpScript.append(HTML_7);
				getScreenMenu(sysRolesId, menu.getId(), tmpScript);
				tmpScript.append(HTML_4);
			} else if (menu.getCounts() == 0 && menu.getParentId() != 0) {
				tmpScript.append(HTML_5);
				tmpScript.append(menu.getPatternDispatcherUrl());
				tmpScript.append(HTML_6);
				tmpScript.append(menu.getMenuName());
				tmpScript.append(HTML_8);
			}
		}
		return tmpScript;
	}

	public ResponseData listOfMenus(RequestData requestData) {
		ResponseData responseData = new ResponseData();
		List<MenusModel> listMenu = menusDaoEM.listOfMenus(requestData);
		long countMenu = menusDaoEM.countMenu(requestData);
		responseData.setData(listMenu);
		responseData.setTotalRecord(countMenu);
		return responseData;
	}

	/*
	 * public List<MenusModel> listOfMenus(RequestData requestData) { return
	 * menusDaoEM.listOfMenus(requestData); }
	 */

	private List<Long> getListAuthorities(List<RolesModel> listRoles) {
		List<Long> listId = new ArrayList<>();
		for (RolesModel sysRole : listRoles) {
			listId.add(sysRole.getId());
		}
		return listId;
	}

	public MenuDto validateSaveMenu(MenuDto menuDto) {

		logger.debug("Validate save menu {}", menuDto.toString());
		
		MenusModel sysMenus = new MenusModel();
		sysMenus.setMenusName(menuDto.getMenusName());
		sysMenus.setUrl(menuDto.getUrl());
		sysMenus.setDisabled(menuDto.isDisabled());

		int countMenuById = menusDao.countMenuById(menuDto.getId());
		int countMenuByName = menusDao.countMenuByMenuName(menuDto.getMenusName());
		if (countMenuByName > 0 || countMenuById > 0) {
			menuDto.setMessageStatus(MessageStatus.DUPLICATE_DATA_ERROR);
			menuDto.setMessage(MessageStatus.DUPLICATE_DATA_ERROR.stringValue);
			return menuDto;
		}

		return saveMenu(sysMenus);
	}

	public MenuDto validateEditMenu(MenuDto menuDto) {

		logger.debug("Validate edit menu {}", menuDto.toString());
		
		MenusModel sysMenus = new MenusModel();
		sysMenus.setMenusName(menuDto.getMenusName());
		sysMenus.setUrl(menuDto.getUrl());
		sysMenus.setDisabled(menuDto.isDisabled());
		sysMenus.setId(menuDto.getId());

		int countMenuById = menusDao.countMenuById(menuDto.getId());
		if (countMenuById == 0) {
			menuDto.setMessageStatus(MessageStatus.DATA_NOT_FOUND);
			menuDto.setMessage(MessageStatus.DATA_NOT_FOUND.stringValue);
			return menuDto;
		}
		
		return saveMenu(sysMenus);
	}

	private MenuDto saveMenu(MenusModel menuModel) {

		MenuDto menuDto = new MenuDto();
		MenusModel sysMenus = menusDao.save(menuModel);

		if (sysMenus.getId() == null) {
			menuDto.setMessageStatus(MessageStatus.SAVE_ERROR);
			menuDto.setMessage(MessageStatus.SAVE_ERROR.stringValue);
			return menuDto;
		}

		menuDto.setMessageStatus(MessageStatus.SAVE_SUCCEED);
		menuDto.setMessage(MessageStatus.SAVE_SUCCEED.stringValue);
		return menuDto;
	}
	
	@Transactional(rollbackFor = { Exception.class, Throwable.class, IllegalArgumentException.class }, readOnly = false)
	public boolean validateDeleteMenu(long idMenu) {
		TransactionStatus TransactionStatus = TransactionAspectSupport.currentTransactionStatus();
		int countDeleteMenu = menusDao.deleteMenu(idMenu);
		logger.debug("Delete menu id {}={}. Transaction status ={}", idMenu, countDeleteMenu,
				TransactionStatus.isCompleted());
		return (countDeleteMenu > 0 ? true : false);
	}

}
