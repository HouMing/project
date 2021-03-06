package me.hm.m;

import com.alibaba.fastjson.JSONObject;

public class User implements Comparable<User>
{
	Integer userId;
	String userName;
	String password;
	String userHome;

	Integer groupId;

	public User()
	{}

	public User(Integer userId, Integer groupId, String userName, String password,
			String userHome)
	{
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userHome = userHome;
		this.groupId = groupId;
	}
	
	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public Integer getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserHome()
	{
		return userHome;
	}

	public void setUserHome(String userHome)
	{
		this.userHome = userHome;
	}

	@Override
	public String toString()
	{
		return JSONObject.toJSONString(this);
	}

	@Override
	public int hashCode()
	{
		return userId;
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

	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		if (o != null) {
		return o.userId.compareTo(userId);
		}
		else {
		return 1;	
		}
	}

}
