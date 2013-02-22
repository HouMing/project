package name.hm.pojo;

import com.alibaba.fastjson.JSONObject;

public class Workflow {
	
	Integer workflowId;
	String workflowName;
	String workflowStatus;
	public Integer getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public String getWorkflowStatus() {
		return workflowStatus;
	}
	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}
	
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
