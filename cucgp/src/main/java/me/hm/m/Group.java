package me.hm.m;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.alibaba.fastjson.JSONObject;

public class Group
{
	Integer groupId;
	String groupName;
	Set<Integer> roles;

	public Group()
	{}

	public Group(Integer groupId, String groupName, Set<Integer> roles)
	{
		this.groupId = groupId;
		this.groupName = groupName;
		this.roles = roles;
	}

	public Group(String groupName)
	{
		this(null, groupName, new TreeSet<Integer>());
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

	public Set<Integer> getRoles()
	{
		return roles;
	}

	/**
	 * 减法现置空
	 * @param roles
	 */
	public void setRoles(List<Integer> roles)
	{ 
		if (roles == null) {
			this.roles.clear();
			return;
		}
		this.roles.addAll(roles);
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
