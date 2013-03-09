package name.hm.pojo;

import name.hm.pojo.Role.Valid;

import com.alibaba.fastjson.JSONObject;

//TODO TEST POJO - task : #0308
//TODO TEST hashCode, equals - interrupt : #0309
public class User
{
	Integer userId;
	Integer groupId;
	String userName;
	String password;
	String userHome;
	Valid valid;

	public static final Valid VALID = Valid.valueOf("valid");
	public static final Valid INVALID = Valid.valueOf("invalid");

	public User()
	{}

	public User(Integer userId, String userName, String password,
			String userHome, Valid valid, Integer groupId)
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

	public Valid getValid()
	{
		return valid;
	}

	public void setValid(Valid valid)
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

	public enum Valid
	{
		valid("valid"), invalid("invalid");
		String value;

		Valid(String value)
		{
			this.value = value;
		}

		public String toString()
		{
			return value;
		}
	}

}
