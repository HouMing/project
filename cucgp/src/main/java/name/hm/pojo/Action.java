package name.hm.pojo;

import name.hm.pojo.Workflow.STATUS;

import com.alibaba.fastjson.JSONObject;

public class Action
{
	Integer actionId;
	String actionName;
	String actionUrl;
	STATUS actionStatus;
	Integer workflowId;
	Integer roleId;

	static public STATUS VALID = STATUS.valueOf("valid");
	static public STATUS INVALID = STATUS.valueOf("invalid");
	
	public Action()
	{}

	public Action(Integer actionId, String actionName, String actionUrl,
			STATUS actionStatus, Integer roleId, Integer workflowId)
	{
		this.actionId = actionId;
		this.actionName = actionName;
		this.actionUrl = actionUrl;
		this.actionStatus = actionStatus;
		this.workflowId = workflowId;
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

	public STATUS getActionStatus()
	{
		return actionStatus;
	}

	public void setActionStatus(STATUS actionStatus)
	{
		this.actionStatus = actionStatus;
	}

	public Integer getWorkflowId()
	{
		return workflowId;
	}

	public void setWorkflowId(Integer workflow)
	{
		this.workflowId = workflow;
	}

	public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}

	public String toString()
	{
		return JSONObject.toJSONString(this);
	}

	public enum STATUS
	{
		valid("valid"), invalid("invalid");
		String val;

		STATUS(String val)
		{
			this.val = val;
		}

		public String toString()
		{
			return val;
		}

	}

}
