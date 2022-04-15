package arprast.qiyosq.handler.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import arprast.qiyosq.beans.Error;
import arprast.qiyosq.ref.StatusType;
import arprast.qiyosq.util.LogUtil;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String REQUEST_DATA_REGEX = "requestData\\.";
	private static final String EMPTY_STRING = "";
	private static final StringWriter st = new StringWriter();

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final static Error handle(MissingServletRequestParameterException exception) {
		exception.printStackTrace(new PrintWriter(st));
		return errors(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), st);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final static Error handle(Exception exception) {
		exception.printStackTrace(new PrintWriter(st));
		return errors(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.BAD_REQUEST.toString(), st);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final static Error handle(ConstraintViolationException exception) {
		exception.printStackTrace(new PrintWriter(st));
		return errors(HttpStatus.BAD_REQUEST, exception.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage).collect(Collectors.toList()), st);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final static Error handle(IllegalStateException exception) {
		exception.printStackTrace(new PrintWriter(st));
		return errors(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), st);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final static Error handle(MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(HttpStatus.BAD_REQUEST, fieldErrors, "MethodArgumentNotValidException");
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final static Error handle(BindException exception) {
		BindingResult result = exception.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(HttpStatus.BAD_REQUEST, fieldErrors, "BindException");
	}

	private final static Error processFieldErrors(HttpStatus httpStatus, List<FieldError> fieldErrors, String message) {
		Error error = new Error(httpStatus.value(), message);
		for (FieldError fieldError : fieldErrors) {
			error.addFieldError(fieldError.getField().replaceAll(REQUEST_DATA_REGEX, EMPTY_STRING).toUpperCase(),
					fieldError.getDefaultMessage());

		}
		LogUtil.logDebugType(logger, true, StatusType.API_REQ_RES_ERROR, error.toString());
		return error;
	}

	private final static Error errors(HttpStatus httpStatus, Object message, Object cause) {
		Error messageErrorMap = new Error(httpStatus.value(), message.toString());
		LogUtil.logDebugType(logger, true, StatusType.API_REQ_RES_GLOBAL_ERROR, cause.toString());
		return messageErrorMap;
	}
}