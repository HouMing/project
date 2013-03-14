package name.hm.m;

import com.alibaba.fastjson.JSON;

//TODO TEST hashCode, equals interrupt : #0309
public class Department
{
	protected Integer departmentId;
	protected String departmentName;

	public Department()
	{}

	public Department(String departmentName)
	{
		this(null, departmentName);
	}

	public Department(Integer departmentId, String departmentName)
	{
		this.departmentId = departmentId;
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
