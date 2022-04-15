/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.model.AuthorizationModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author ari-prasetiyo
 */

public class AuthorizationDaoImpl {

	@Autowired
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<AuthorizationModel> listAllAuthorizationMenu(RequestData requestData) {
		return em
				.createQuery(
						"from AuthorizationModel am "
						+ "JOIN FETCH am.sysRoles sr "
						+ "JOIN FETCH am.sysMenu sm "
						+ "where sr.id = :nsysRolesId and sm.menusName is not null")
				.setParameter("nsysRolesId", requestData.getId()).setFirstResult(requestData.getOffset())
				.setMaxResults(requestData.getLimit()).getResultList();
	}

	public List<String> buttonAcionACL(final String username, String menuName, String roleName) {
		return em
				.createNativeQuery(queryButtonActionACL())
				.setParameter("nusername", username)
				.setParameter("nmenusName", menuName)
				.setParameter("nrolesName", roleName)
				.getResultList();

	}

	private static final String queryButtonActionACL(){
		return new StringBuilder()
				.append("select srba.button_name from sys_menu sm ")
				.append("inner join sys_authorization sa on sm.id = sa.sys_menu_id ")
				.append("inner join sys_roles sr on sa.sys_roles_id  = sr.id ")
				.append("inner join sys_roles_button_action srba on srba.roles_id = sr.id ")
				.append("inner join sys_user_roles  sur on sr.id = sur.sys_roles_id ")
				.append("inner join sys_user su on su.id = sur.sys_user_id ")
				.append("where su.username =:nusername and  menus_name =:nmenusName and role_name in ( :nrolesName)")
				.toString();
	}


}
