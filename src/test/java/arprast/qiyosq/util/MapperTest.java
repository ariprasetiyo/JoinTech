package arprast.qiyosq.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import arprast.qiyosq.beans.UserMapper;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.model.RolesModel;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.model.UserRolesModel;
import fr.xebia.extras.selma.Selma;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
	
	private static final UserMapper userMapper = Selma.mapper(UserMapper.class);
	private static Logger logger = LoggerFactory.getLogger(MapperTest.class);
	
	@Test
	public void mapperUserTest(){
		UserDto userDto = new UserDto();
		userDto = userMapper.asUserDTO(userModel());
		logger.debug("result : {}", userDto);	
	}
	
	private static UserModel userModel(){
		UserModel userModel = new UserModel();
		userModel.setId(333L);
		userModel.setName("Ari Prasetiyo");
		
		RolesModel rolesModel = new RolesModel();
		rolesModel.setId(123L);
		rolesModel.setRoleName("admin");
		
		RolesModel rolesModel1 = new RolesModel();
		rolesModel1.setId(124L);
		rolesModel1.setRoleName("public");
		
		List<UserRolesModel> userRolesList = new ArrayList<UserRolesModel>();
		UserRolesModel userRolesModel = new UserRolesModel();
		userRolesModel.setId(1L);
		userRolesModel.setSysRoles(rolesModel);
		userRolesList.add(userRolesModel);
		
		userRolesModel = new UserRolesModel();
		userRolesModel.setId(2L);
		userRolesModel.setSysRoles(rolesModel1);
		userRolesList.add(userRolesModel);
		
		userModel.setUserRolesModel(userRolesList);
		
		return userModel;
	}
}
