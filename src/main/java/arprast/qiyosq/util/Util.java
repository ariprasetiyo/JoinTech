package arprast.qiyosq.util;

import arprast.qiyosq.http.Request;
import arprast.qiyosq.http.Response;
import org.apache.commons.lang3.RandomStringUtils;

public class Util {
	
	private static boolean isLogAuditTrail = true;
	private static boolean isEnableCommonLogger = true;
	private static boolean isEnableLoggerAccessPage = false;
	public static final String PERCENTAGE = "%";
	public static final String REQUEST = "class={}, request={}";
	public static final String RESPONSE = "class={}, response={}";
	private static final String PREFIX_CUSTOMER_ID = "cst";
	private static final String DASH = "-";

	public static boolean isLogAuditTrail() {
		return isLogAuditTrail;
	}
	public static void setLogAuditTrail(boolean isLogAuditTrail) {
		Util.isLogAuditTrail = isLogAuditTrail;
	}
	public static boolean isEnableCommonLogger() {
		return isEnableCommonLogger;
	}
	public static void setEnableCommonLogger(boolean isEnableCommonLogger) {
		Util.isEnableCommonLogger = isEnableCommonLogger;
	}
	public static boolean isEnableLoggerAccessPage() {
		return isEnableLoggerAccessPage;
	}
	public static void setEnableLoggerAccessPage(boolean isEnableLoggerAccessPage) {
		Util.isEnableLoggerAccessPage = isEnableLoggerAccessPage;
	}


	public static final Response buildResponse(final Request request){
		final Response responseDto = new Response();
		responseDto.setResponseId(request.getRequestId());
		responseDto.setUsername(request.getUsername());
		responseDto.setId(request.getId());
		return responseDto;
	}

	public static final String generateCustomerId(){
		return new StringBuilder()
				.append(PREFIX_CUSTOMER_ID)
				.append(DASH)
				.append(System.currentTimeMillis())
				.append(DASH)
				.append(RandomStringUtils.randomAlphanumeric(3))
				.toString();
	}
}
