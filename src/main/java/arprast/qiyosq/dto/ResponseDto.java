package arprast.qiyosq.dto;

import arprast.qiyosq.ref.StatusCode;

public class ResponseDto extends Dto {

	private static final long serialVersionUID = -191184234025791265L;

	/*
	 * protected Class<T> clazz;
	 * 
	 * protected JsonMessageDto(Class<T> clazz) { this.clazz = clazz; }
	 * 
	 * public Class<T> getClazz() { return clazz; }
	 */

	private StatusCode statusCode;

	private String message;

	private Object responseData;

	public ResponseDto() {

	}

	/**
	 * 
	 * @param statusCode
	 * @param message
	 * @param responseData
	 */
	public ResponseDto(StatusCode statusCode, String message, Object responseData) {
		this.message = message;
		this.statusCode = statusCode;
		this.responseData = responseData;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
		this.message = statusCode.desc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}



	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
}
