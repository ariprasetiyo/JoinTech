package arprast.qiyosq.dto;

import arprast.qiyosq.ref.MessageStatus;

public class ResponseDto extends Dto {

	private static final long serialVersionUID = -191184234025791265L;

	/*
	 * protected Class<T> clazz;
	 * 
	 * protected JsonMessageDto(Class<T> clazz) { this.clazz = clazz; }
	 * 
	 * public Class<T> getClazz() { return clazz; }
	 */

	private MessageStatus messageStatus;

	private String message;

	private Object responseData;

	public ResponseDto() {

	}

	/**
	 * 
	 * @param messageStatus
	 * @param message
	 * @param responseData
	 */
	public ResponseDto(MessageStatus messageStatus, String message, Object responseData) {
		this.message = message;
		this.messageStatus = messageStatus;
		this.responseData = responseData;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
		this.message = messageStatus.stringValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageStatus getMessageStatus() {
		return messageStatus;
	}



	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
}
