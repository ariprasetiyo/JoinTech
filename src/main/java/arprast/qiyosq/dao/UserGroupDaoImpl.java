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
import arprast.qiyosq.model.RolesModel;

/**
 *
 * @author ari-prasetiyo
 * @category {@literal If 1 method there are two or more transactionDao, create
 *           here}
 * 
 */
public class UserGroupDaoImpl {

	@Autowired
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserGroupDao userGroupDao;

	/**
	 * 
	 * @param requestData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolesModel> listUserGroup(RequestData requestData) {

		if (requestData.getSearch() == null || requestData.getSearch().isEmpty()) {
			return em.createQuery("from RolesModel order by roleName asc")
					.setFirstResult(requestData.getOffset())
					.setMaxResults(requestData.getLimit())
					.getResultList();
		} else  {
			return em.createQuery("from RolesModel where roleName like :searchData order by roleName asc")
					.setParameter("searchData", "%" + requestData.getSearch() + "%")
					.setFirstResult(requestData.getOffset())
					.setMaxResults(requestData.getLimit()).getResultList();
		} 	
	}
	
	/**
	 * 
	 * @param requestData
	 * @return
	 */
	public long countUserGroup(RequestData requestData) {
		if (requestData.getSearch() == null || requestData.getSearch().isEmpty()) {
			return userGroupDao.count();
		} else {
			return em
					.createQuery("select count(RM.id) from RolesModel RM " 
							+ " WHERE RM.roleName like :searchData ")
					.setParameter("searchData", requestData.getSearch())
					.getFirstResult();
		} 
	}

}
