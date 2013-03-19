package name.hm.m;

import java.util.ArrayList;
import java.util.HashSet;

import com.alibaba.fastjson.JSONObject;

//TODO TEST hashCode, equals interrupt : #0309
public class Role
{
	Integer roleId;
	String roleName;
	Valid valid;
	
	ArrayList<Integer> actions;
	HashSet<Integer> groupIds;

	public static final Valid ConstValid = Valid.valueOf("valid");
	public static final Valid ConstInvalid = Valid.valueOf("invalid");
	
	public HashSet<Integer> getGroupIds()
	{
		return groupIds;
	}

	public void setGroupIds(HashSet<Integer> groupIds)
	{
		this.groupIds = groupIds;
 }

	public Role()
	{}

	public Role(Integer roleId, String roleName, Valid valid,
			ArrayList<Integer> actions)
	{
		this.roleId = roleId;
		this.roleName = roleName;
		this.valid = valid;
		this.actions = actions;
	}

	public Role(String roleName, Valid valid, ArrayList<Integer> actions)
	{
		this(null, roleName, valid, actions);
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

	public Valid getValid()
	{
		return valid;
	}

	public void setValid(Valid valid)
	{
		this.valid = valid;
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
