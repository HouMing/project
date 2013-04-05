package name.hm.m;

import com.alibaba.fastjson.JSON;

public class Student
{
	String userName;
	String studentName;
	String telephone;
	String email;
	String weibo;
	String introduction;
	String classroomName;
	String departmentName;

	public Student()
	{}

	public Student(String userName, String studentName, String telephone,
			String email, String weibo, String introduction, String classroomName,
			String departmentName)
	{
		this.userName = userName;
		this.studentName = studentName;
		this.telephone = telephone;
		this.email = email;
		this.weibo = weibo;
		this.introduction = introduction;
		this.classroomName = classroomName;
		this.departmentName = departmentName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
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

	public String getClassroomName()
	{
		return classroomName;
	}

	public void setClassroomName(String classroomName)
	{
		this.classroomName = classroomName;
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
		return userName.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Student && obj.hashCode() == this.hashCode()) {
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
