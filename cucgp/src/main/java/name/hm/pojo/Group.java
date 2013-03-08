package name.hm.pojo;

import java.io.Serializable;

import name.hm.pojo.Role.VALID;

import org.eclipse.jetty.util.ajax.JSON;

import com.alibaba.fastjson.JSONObject;


//TODO interrupt : #0309
public class Group
{
	Integer groupId;
	String groupName;
	Valid valid;

	public static final Valid VALID = Valid.valueOf("valid");
	public static final Valid INVALID = Valid.valueOf("invalid");

	public Group()
	{}

	public Group(Integer groupId, String groupName, Valid valid)
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
