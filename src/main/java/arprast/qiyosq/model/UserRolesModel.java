/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ari-prasetiyo
 */
@Entity
@Table(name = "sys_user_roles")
public class UserRolesModel extends ModelEntity {

	private static final long serialVersionUID = 2432434267482377275L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "sys_roles_id")
	private RolesModel sysRoles;

	public RolesModel getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(RolesModel sysRoles) {
		this.sysRoles = sysRoles;
	}

	@Override
	public String toString() {
		return "UserRolesModel [sysRoles=" + sysRoles + "]";
	}

}
