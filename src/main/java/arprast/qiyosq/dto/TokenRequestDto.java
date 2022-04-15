package arprast.qiyosq.dto;

public class TokenRequestDto extends Dto {

	private static final long serialVersionUID = -5601885952357369871L;

	private String user;

	private String token;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "TokenRequestDto [user=" + user + ", token=" + token + ", getId()=" + getId() + "]";
	}

}
