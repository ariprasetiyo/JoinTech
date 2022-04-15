/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.model.UserModel;

/**
 *
 * @author ari-prasetiyo
 * @category {@literal If 1 method there are two or more transactionDao, create
 *           here}
 * 
 */
public class UserDaoImpl {

	public static final int MYSQL_DUPLICATE_PK = 1062;

	@Autowired
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserDao userDao;

	/**
	 * 
	 * @param requestData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserModel> listUser(RequestData requestData) {

		if ((requestData.getSearch() == null || requestData.getSearch().isEmpty()) && requestData.getId() == 0) {
			return em.createQuery("from UserModel order by username asc")
					.setFirstResult(requestData.getOffset())
					.setMaxResults(requestData.getLimit())
					.getResultList();
		} else if ((requestData.getSearch() == null || requestData.getSearch().isEmpty()) && requestData.getId() != 0) {
			return em
					.createQuery("SELECT UM from UserModel UM " 
							+ "LEFT JOIN UM.userRolesModel URM "
							+ "LEFT JOIN URM.sysRoles SR "
							+ "WHERE SR.id = :roleId order by UM.username asc")
					.setParameter("roleId", requestData.getId())
					.setFirstResult(requestData.getOffset())
					.setMaxResults(requestData.getLimit()).getResultList();
		} else if (requestData.getId() == 0) {
			return em.createQuery("from UserModel where username like :searchUserName order by username asc")
					.setParameter("searchUserName", "%" + requestData.getSearch() + "%")
					.setFirstResult(requestData.getOffset())
					.setMaxResults(requestData.getLimit()).getResultList();
		} else {
			return em
					.createQuery("SELECT UM from UserModel UM " 
							+ "LEFT JOIN  UM.userRolesModel URM "
							+ "LEFT JOIN URM.sysRoles SR "
							+ "WHERE SR.id = :roleId AND UM.username like :searchUserName order by UM.username asc")
					.setParameter("searchUserName", "%" + requestData.getSearch() + "%")
					.setParameter("roleId", requestData.getId()).setFirstResult(requestData.getOffset())
					.setMaxResults(requestData.getLimit()).getResultList();
		}		
	}
	
	/**
	 * 
	 * @param requestData
	 * @return
	 */
	public long countUser(RequestData requestData) {		
		if ((requestData.getSearch() == null || requestData.getSearch().isEmpty()) && requestData.getId() == 0) {
			return userDao.count();
		} else if ((requestData.getSearch() == null || requestData.getSearch().isEmpty()) &&  requestData.getId() != 0) {
			return em
					.createQuery("select count(UM.id) from UserModel UM " 
							+ "LEFT JOIN UM.userRolesModel URM "
							+ "LEFT JOIN URM.sysRoles SR "
							+ "where SR.id = :idRole  ")
					.setParameter("idRole", requestData.getId())
					.getFirstResult();
		} else if( requestData.getId() == 0){
			return em
					.createQuery("select count(UM.id) from UserModel UM " 
							+ " WHERE UM.username like :searchUserName ")
					.setParameter("searchUserName", requestData.getId())
					.getFirstResult();
		} else{
			return em
					.createQuery("select count(UM.id) from UserModel UM " 
							+ "LEFT JOIN UM.userRolesModel URM "
							+ "LEFT JOIN URM.sysRoles SR "
							+ "where SR.id = :idRole AND UM.username like :searchUserName ")
					.setParameter("idRole", requestData.getId())
					.setParameter("searchUserName", "%" + requestData.getSearch() + "%")
					.getFirstResult();
		}
	}

	@Modifying
	public int deleteByUserId(long userId) {
		return em.createNativeQuery("delete from sys_user_roles where sys_user_id =:nUserId")
				.setParameter("nUserId", userId).executeUpdate();
	}

	@Transactional(rollbackFor = { Exception.class, Throwable.class, IllegalArgumentException.class }, readOnly = false)
	public UserModel saveEditUserRole(UserModel user, boolean isEdit) {
		TransactionStatus TransactionStatus = TransactionAspectSupport.currentTransactionStatus();

			if (isEdit) {
				deleteByUserId(user.getId());
			}
			user = userDao.save(user);
			return user;

	}

}
