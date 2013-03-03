package name.hm.pojo;

import com.alibaba.fastjson.JSON;

public class Department
{
	String departmentName;

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
