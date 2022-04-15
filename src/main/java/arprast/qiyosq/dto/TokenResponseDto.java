package arprast.qiyosq.dto;


public class TokenResponseDto extends Dto {

	private static final long serialVersionUID = 3701687484081833307L;

	private String sessionToken;
	private String csrfToken;

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getCsrfToken() {
		return csrfToken;
	}

	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}

}
