package name.hm.pojo;

import name.hm.pojo.Workflow.STATUS;

import com.alibaba.fastjson.JSONObject;

public class Workflow
{

	Integer workflowId;
	String workflowName;
	STATUS workflowStatus;

	public Workflow(Integer workflow_Id, String workflow_Name,
			STATUS workflow_Status)
	{
		workflowId = workflow_Id;
		workflowName = workflow_Name;
		workflowStatus = workflow_Status;
	}

	public Integer getWorkflowId()
	{
		return workflowId;
	}

	public void setWorkflowId(Integer workflowId)
	{
		this.workflowId = workflowId;
	}

	public String getWorkflowName()
	{
		return workflowName;
	}

	public void setWorkflowName(String workflowName)
	{
		this.workflowName = workflowName;
	}

	public STATUS getWorkflowStatus()
	{
		return workflowStatus;
	}

	public void setWorkflowStatus(STATUS workflowStatus)
	{
		this.workflowStatus = workflowStatus;
	}

	@Override
	public String toString()
	{
		return JSONObject.toJSONString(this);
	}

	public enum STATUS
	{
		valid("valid"), invalid("invalid");
		String val;

		STATUS(String val_)
		{
			val = val_;
		}

		public String toString()
		{
			return val;
		}
	}

	public static STATUS getStatus(String str)
	{
		switch (str) {
		case "valid":
			return STATUS.valid;
		case "invalid":
			return STATUS.invalid;
		default:
			return null;
		}
	}

}
