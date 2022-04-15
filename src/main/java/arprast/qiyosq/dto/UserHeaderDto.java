/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ari-prasetiyo
 */
public class UserHeaderDto extends Dto {

	private static final long serialVersionUID = -3158852729647788219L;
	private float totalRecord;
	private List<UserDto> listUser = new ArrayList<UserDto>();

	public float getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(float totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<UserDto> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserDto> listUser) {
		this.listUser = listUser;
	}

	@Override
	public String toString() {
		return "UserHeaderDto [totalRecord=" + totalRecord + ", listUser=" + listUser + ", getId()=" + getId() + "]";
	}

}
