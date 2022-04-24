package arprast.qiyosq.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import arprast.qiyosq.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestControllerTest {
	
	@Autowired
	UserService userService;
	
	@Test
	public void editUser() {
//		UserDto userDto = new UserDto();
//		userDto.setUsername("Ari Prasetiyo");
//		userDto.setPassword("1234456");
//		userDto.setEmail("prasetiyooo@gmail.com");
//		userDto.setIsActive(true);
//		userDto.setNoHp("085645480401");
//		userDto.setRoleName("admin");
//		userDto.setId(111L);
//		Long[] selectRole = { 1L, 2L };
//		userServiceImpl.updateUserAndRole(userDto, selectRole);
	}
}
