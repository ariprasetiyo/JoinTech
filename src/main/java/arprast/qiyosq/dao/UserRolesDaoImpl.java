/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ari-prasetiyo
 */
@Repository
public class UserRolesDaoImpl {
	
	@Autowired
	private EntityManager em;
	
	public int deleteByUserId(long userId) {
//		return em.createQuery("delete from UserRolesModel where sysUser.id = :userId ").setParameter("userId", userId)
//				.executeUpdate();
		return 0;
	}

}
