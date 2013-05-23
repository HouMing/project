package me.hm.m;

import java.util.Set;
import java.util.TreeSet;

import com.alibaba.fastjson.JSONObject;

public class Action
{
	Integer actionId;
	String actionName;
	String actionUrl;
	String description;
	Set<Integer> roles;

	public Action()
	{}

	public Action(Integer actionId, Set<Integer> roles, String actionName, String actionUrl, String description)
	{
		this.actionId = actionId;
		this.roles = roles;
		this.actionName = actionName;
		this.actionUrl = actionUrl;
		this.description = description;
	}

	public Action(Set<Integer> roles, String actionName, String actionUrl, String description)
	{
		this(null, roles, actionName, actionUrl, description);
	}
	
	public Action(String actionName, String actionUrl, String description)
	{
		this(null, new TreeSet<Integer>(), actionName, actionUrl, description);
	}
	
	public Integer getActionId()
	{
		return actionId;
	}

	public void setActionId(Integer actionId)
	{
		this.actionId = actionId;
	}

	public String getActionName()
	{
		return actionName;
	}

	public void setActionName(String actionName)
	{
		this.actionName = actionName;
	}

	public String getActionUrl()
	{
		return actionUrl;
	}

	public void setActionUrl(String actionUrl)
	{
		this.actionUrl = actionUrl;
	}

	public Set<Integer> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<Integer> roleId)
	{
		if (roleId == null) {
			this.roles.clear();
			return;
		}
		this.roles.addAll(roleId);
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return JSONObject.toJSONString(this);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Action && obj.hashCode() == this.hashCode()) {
			if (this.toString().equals(obj.toString())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return actionId;
	}

}
