package name.hm.pojo;

import java.io.Serializable;

import org.eclipse.jetty.util.ajax.JSON;

import com.alibaba.fastjson.JSONObject;

public class Group implements Serializable {
	private Integer groupId;
	private String groupName;
	private String valid;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
