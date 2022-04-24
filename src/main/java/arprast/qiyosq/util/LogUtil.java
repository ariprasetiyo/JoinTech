package arprast.qiyosq.util;

import arprast.qiyosq.controller.rest.ApiRestController;
import arprast.qiyosq.dto.ResponseDto;
import arprast.qiyosq.http.Response;
import arprast.qiyosq.ref.StatusCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;

import arprast.qiyosq.ref.ActionType;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class LogUtil {

	private static StringBuilder messages = new StringBuilder();
	private static final String MESSAGE_STATUS_TYPE = "MESSAGE_STATUS_TYPE";
	private static final String ACTION_TYPE = "ActionType";
	private static final String COLON = "=";
	private static final String DIRECTION = " -> ";

	private static final Logger logger = LoggerFactory.getLogger(ApiRestController.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	private static final TypeReference<Object> objectRef = new TypeReference<Object>() {
	};
	private static final TypeReference<ResponseEntity<ResponseDto>> responseRef = new TypeReference<ResponseEntity<ResponseDto>>() {
	};
	private static final ObjectWriter requestWritter = jsonMapper.writerFor(objectRef);
	private static final ObjectWriter responseWritter = jsonMapper.writerFor(responseRef);

	public static void logRequest(final Class aClass, final Object request) {
		String jsonRequest = null;
		try {
			jsonRequest = requestWritter.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		logger.info(Util.REQUEST, aClass.getSimpleName(), jsonRequest);
	}

	public static ResponseEntity<Response> logResponse(final Class aClass, final ResponseEntity<Response> response) {
		String jsonResponse = null;
		try {
			jsonResponse = responseWritter.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		logger.info(Util.RESPONSE, aClass.getSimpleName(), jsonResponse);
		return response;
	}

	/**
	 * Future for audit trail
	 * 
	 * @param logger
	 * @param isEnabled
	 * @param paramMessageStatus
	 * @param message
	 * @param values
	 * 
	 */
	public static void logDebugType(Logger logger, boolean isEnabled, Object paramMessageStatus, String message,
			Object... values) {
		try {
			if (paramMessageStatus instanceof StatusCode) {
				if (logger.isDebugEnabled() && isEnabled) {
					messages.append(MESSAGE_STATUS_TYPE);
					messages.append(COLON);
					messages.append(paramMessageStatus.toString());
					messages.append(DIRECTION);
					messages.append(message);
					logger.debug(messages.toString(), values);
				}
			} else if (paramMessageStatus instanceof ActionType) {
				if (logger.isDebugEnabled() && isEnabled && Util.isLogAuditTrail()) {

					if (paramMessageStatus == ActionType.ACCESS_PAGE && !Util.isEnableLoggerAccessPage())
						return;

					messages.append(ACTION_TYPE);
					messages.append(COLON);
					messages.append(paramMessageStatus.toString());
					messages.append(DIRECTION);
					messages.append(message);
					logger.debug(messages.toString(), values);
				}
			} else {
				if (logger.isDebugEnabled() && isEnabled && Util.isEnableCommonLogger()) {
					logger.debug(message, values);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			messages.delete(0, messages.length());
		}
	}

	/**
	 * 
	 * @param logger
	 * @param isEnabled
	 * @param message
	 * @param values
	 * 
	 */
	public static void logDebug(Logger logger, boolean isEnabled, String message, Object... values) {
		if (logger.isDebugEnabled() && isEnabled && values.length > 0) {
			logger.debug(message, values);
		} else {
			logger.debug(message);
		}
	}
}
