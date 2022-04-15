package arprast.qiyosq.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import arprast.qiyosq.beans.UserMapper;
import arprast.qiyosq.dao.UserDao;
import arprast.qiyosq.dao.UserDaoImpl;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.ResponseData;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.ref.MessageType;
import arprast.qiyosq.ref.StatusType;
import arprast.qiyosq.util.LogUtil;
import fr.xebia.extras.selma.Selma;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final UserMapper userMapper = Selma.mapper(UserMapper.class);

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private UserDao userDao;

	public UserDto saveUserAndRole(UserDto userDto) {
		LogUtil.logDebugType(logger, true, ActionType.SAVE, "{}", userDto.toString());
		return saveEditUserAndRole(userDto, false, ActionType.SAVE, StatusType.SAVE_ERROR, StatusType.SAVE_SUCCEED);
	}

	public UserDto updateUserAndRole(UserDto userDto) {
		LogUtil.logDebugType(logger, true, ActionType.UPADATE, "{}", userDto.toString());
		if (userDto.getOldPassword() != null && !userDto.getOldPassword().isEmpty()) {
			if (isValidPassword(userDto) == false) {
				userDto = new UserDto();
				userDto.setStatusType(StatusType.UPDATE_ERROR);
				userDto.setMessage(StatusType.WRONG_OLD_PASSWORD.stringValue);
				return userDto;
			}
		} else {
			String password = userDao.findUserPassword(userDto.getId());
			userDto.setPassword(password);
		}
		return saveEditUserAndRole(userDto, true, ActionType.UPADATE, StatusType.UPDATE_ERROR,
				StatusType.UPDATE_SUCCEED);
	}

	private boolean isValidPassword(UserDto userDto) {
		int countUser = userDao.countUserByEmailAndPassword(userDto.getEmail(), userDto.getUsername(),
				userDto.getOldPassword());
		return (countUser >= 1 ? true : false);
	}

	private UserDto saveEditUserAndRole(UserDto userDto, final boolean isEdit, final ActionType actionType,
			final StatusType messageErrorType, final StatusType messageSuccessType) {

		if (userDto.getPassword().isEmpty()) {
			userDto = new UserDto();
			userDto.setStatusType(messageErrorType);
			userDto.setMessage(MessageType.PASSWORD_IS_EMPTY.stringValue);
			return userDto;
		}

		if (userDto.getPassword().length() < 8) {
			userDto = new UserDto();
			userDto.setStatusType(messageErrorType);
			userDto.setMessage(MessageType.PASSWORD_LESS_THAN_EIGHT.stringValue);
			return userDto;
		}

		UserModel user = userMapper.asUserModel(userDto);
		user = userDaoImpl.saveEditUserRole(user, isEdit);
		userDto = userMapper.asUserDTO(user);
		if (user == null) {
			LogUtil.logDebugType(logger, true, messageErrorType, MessageType.NULL.stringValue);
			userDto = new UserDto();
			userDto.setStatusType(messageErrorType);
			userDto.setMessage(MessageType.UPDATE_USER_IS_NULL.stringValue);
			return userDto;
		}

		userDto.setStatusType(messageSuccessType);
		LogUtil.logDebugType(logger, true, messageSuccessType, user.toString());

		return userDto;
	}

	public ResponseData listUser(RequestData requestData) {

		if (logger.isDebugEnabled()) {
			logger.debug("{}", requestData.toString());
		}

		ResponseData responseData = new ResponseData();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(() -> {
			responseData.setTotalRecord(userDaoImpl.countUser(requestData));
		});

		List<UserModel> listSysUser = new ArrayList<UserModel>();
		listSysUser = userDaoImpl.listUser(requestData);
		responseData.setData(userMapper.asUserDTO(listSysUser));

		return responseData;
	}

	@Transactional(rollbackFor = { Exception.class, Throwable.class, IllegalArgumentException.class }, readOnly = false)
	public boolean deleteUser(long idUser) {
		TransactionStatus TransactionStatus = TransactionAspectSupport.currentTransactionStatus();
		userDao.deleteUserRoles(idUser);
		userDao.deleteUser(idUser);
		return TransactionStatus.isCompleted();
	}
	
	public UserDto getUser() {
		UserDto userDto = new UserDto();
		UserModel userModel = userDao.findUserByUserName(getAuthentication().getName());

		if (userModel == null) {
			return userDto;
		}

		return userMapper.asUserDTO(userModel);
	}
	
	private Authentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication;
	}

}
