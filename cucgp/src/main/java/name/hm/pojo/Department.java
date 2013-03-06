package name.hm.pojo;

import com.alibaba.fastjson.JSON;

public class Department
{
	protected Integer departmentId;
	protected String departmentName;

	public Department()
	{}

	public Department(String departmentName)
	{
		this.departmentName = departmentName;
	}

	public Integer getDepartmentId()
	{
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId)
	{
		this.departmentId = departmentId;
	}

	public String getDepartmentName()
	{
		return departmentName;
	}

	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	@Override
	public String toString()
	{
		return JSON.toJSONString(this);
	}

}
