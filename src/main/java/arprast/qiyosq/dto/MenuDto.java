package arprast.qiyosq.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MenuDto extends Dto {

	private static final long serialVersionUID = -5813048583639762690L;

	@NotNull
	@Size(min = 3, max = 50)
	private String menusName;

	private String url;

	public String getMenusName() {
		return menusName;
	}

	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "MenuDto [menusName=" + menusName + ", url=" + url + ", id=" + id + "]";
	}

}
