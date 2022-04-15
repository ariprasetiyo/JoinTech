/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "sys_user")
public class UserModel extends ModelEntity {

	private static final long serialVersionUID = 2432434267482377275L;

	@Column(length = 50, nullable = false, unique = true, updatable = false)
	@NotBlank
	private String username;

	@Column(length = 50, nullable = false)
	@NotBlank
	private String name;

	@Column(nullable = false, length = 150)
	@NotBlank
	private String password;

	@Column(length = 30, unique = true, updatable = false)
	@Email
	@NotBlank
	private String email;

	@Column(name = "no_hp", length = 13)
	@NotBlank
	private String noHp;

	@Column(name = "is_active")
	private boolean isActive;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(nullable = true, name = "sys_user_id")
	private List<UserRolesModel> userRolesModel;

	public List<UserRolesModel> getUserRolesModel() {
		return userRolesModel;
	}

	public void setUserRolesModel(List<UserRolesModel> userRolesModel) {
		this.userRolesModel = userRolesModel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNoHp() {
		return noHp;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public boolean isIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UserModel [username=" + username + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", noHp=" + noHp + ", isActive=" + isActive + ", userRolesModel=" + userRolesModel + "]";
	}

}
