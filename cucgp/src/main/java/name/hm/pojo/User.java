package name.hm.pojo;

import name.hm.pojo.Role.VALID;

import com.alibaba.fastjson.JSONObject;

public class User
{
	Integer userId;
	Integer groupId;
	String userName;
	String password;
	String userHome;
	VALID valid;

	public User()
	{}

	public User(Integer userId, String userName, String password,
			String userHome, VALID valid, Integer groupId)
	{
		this.userId = userId;
		this.groupId = groupId;
		this.userName = userName;
		this.password = password;
		this.userHome = userHome;
		this.valid = valid;
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

	public VALID getValid()
	{
		return valid;
	}

	public void setValid(VALID valid)
	{
		this.valid = valid;
	}

	public String getUserHome()
	{
		return userHome;
	}

	public void setUserHome(String userHome)
	{
		this.userHome = userHome;
	}

	public String toString()
	{
		return JSONObject.toJSONString(this);
	}

	public enum VALID
	{
		valid("valid"), invalid("invalid");
		String val;

		VALID(String val_)
		{
			val = val_;
		}

		public String toString()
		{
			return val;
		}
	}

	public static User.VALID getValid(String str)
	{
		switch (str) {
		case "valid":
			return VALID.valid;
		case "invalid":
			return VALID.invalid;
		default:
			return null;
		}
	}
}
