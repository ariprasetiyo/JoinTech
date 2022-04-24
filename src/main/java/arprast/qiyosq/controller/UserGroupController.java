/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.controller;

import arprast.qiyosq.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/v1/view/usergroup")
public class UserGroupController {
	
	@Autowired
	RolesService rolesService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("selectroles", rolesService.viewSysRoleAll());
		return "/admin/v1/spa/templates/user-group-index";
	}
}
