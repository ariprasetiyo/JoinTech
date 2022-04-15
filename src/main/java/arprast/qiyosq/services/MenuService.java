package arprast.qiyosq.services;

import java.util.List;

import arprast.qiyosq.dto.MenuDto;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.ResponseData;

public interface MenuService {
	ResponseData listOfMenus(RequestData requestData);

	String getScreenMenu(List<String> listAuthoritiesString);

	MenuDto validateEditMenu(MenuDto menuDto);

	MenuDto validateSaveMenu(MenuDto menuDto);
	
	public boolean validateDeleteMenu(long idMenu);

}
