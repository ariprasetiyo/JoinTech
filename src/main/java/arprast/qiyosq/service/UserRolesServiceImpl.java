package arprast.qiyosq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arprast.qiyosq.dao.UserRolesDao;
import arprast.qiyosq.dao.UserRolesDaoImpl;
import arprast.qiyosq.model.UserRolesModel;
import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.util.LogUtil;

@Service
public class UserRolesServiceImpl implements UserRolesService {

	private static Logger logger = LoggerFactory.getLogger(UserRolesServiceImpl.class);

	@Autowired
	private UserRolesDao userRolesDao;

	@Autowired
	private UserRolesDaoImpl userRolesDaoEM;

	@Override
	public UserRolesModel save(UserRolesModel sysUserRoles) {
		return userRolesDao.save(sysUserRoles);
	}

	@Override
	public int deleteByUserId(long userId) {
		int countDelete = userRolesDaoEM.deleteByUserId(userId);
		LogUtil.logDebugType(logger, true, ActionType.DELETE, "delete  UserRolesModel : {} for update", countDelete);
		return countDelete;
	}
}
