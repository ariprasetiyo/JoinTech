package arprast.qiyosq.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import arprast.qiyosq.dao.UserDao;
import arprast.qiyosq.dao.UserDaoImpl;
import arprast.qiyosq.dto.RolesDto;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.model.UserModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	UserService userServiceImpl;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private UserDao userDao;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

	@Test
	public void saveUser() {
		String email = "prasetiyooooo@gmail.com";
		// UserModel userModel = userDao.findUserByEmail(email);
		// userServiceImpl.deleteUser(userModel.getId());

		UserDto userDto = new UserDto();
		userDto.setUsername("Ari Prasetiyoo");
		userDto.setPassword("123456");
		userDto.setEmail(email);
		userDto.setIsActive(true);
		userDto.setNoHp("08564548111");
		// userDto.setRoleName("admin");
		userDto.setName("ddddddddddd");
		// userDto.setId(userModel.getId());

		List<RolesDto> rolesDto = new ArrayList<RolesDto>();
		RolesDto roleDto = new RolesDto();
		roleDto.setId(1L);
		rolesDto.add(roleDto);

		roleDto = new RolesDto();
		roleDto.setId(2L);
		rolesDto.add(roleDto);
		userDto.setRoles(rolesDto);

		userServiceImpl.saveUserAndRole(userDto);
	}

	@Test
	public void editUser() {
		String email = "prasetiyoooo@gmail.com";
		UserModel userModel = userDao.findUserByEmail(email);
		System.out.println(userModel.getId());
		UserDto userDto = new UserDto();
		userDto.setUsername("Ari Praseti");
		userDto.setPassword("123456");
		userDto.setEmail(email);
		userDto.setIsActive(true);
		userDto.setNoHp("08564548222");
		// userDto.setRoleName("admin");
		userDto.setName("bisaaa bisaa");
		userDto.setId(userModel.getId());

		List<RolesDto> rolesDto = new ArrayList<RolesDto>();
		RolesDto roleDto = new RolesDto();
		roleDto.setId(1L);
		rolesDto.add(roleDto);
		userDto.setRoles(rolesDto);
		userServiceImpl.updateUserAndRole(userDto);

		UserModel userModelResult = userDao.findUserByEmail(email);
		Assert.assertNotEquals(userModelResult.getName(), userDto.getName());
	}

	@Test
	public void deleteUserTest() {
		logger.debug("delete={}", userDaoImpl.deleteByUserId(4));
	}

}
