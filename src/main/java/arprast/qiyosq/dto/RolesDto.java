/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ari-prasetiyo
 */
public class RolesDto extends Dto {

	private static final long serialVersionUID = 7283833745054615419L;

	@Size(min = 3, max = 30)
	@NotNull
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RolesDto [roleName=" + roleName + ", getId()=" + getId() + "]";
	}

}
