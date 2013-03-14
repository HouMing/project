package name.hm.single;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import name.hm.m.Action;
import name.hm.m.Role;
import name.hm.orm.ActionMapper;
import name.hm.orm.RoleMapper;

@Component
public class Rights
{
	static HashMap<Integer, String> urls = new HashMap<Integer, String>();

	protected ActionMapper actionMapper = null;
	protected RoleMapper roleMapper = null;

	boolean isInited = false;

	public void loadRights() throws Exception
	{
		if ( !isInited || !isInited() )
		{
			throw new Exception("not init");
		}
		synchronized (urls) {
			ArrayList<Action> actions = actionMapper.selectAll();
			ArrayList<Role> roles = roleMapper.selectByAll();
			for (Role role : roles) {
				for (Action action : actions) {
					if (role.getRoleId() == action.getRoleId()) {
						urls.put(role.getRoleId(), action.getActionUrl());
						actions.remove(action);
					}
				}
				roles.remove(role);
			}
			urls.notify();
		}
	}

	private boolean isInited()
	{
		if (actionMapper != null && roleMapper != null) {
			isInited = true;
		} else {
			isInited = false;
		}
		return isInited;
	}

	public ActionMapper getActionMapper()
	{
		return actionMapper;
	}

	public void setActionMapper(ActionMapper actionMapper)
	{
		this.actionMapper = actionMapper;
	}

	public RoleMapper getRoleMapper()
	{
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper)
	{
		this.roleMapper = roleMapper;
	}
}
