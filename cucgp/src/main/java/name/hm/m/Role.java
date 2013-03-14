package name.hm.m;

import com.alibaba.fastjson.JSONObject;

//TODO TEST hashCode, equals interrupt : #0309
public class Role
{
	Integer roleId;
	String roleName;
	Valid valid;

	public static final Valid VALID = Valid.valueOf("valid");
	public static final Valid INVALID = Valid.valueOf("invalid");

	public Role()
	{}
	
	public Role(String roleName, Valid valid)
	{
		this(null, roleName, valid);
	}
	
	public Role(Integer roleId, String roleName, Valid valid)
	{
		this.roleId = roleId;
		this.roleName = roleName;
		this.valid = valid;
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
