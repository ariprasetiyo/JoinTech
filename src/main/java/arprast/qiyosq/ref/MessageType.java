package arprast.qiyosq.ref;

public enum MessageType {
	
	UPDATE_USER_IS_NULL(1, "UPDATE USER IS NULL"),
	NULL(2, "NULL"),
	PASSWORD_IS_EMPTY(3, "PASSWORD IS EMPTY"),
	PASSWORD_LESS_THAN_EIGHT(3, "PASSWORD LESS THAN 8");

	public final int intValue;
	public final String stringValue;

	/**
	 * 
	 * @param intValue
	 * @param message
	 */
	private MessageType(int intValue, String stringValue) {
		this.intValue = intValue;
		this.stringValue = stringValue;
	}
}
