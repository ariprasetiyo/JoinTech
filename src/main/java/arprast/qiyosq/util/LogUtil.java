package arprast.qiyosq.util;

import org.slf4j.Logger;

import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.ref.StatusType;

public class LogUtil {

	private static StringBuilder messages = new StringBuilder();
	private static final String MESSAGE_STATUS_TYPE = "MESSAGE_STATUS_TYPE";
	private static final String ACTION_TYPE = "ActionType";
	private static final String COLON = "=";
	private static final String DIRECTION = " -> ";

	/**
	 * Future for audit trail
	 * 
	 * @param logger
	 * @param isEnabled
	 * @param paramMessageStatus
	 *            : {@value StatusType, ActionType}
	 * @param message
	 * @param values
	 * 
	 */
	public static void logDebugType(Logger logger, boolean isEnabled, Object paramMessageStatus, String message,
			Object... values) {
		try {
			if (paramMessageStatus instanceof StatusType) {
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
