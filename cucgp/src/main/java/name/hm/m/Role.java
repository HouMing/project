package name.hm.m;

import java.util.ArrayList;
import java.util.HashSet;

import com.alibaba.fastjson.JSONObject;

//TODO TEST hashCode, equals interrupt : #0309
public class Role
{
	Integer roleId;
	String roleName;
	
	ArrayList<Integer> actions;
	ArrayList<Integer> groups;

	public Role()
	{}

	public Role(String roleName)
	{
		this(null, roleName, null, null);
	}

	public Role(Integer roleId, String roleName, ArrayList<Integer> actions, ArrayList<Integer> groups)
	{
		this.roleId = roleId;
		this.roleName = roleName;
		this.actions = actions;
		this.groups = groups;
	}

	public ArrayList<Integer> getGroups()
	{
		return groups;
	}

	public void setGroups(ArrayList<Integer> groups)
	{
		this.groups = groups;
  }
	
	public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public void setActions(ArrayList<Integer> actions)
	{
		this.actions = actions;
	}

	public ArrayList<Integer> getActions()
	{
		return this.actions;
	}

	@Override
	public String toString()
	{
		return JSONObject.toJSONString(this);
	}

	@Override
	public int hashCode()
	{
		return roleId;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Role && obj.hashCode() == this.hashCode()) {
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
