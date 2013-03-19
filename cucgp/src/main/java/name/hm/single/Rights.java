package name.hm.single;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.mybatis.guice.transactional.Transactional;
import org.springframework.stereotype.Component;

import name.hm.jpa.ActionMapper;
import name.hm.jpa.RoleMapper;
import name.hm.m.Action;
import name.hm.m.Role;

@Component
@Singleton
public class Rights
{
	static HashMap<Integer, String> urls = new HashMap<Integer, String>();

	@Inject
	protected ActionMapper actionMapper;

	@Inject
	protected RoleMapper roleMapper;

	@Transactional(
			)
	public void loadRights() throws Exception
	{
		ArrayList<Action> actions = actionMapper.selectAll();
		ArrayList<Role> roles = roleMapper.selectAll();
		for (Role role : roles) {
			for (Action action : actions) {
				if (role.getRoleId() == action.getRoleId()) {
					urls.put(role.getRoleId(), action.getActionUrl());
					actions.remove(action);
				}
			}
			roles.remove(role);
		}
	}

	public void setActionMapper(ActionMapper actionMapper)
	{
		this.actionMapper = actionMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper)
	{
		this.roleMapper = roleMapper;
	}
}
