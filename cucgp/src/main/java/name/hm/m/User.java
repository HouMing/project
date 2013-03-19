package name.hm.m;

import name.hm.m.Role.Valid;

import com.alibaba.fastjson.JSONObject;

//TODO TEST POJO - task : #0308
//TODO TEST hashCode, equals - interrupt : #0309
public class User
{
	Integer userId;
	String userName;
	String password;
	String userHome;

	Integer groupId;

	public static final Valid ConstValid = Valid.valueOf("valid");
	public static final Valid ConstInvalid = Valid.valueOf("invalid");

	public User()
	{}

	public User(Integer userId, String userName, String password,
			String userHome, Valid valid, Integer groupId)
	{
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userHome = userHome;
		this.groupId = groupId;
	}

	public User(String userName, String password,
			String userHome, Valid valid, Integer groupId)
	{
		this(null, userName, password, userHome, valid, groupId);
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

}
