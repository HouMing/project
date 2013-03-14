package name.hm.m;

import name.hm.m.Workflow.Status;

import com.alibaba.fastjson.JSONObject;

public class Action
{
	Integer actionId;
	String actionName;
	String actionUrl;
	String description;
	Integer roleId;

	public Action()
	{}


	public Action(String actionName, String actionUrl,
			String description, Integer roleId)
	{
		this(null, actionName, actionUrl, description, roleId);
	}

	public Action(Integer actionId, String actionName, String actionUrl,
			String description, Integer roleId)
	{
		this.actionId = actionId;
		this.actionName = actionName;
		this.actionUrl = actionUrl;
		this.description = description;
		this.roleId = roleId;
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

	public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
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
