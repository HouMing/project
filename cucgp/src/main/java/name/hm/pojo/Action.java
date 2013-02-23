package name.hm.pojo;

import com.alibaba.fastjson.JSONObject;

public class Action {
	Integer actionId;
	String actionName;
	String actionUrl;
	String actionStatus;
	Integer workflowId;
	Integer roleId;
	public Integer getActionId() {
		return actionId;
	}
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public String getActionStatus() {
		return actionStatus;
	}
	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}
	public Integer getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(Integer workflow) {
		this.workflowId = workflow;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
