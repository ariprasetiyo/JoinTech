package arprast.qiyosq.controller.rest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import arprast.qiyosq.dto.TokenRequestDto;
import arprast.qiyosq.dto.TokenResponseDto;

/**
 * 
 * @author ari.prasetiyo
 */
@RestController
@RequestMapping(value = "/admin/v1/security")
public class TokenRequestRestController {

	private static final String JSESSIONID = "JSESSIONID";
	private static final String XSRF_TOKEN = "XSRF-TOKEN";
	private static final String USER = "ariariari";
	private static final String TOKEN = "98253668-476e-47c9-bf7a-05aaad45bf18";

	@RequestMapping(value = "/request-token", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public TokenResponseDto getCsrfToken2(HttpServletRequest request, @RequestBody TokenRequestDto tokenReqDto) {

		System.out.println(tokenReqDto.toString());

		TokenResponseDto tokenResponseDto = new TokenResponseDto();
		if (isValidAuth(tokenReqDto)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(JSESSIONID)) {
					tokenResponseDto.setSessionToken(cookie.getValue());
				} else if (cookie.getName().equals(XSRF_TOKEN)) {
					tokenResponseDto.setCsrfToken(cookie.getValue());
				}
			}
		}
		return tokenResponseDto;
	}

	/**
	 * While hard code
	 * 
	 * @param tokenRequestDto
	 * @return boolean
	 */
	private boolean isValidAuth(TokenRequestDto tokenRequestDto) {
		if (tokenRequestDto == null)
			return false;

		return (tokenRequestDto.getUser().equals(USER) && tokenRequestDto.getToken().equals(TOKEN) ? true : false);
	}
}
