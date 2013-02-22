package name.hm.pojo;

import java.io.Serializable;

import org.eclipse.jetty.util.ajax.JSON;

import com.alibaba.fastjson.JSONObject;

public class Group implements Serializable {
	private Integer groupId;
	private String groupName;
	private String valide;

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

	public String getValide() {
		return valide;
	}

	public void setValide(String valide) {
		this.valide = valide;
	}

	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
