package name.hm.m;

import com.alibaba.fastjson.JSON;

public class Teacher
{
	String userName;
	String teacherName;
	String telephone;
	String email;
	String weibo;
	String introduction;
	String departmentName;
	String titleName;

	public Teacher()
	{}

	public Teacher(String userName, String teacherName, String telephone,
			String email, String weibo, String introduction, String departmentName,
			String titleName)
	{
		this.userName = userName;
		this.teacherName = teacherName;
		this.telephone = telephone;
		this.email = email;
		this.weibo = weibo;
		this.introduction = introduction;
		this.departmentName = departmentName;
		this.titleName = titleName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getTeacherName()
	{
		return teacherName;
	}

	public void setTeacherName(String teacherName)
	{
		this.teacherName = teacherName;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getWeibo()
	{
		return weibo;
	}

	public void setWeibo(String weibo)
	{
		this.weibo = weibo;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public String getDepartmentName()
	{
		return departmentName;
	}

	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	public String getTitleName()
	{
		return titleName;
	}

	public void setTitleName(String titleName)
	{
		this.titleName = titleName;
	}

	@Override
	public String toString()
	{
		return JSON.toJSONString(this);
	}

	@Override
	public int hashCode()
	{
		return userName.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof User && obj.hashCode() == this.hashCode()) {
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
