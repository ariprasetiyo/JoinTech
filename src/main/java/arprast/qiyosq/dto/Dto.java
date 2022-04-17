/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import arprast.qiyosq.ref.StatusType;

/**
 *
 * @author ari-prasetiyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dto implements Serializable {

	private static final long serialVersionUID = 1690600905679083387L;

	//userId
	protected Long id;
	@NotNull
	protected String requestId;

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private Date createdTime;

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy
	// hh:mm:ss")
	private Date modifiedTime;

	private String version;

	@NotNull
	private boolean disabled;

	private String createdBy;

	private String modifiedBy;

	@JsonIgnore
	private StatusType statusType;

	@JsonIgnore
	private String message;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public StatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
