package name.hm.s;

import java.util.ArrayList;

import name.hm.m.Action;
import name.hm.m.Group;
import name.hm.m.Role;
import name.hm.m.User;
import name.hm.s.e.LoginException;
import name.hm.s.e.RightException;

public class UserRightService extends BaseService
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
		group.setRoles(groupMapper.getRoles(group.getGroupId()));
		se.commit();
		if (group == null) {
			endService();
			throw new RightException(RightException.NoGroup);
		}
		
		rolesId = group.getRoles();
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
		ArrayList<Action> ret = new ArrayList<Action>();
		if (user == null) {
			endService();
      throw	new  LoginException(LoginException.NoUser);
		}
		ArrayList<Role> roles = loadRoles(user);
		for(Role role : roles) {
			ArrayList<Action> tmp_actions = actionMapper.selectByRoleId(role.getRoleId());
			ret.addAll(tmp_actions);
		}
		if (ret.size() == 0) {
			endService();
			throw new RightException(RightException.NoAction);
		}
		return ret;
	}

}
