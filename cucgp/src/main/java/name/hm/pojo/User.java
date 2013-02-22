package name.hm.pojo;

import com.alibaba.fastjson.JSONObject;

public class User {
	Integer userId;
	Integer groupId;
	String userName;
	String password;
	String valid;
	String userHome;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getUserHome() {
		return userHome;
	}
	public void setUserHome(String userHome) {
		this.userHome = userHome;
	}
	
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
