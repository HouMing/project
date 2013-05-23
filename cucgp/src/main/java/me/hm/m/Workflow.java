package me.hm.m;

import com.alibaba.fastjson.JSONObject;

public class Workflow {
	String workflowName;
	Integer workflowStatus;

	public Workflow(JSONObject obj) {
		this.workflowName = obj.getString("workflowName");
		this.workflowStatus = obj.getInteger("workflowStatus");
	}

	public Workflow(String workflow_Name, Integer workflow_Status) {
		workflowName = workflow_Name;
		workflowStatus = workflow_Status;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public Integer getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(Integer workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	@Override
	public int hashCode() {
		return workflowName.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Workflow && obj.hashCode() == this.hashCode()) {
			if (obj.toString().equals(this.toString())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
