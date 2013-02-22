package name.hm.pojo;

import org.eclipse.jetty.util.ajax.JSON;

import com.alibaba.fastjson.JSONObject;

public class Role {
	Integer roleId;
	String roleName;
	String valid;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
