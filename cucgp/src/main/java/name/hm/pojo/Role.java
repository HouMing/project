package name.hm.pojo;

import name.hm.pojo.Role.VALID;
import name.hm.pojo.Workflow.STATUS;

import org.eclipse.jetty.util.ajax.JSON;

import com.alibaba.fastjson.JSONObject;

public class Role {
	Integer roleId;
	String roleName;
	VALID valid;
	
	public Role(Integer role_Id, String role_Name, VALID _valid)
	{
		roleId = role_Id;
		roleName = role_Name;
		valid = _valid;
	}

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
	public VALID getValid() {
		return valid;
	}
	public void setValid(VALID valid) {
		this.valid = valid;
	}
	
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	public enum VALID {
		valid("valid"),invalid("invalid");
		String val;
		VALID(String val_){
			val = val_;
		}
		public String toString(){
			return val;
		}
	}

	public static VALID VALID(String str)
	{
		return VALID(str);
	}
	
}
