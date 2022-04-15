/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.model.MenusModel;

/**
 *
 * @author ari-prasetiyo
 */
public class MenusDaoImpl {

	@Autowired
	@PersistenceContext
	EntityManager em;

	@Autowired
	MenusDao menuDao;

	@SuppressWarnings("unchecked")
	public List<MenusModel> listOfMenus(RequestData requestData) {
		return em.createQuery("from MenusModel where menusName like :searchUserName order by menusName asc")
				.setParameter("searchUserName", "%" + requestData.getSearch() + "%")
				.setFirstResult(requestData.getOffset()).setMaxResults(requestData.getLimit()).getResultList();
	}

	public long countMenu(RequestData requestData) {
		if (requestData.getSearch() == null || requestData.getSearch().isEmpty()) {
			return menuDao.count();
		} else {
			return em.createQuery("select count(id) from MenusModel WHERE menusName like :searchData ")
					.setParameter("searchData", "%" + requestData.getSearch() + "%").getFirstResult();
		}
	}

/*	public int countMenuByMenuName(String menuName) {
		return em.createQuery("select count(1) from MenusModel WHERE menusName =:nMenuName ")
				.setParameter("nMenuName", menuName).getFirstResult();

	}*/
	
	/*public int countMenuById(Long idMenu) {
		return em.createQuery("select count(id) from MenusModel WHERE id = :menuId ")
				.setParameter("menuId", idMenu).getFirstResult();

	}*/

}
