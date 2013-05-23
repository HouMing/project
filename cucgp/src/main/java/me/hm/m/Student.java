package me.hm.m;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class Student extends User
{
	String userName;
	String studentName;
	String telephone;
	String email;
	String weibo;
	String introduction;
	String classroomName;
	String departmentName;

	public void sync(Student student) {
		this.userId = student.userId;
		this.groupId = student.groupId;
		this.userName = student.userName;
		this.password = student.password;
		this.userHome = student.userHome;
		this.studentName = student.studentName;
		this.telephone = student.telephone;
		this.email = student.email;
		this.weibo = student.weibo;
		this.introduction = student.introduction;
		this.classroomName = student.classroomName;
		this.departmentName = student.departmentName;
	}
	
	public Student (JSONObject obj) {
        this.userId = obj.getInteger("userId");
		this.groupId = obj.getInteger("groupId");
		this.userName = obj.getString("userName");
		this.password = obj.getString("password");
		this.userHome = obj.getString("userHome");
		this.studentName = obj.getString("studentName");
		this.telephone = obj.getString("telephone");
		this.email = obj.getString("email");
		this.weibo = obj.getString("weibo");
		this.introduction = obj.getString("introduction");
		this.classroomName = obj.getString("classroomName");
		this.departmentName = obj.getString("departmentName");
	}
	
	public Student(Integer userId, Integer groupId, String userName, String password, String userHome, String studentName, String telephone,
			String email, String weibo, String introduction, String classroomName,
			String departmentName)
	{
		super(userId, groupId, userName, password, userHome);
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
