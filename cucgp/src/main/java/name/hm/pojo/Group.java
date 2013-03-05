package name.hm.pojo;

import java.io.Serializable;

import name.hm.pojo.Role.VALID;

import org.eclipse.jetty.util.ajax.JSON;

import com.alibaba.fastjson.JSONObject;

public class Group implements Serializable
{
	Integer groupId;
	String groupName;
	VALID valid;

	public Group()
	{}

	public Group(Integer groupId, String groupName, VALID valid)
	{
		this.groupId = groupId;
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

	public VALID getValid()
	{
		return valid;
	}

	public void setValid(VALID valid)
	{
		this.valid = valid;
	}

	@Override
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

	public static name.hm.pojo.Group.VALID getValid(String str)
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
