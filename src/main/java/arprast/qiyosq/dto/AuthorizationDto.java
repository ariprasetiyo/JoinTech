/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dto;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

import java.lang.*;

/**
 *
 * @author ari-prasetiyo
 * @category These attribute fasterXML for set JSON message in class
 *           authorization method authorizationList
 */
@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class AuthorizationDto extends Dto {

	private static final long serialVersionUID = -5525131693784409145L;

	private String roleName;

	private String url;

	private String menuName;

	private Long parentId;

	private boolean isUpdate;

	private boolean isDelete;

	private boolean isInsert;

	private boolean isRead;

	private Long roleId;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isInsert() {
		return isInsert;
	}

	public void setInsert(boolean isInsert) {
		this.isInsert = isInsert;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public AuthorizationDto(String patternDispatcherUrl, String roleName) {
		this.roleName = roleName;
		this.url = patternDispatcherUrl;
	}

	public AuthorizationDto() {
	};

	public AuthorizationDto(Date createTime, Date modifyTime, Long id, String menuName, Long parentId, boolean isUpdate,
			boolean isDelete, boolean isInsert, boolean isRead) {
		this.id = id;
		this.menuName = menuName;
		this.parentId = parentId;
		this.isUpdate = isUpdate;
		this.isDelete = isDelete;
		this.isInsert = isInsert;
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "AuthorizationDto [roleName=" + roleName + ", url=" + url + ", menuName=" + menuName + ", parentId="
				+ parentId + ", isUpdate=" + isUpdate + ", isDelete=" + isDelete + ", isInsert=" + isInsert
				+ ", isRead=" + isRead + ", roleId=" + roleId + ", id=" + id + ", isDisabled()=" + isDisabled() + "]";
	}
}
