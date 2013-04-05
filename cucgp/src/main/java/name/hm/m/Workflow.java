package name.hm.m;

import com.alibaba.fastjson.JSONObject;

public class Workflow
{
	Integer workflowId;
	String workflowName;
	Status workflowStatus;

	public static final Status APPLYING = Status.valueOf("applying");
	public static final Status PUBLISHING = Status.valueOf("publishing");
	public static final Status PROCESSING = Status.valueOf("processing");
	public static final Status FINISHING = Status.valueOf("finishing");

	public Workflow()
	{}

	public Workflow(String workflow_Name,
			Status workflow_Status)
	{
		this(null, workflow_Name, workflow_Status);
	}
	
	public Workflow(Integer workflow_Id, String workflow_Name,
			Status workflow_Status)
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

	public Status getWorkflowStatus()
	{
		return workflowStatus;
	}

	public void setWorkflowStatus(Status workflowStatus)
	{
		this.workflowStatus = workflowStatus;
	}

	@Override
	public String toString()
	{
		return JSONObject.toJSONString(this);
	}
	
	@Override
	public int hashCode()
	{
		return workflowId;
	}

	@Override
	public boolean equals(Object obj)
	{
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

	public enum Status
	{
		applying("教师申请"), publishing("审核发布"), processing("毕设进行"), finishing("毕设结束");
		
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
