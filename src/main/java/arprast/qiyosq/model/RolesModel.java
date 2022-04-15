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
@Table(name="sys_roles")
public class RolesModel  extends ModelEntity{
    
	private static final long serialVersionUID = 2432434267482377275L;
	
    @Column(name = "role_name", length = 15, nullable = false)
    public String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	@Override
	public String toString() {
		return "RolesModel [roleName=" + roleName + "]";
	}
    
}
