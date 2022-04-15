/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ari-prasetiyo
 */
@Entity
@Table(name = "sys_menu")
//@NamedNativeQueries({
//		@NamedNativeQuery(name = "UserRolesModel.deleteByUserId", query = "delete from sys_user_roles where sys_user_id = :nUserId") })
public class MenusModel extends ModelEntity {

	private static final long serialVersionUID = 2432434267482377275L;

	@Column(name = "menus_name")
	private String menusName;

	@Column(nullable = true, length = 100)
	private String url;

	public String getMenusName() {
		return menusName;
	}

	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
