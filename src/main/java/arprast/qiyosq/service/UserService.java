package arprast.qiyosq.service;

import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.ResponseData;
import arprast.qiyosq.dto.UserDto;

public interface UserService {
	boolean deleteUser(long idUser);

	UserDto saveUserAndRole(UserDto user);

	UserDto updateUserAndRole(UserDto user);

	ResponseData listUser(RequestData requestData);

	UserDto getUser();

}
