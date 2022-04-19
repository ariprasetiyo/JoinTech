package arprast.qiyosq.ref;

public enum MessageStatus {

	SAVE_SUCCEED(1, "SAVE SUCCEED"), 
	UPDATE_SUCCEED(2, "UPDATE SUCCEED"), 
	DELETE_SUCCEED(3,"DELETE SUCCEED"), 
	DUPLICATE_DATA_ERROR(501,"DUPLICATE DATA ERROR"), 
	DUPLICATE_EMAIL_ERROR(502,"DUPLICATE EMAIL ERROR"), 
	NULL_POINTER_ERROR(503,"NULL POINTER ERROR"), 
	NOT_SAVED_ERROR(504,"NOT SAVED ERROR"), 
	SAVE_USER_ERROR(505,"SAVE USER ERROR"), 
	SAVE_ROLE_ERROR(506,"SAVE ROLE ERROR"), 
	API_REQ_RES_ERROR(507,"API REQ RES ERROR"), 
	API_REQ_RES_GLOBAL_ERROR(508,"API REQ RES GLOBAL ERROR"), 
	UPDATE_ERROR(509,"UPDATE ERROR"), 
	SAVE_ERROR(510,"SAVE ERROR"), 
	WRONG_OLD_PASSWORD(511,"WRONG OLD PASSWORD"), 
	DELETE_ERROR(512,"DELETE ERROR"), 
	NULL_VALUE(513,"NULL VALUE"), 
	SAVE_AUTHORIZATION_ERROR(514,"SAVE ROLE ERROR"), 
	SAVE_USER_GROUP_ERROR(515,"SAVE USER GROUP ERROR"),
	DUPLICATE_DATA_ON_DB(516,"DUPLICATE DATA ON DB"),

	POS_TMP_NOT_ENOUGH_STOCK(1000, "not enough stock"),

	DATA_NOT_FOUND(517,"DATA NOT FOUND");

	public final int intValue;
	public final String stringValue;

	/**
	 * @param intValue
	 * @param stringValue
	 */
	private MessageStatus(int intValue, String stringValue) {
		this.intValue = intValue;
		this.stringValue = stringValue;
	}

	public static MessageStatus valueOf(int intValue) {
		switch (intValue) {
		case 1:
			return SAVE_SUCCEED;
		case 2:
			return UPDATE_SUCCEED;
		case 3:
			return DELETE_SUCCEED;
		case 501:
			return DUPLICATE_DATA_ERROR;
		case 502:
			return DUPLICATE_EMAIL_ERROR;
		case 503:
			return NULL_POINTER_ERROR;
		case 504:
			return NOT_SAVED_ERROR;
		case 505:
			return SAVE_USER_ERROR;
		case 506:
			return SAVE_ROLE_ERROR;
		case 507:
			return API_REQ_RES_ERROR;
		case 508:
			return API_REQ_RES_GLOBAL_ERROR;
		case 509:
			return UPDATE_ERROR;
		case 510:
			return SAVE_ERROR;
		case 511:
			return WRONG_OLD_PASSWORD;
		case 512:
			return DELETE_ERROR;
		case 513:
			return NULL_VALUE;
		case 514:
			return SAVE_AUTHORIZATION_ERROR;
		case 515:
			return SAVE_USER_GROUP_ERROR;
		case 516:
			return DUPLICATE_DATA_ON_DB;
		case 517 :
			return DATA_NOT_FOUND;
		default:
			return null;
		}
	}

}
