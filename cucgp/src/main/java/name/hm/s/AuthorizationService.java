package name.hm.s;

import java.util.ArrayList;
import java.util.Collections;

import name.hm.m.Action;
import name.hm.m.Group;
import name.hm.m.Role;
import name.hm.m.User;
import name.hm.s.e.LoginException;
import name.hm.s.e.RightException;
import name.hm.s.e.ServiceException;

public class AuthorizationService extends BaseService
{
	
	public User loadUser(String userName, String password) throws LoginException
	{
		User user;
		user = userMapper.selectByUserName(userName);
		se.commit();
		if (user == null ) {
			throw new LoginException(LoginException.NoUser);
		}
		if (user.getGroupId() == null) {
			throw new LoginException(LoginException.InvalidUser);
		}
		if (!user.getPassword().equals(password)) {
			throw new LoginException(LoginException.WrongPassword);
		} else {
			return user;
		}
	}
	
	public Group loadGroup(User user) throws RightException, LoginException
	{
		Group group = null;
		if (user == null) {
			endService();
			throw new LoginException(LoginException.NoUser);
		}
		group = groupMapper.selectByGroupId(user.getGroupId());
		se.commit();
		group.setRoles(groupMapper.getRoles(group.getGroupId()));
		se.commit();
		if(group == null){
			endService();
			throw new RightException(RightException.NoGroup);
		}
		return group;
	}

	public ArrayList<Role> loadRoles(User user) throws LoginException, RightException
	{
		Group group;
		ArrayList<Integer> rolesId;
		ArrayList<Role> roles = new ArrayList<Role>();
		if (user == null) {
			endService();
			throw new LoginException(LoginException.NoLogin);
		}
		group = groupMapper.selectByGroupId(user.getGroupId());
		se.commit();
		if (group == null) {
			endService();
			throw new RightException(RightException.NoGroup);
		}
		
		rolesId = groupMapper.getRoles(group.getGroupId());
		se.commit();
		if (rolesId == null || rolesId.size() == 0) {
			endService();
			throw new RightException(RightException.NoRole);
		}
		
		for (Integer roleId : rolesId) {
			Role tmpRole = roleMapper.selectByRoleId(roleId);
			se.commit();
			roles.add(tmpRole);
		}
		return roles;
	}

	public ArrayList<Action> loadActions(User user) throws LoginException, RightException
	{
		Group group;
		ArrayList<Integer> rolesId;
		ArrayList<Role> roles = new ArrayList<Role>();
		ArrayList<Action> actions = new ArrayList<Action>();
		if (user == null) {
			endService();
			throw new LoginException(LoginException.NoLogin);
		}
		group = groupMapper.selectByGroupId(user.getGroupId());
		se.commit();
		if (group == null) {
			endService();
			throw new RightException(RightException.NoGroup);
		}
		rolesId = groupMapper.getRoles(group.getGroupId());
		se.commit();
		if (rolesId == null || rolesId.size() == 0) {
			endService();
			throw new RightException(RightException.NoRole);
		}
		
		for (Integer roleId : rolesId) {
			ArrayList<Action> tmp_actions = actionMapper.selectByRoleId(roleId);
			se.commit();
			actions.addAll(tmp_actions);
		}
		return actions;
	}

	@Deprecated
	public void actionCheck(User user, String url) throws RightException,ServiceException
	{
		boolean ret = false;
		ArrayList<Action> actions = loadActions(user);
		for (Action ac : actions) {
			ret |= ac.getActionUrl().equals(url);
		}
		if (ret == false) {
			endService();
			throw new RightException(RightException.IllegalAction);
		}
	}

	public boolean userIsRole(Integer userId, Integer roleId) throws ServiceException
	{
		Integer it = ultimateMapper.isRole(userId, roleId);
		if (it == 1) {
			return true;
		} else {
			return false;
		}
	}

}
