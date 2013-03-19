package name.hm.m;

import java.util.ArrayList;


import com.alibaba.fastjson.JSONObject;

//TODO TEST hashCode, equals interrupt : #0309
public class Group
{
	Integer groupId;
	String groupName;
	Valid valid;
	ArrayList<Integer> roles;

	public static final Valid ConstValid = Valid.valueOf("valid");
	public static final Valid ConstInvalid = Valid.valueOf("invalid");

	public Group()
	{}

	public Group(Integer groupId, String groupName, Valid valid)
	{
		this.groupId = groupId;
		this.groupName = groupName;
		this.valid = valid;
	}

	public Group(String groupName, Valid valid)
	{
		this.groupId = null;
		this.groupName = groupName;
		this.valid = valid;
	}
	
	public Integer getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public Valid getValid()
	{
		return valid;
	}

	public void setValid(Valid valid)
	{
		this.valid = valid;
	}

	public ArrayList<Integer> getRoles()
	{
		return roles;
	}

	public void setRoleIds(ArrayList<Integer> roles)
	{
		this.roles = roles;
	}

	@Override
	public String toString()
	{
		return JSONObject.toJSONString(this);
	}

	@Override
	public int hashCode()
	{
		return groupId;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Group && obj.hashCode() == this.hashCode()) {
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
		valid("有效"), invalid("无效");
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
