package arprast.qiyosq.controller.rest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import arprast.qiyosq.service.RolesService;

@RestController
@RequestMapping(value = "/admin/v1/api/role")
public class RoleRestController {

	@Autowired
	private RolesService rolesService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Future<ResponseEntity> getListRole() {
		
		/*
		 * CompletableFuture concurrent API
		 */
		return CompletableFuture.supplyAsync(() -> {
			try {
				return new ResponseEntity(rolesService.viewSysRoleAll(), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		});
	}

}
