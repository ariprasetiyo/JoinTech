package arprast.qiyosq.dto;

import arprast.qiyosq.ref.StatusType;

public class ResponseDto extends Dto {

	private static final long serialVersionUID = -191184234025791265L;

	/*
	 * protected Class<T> clazz;
	 * 
	 * protected JsonMessageDto(Class<T> clazz) { this.clazz = clazz; }
	 * 
	 * public Class<T> getClazz() { return clazz; }
	 */

	private StatusType statusType;

	private String message;

	private Object responseData;

	public ResponseDto() {

	}

	/**
	 * 
	 * @param statusType
	 * @param message
	 * @param responseData
	 */
	public ResponseDto(StatusType statusType, String message, Object responseData) {
		this.message = message;
		this.statusType = statusType;
		this.responseData = responseData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
}
