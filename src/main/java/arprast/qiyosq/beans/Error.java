package arprast.qiyosq.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Error {
	private final int status;
	private final String message;
	private List<FieldError> fieldErrors = new ArrayList<>();

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	private Date date;
	private String cause;

	/**
	 * 
	 * @param status
	 * @param message
	 */
	public Error(int status, String message) {
		this.status = status;
		this.message = message;
		this.date = new Date();
	}

	/**
	 * 
	 * @param status
	 * @param message
	 * @param cause
	 */
	public Error(int status, String message, String cause) {
		this.status = status;
		this.message = message;
		this.date = new Date();
		this.cause = cause;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param path
	 * @param message
	 */
	public void addFieldError(String path, String message) {
		FieldError error = new FieldError(path, message, message);
		fieldErrors.add(error);
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Override
	public String toString() {
		return "\nError [status=" + status + ", message=" + message + ", fieldErrors=" + fieldErrors + ", date=" + date
				+ ", cause=" + cause + "]\n";
	}

}
