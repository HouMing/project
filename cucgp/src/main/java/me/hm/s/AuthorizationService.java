package me.hm.s;

import java.util.ArrayList;
import java.util.List;

import me.hm.jpa.ActionDao;
import me.hm.jpa.GroupDao;
import me.hm.jpa.RoleDao;
import me.hm.jpa.UserDao;
import me.hm.m.Action;
import me.hm.m.Group;
import me.hm.m.Role;
import me.hm.m.User;
import me.hm.s.e.AuthorizationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class AuthorizationService {

	@Autowired
	UserDao userDao;
	@Autowired
	GroupDao groupDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	ActionDao actionDao;

	public User login(String userName, String password)	throws AuthorizationException {
		User user = userDao.getUser(userName);
		if ( user == null) {
			throw new AuthorizationException(AuthorizationException.NoUser);
		}
		if (!password.equals(user.getPassword())) {
			throw new AuthorizationException(AuthorizationException.WrongPassword);
		}
		return user;
	}

	public Group lsGroups(User user) throws AuthorizationException {
		try {
			Group group = groupDao.readById(user.getGroupId());
			return group;
		} catch (DataAccessException e) {
			throw new AuthorizationException(AuthorizationException.NoGroup);
		}
	}

	public ArrayList<Role> lsRoles(User user) throws AuthorizationException {
		Group group = lsGroups(user);
		ArrayList<Role> roles = new ArrayList<Role>();
		for (Integer roleId : group.getRoles()) {
			try {
				Role role = roleDao.readById(roleId);
				roles.add(role);
			} catch (DataAccessException e) {
				continue;
			}
		}
		if (roles.size() == 0) {
			throw new AuthorizationException(AuthorizationException.NoRole);
		}
		return roles;
	}

	public ArrayList<Action> lsActions(User user) throws AuthorizationException {
		ArrayList<Role> roles = lsRoles(user);
		ArrayList<Action> actions = new ArrayList<Action>();
		for (Role role : roles) {
			for (Integer actionId : role.getActions()) {
		        Action action = actionDao.readById(actionId);
				actions.add(action);
			}
		}
		if (actions.size() == 0) {
			throw new AuthorizationException(AuthorizationException.NoAction);
		}
		return actions;
	}

	public void accessible(User user, Action action)
			throws AuthorizationException {
		ArrayList<Action> actions = lsActions(user);
		if (!actions.contains(actions)) {
			throw new AuthorizationException( AuthorizationException.NoPermission);
		}
	}

	public void userIsRole(User user, Role role) throws AuthorizationException {
		List<Role> roles = lsRoles(user);
		if (!roles.contains(role)) {
			throw new AuthorizationException(AuthorizationException.NoRole);
		}
	}

}
