package arprast.qiyosq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import arprast.qiyosq.service.RolesService;
import arprast.qiyosq.service.UserService;

@Controller
@RequestMapping("/admin/v1/view/main")
public class MainAdminController {

	@Autowired
	RolesService rolesService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, Authentication authentication) {

		model.addAttribute("userInfo", userService.getUser());
		model.addAttribute("selectroles", rolesService.viewSysRoleAll());

		return "/admin/v1/spa/index";
	}
}
