package name.hm.m;

import java.util.ArrayList;


import com.alibaba.fastjson.JSONObject;

public class Group
{
	Integer groupId;
	String groupName;
	ArrayList<Integer> roles;

	public Group()
	{}

	public Group(Integer groupId, String groupName,ArrayList<Integer> roles)
	{
		this.groupId = groupId;
		this.groupName = groupName;
		this.roles = roles;
	}

	public Group(String groupName)
	{
		this(null, groupName, new ArrayList<Integer>(5));
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

	public ArrayList<Integer> getRoles()
	{
		return roles;
	}

	public void setRoles(ArrayList<Integer> roles)
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
}
