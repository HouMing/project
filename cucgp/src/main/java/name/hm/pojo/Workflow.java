package name.hm.pojo;

import com.alibaba.fastjson.JSONObject;

//TODO TEST POJO - task : #0308
//TODO hashCode, equals - interrupt : #0309
public class Workflow
{
	Integer workflowId;
	String workflowName;
	STATUS workflowStatus;

	public static final STATUS VALID = STATUS.valueOf("valid");
	public static final STATUS INVALID = STATUS.valueOf("invalid");

	public Workflow()
	{}

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
