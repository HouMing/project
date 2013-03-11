package name.hm.pojo;

import name.hm.pojo.Workflow.Status;

import com.alibaba.fastjson.JSONObject;

public class Action
{
	Integer actionId;
	String actionName;
	String actionUrl;
	Status actionStatus;
	Integer roleId;

	static public Status VALID = Status.valueOf("valid");
	static public Status INVALID = Status.valueOf("invalid");

	public Action()
	{}


	public Action(String actionName, String actionUrl,
			Status actionStatus, Integer roleId)
	{
		this(null, actionName, actionUrl, actionStatus, roleId);
	}

	public Action(Integer actionId, String actionName, String actionUrl,
			Status actionStatus, Integer roleId)
	{
		this.actionId = actionId;
		this.actionName = actionName;
		this.actionUrl = actionUrl;
		this.actionStatus = actionStatus;
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

	public Status getActionStatus()
	{
		return actionStatus;
	}

	public void setActionStatus(Status actionStatus)
	{
		this.actionStatus = actionStatus;
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

	public enum Status
	{
		valid("valid"), invalid("invalid");
		String value;

		Status(String value)
		{
			this.value = value;
		}

		public String toString()
		{
			return value;
		}

	}

}
