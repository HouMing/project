package me.hm.m;

import com.alibaba.fastjson.JSON;

@Deprecated
public class Department
{
	protected Group departmentId;
	protected String departmentName;

	public Department()
	{}

	public Department(String departmentName)
	{
		this(null, departmentName);
	}

	public Department(Group departmentId, String departmentName)
	{
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	
	public Group getDepartmentId()
	{
		return departmentId;
	}

	public void setDepartmentId(Group departmentId)
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

	@Override
	public int hashCode()
	{
		return departmentName.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Department && obj.hashCode() == this.hashCode()) {
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
