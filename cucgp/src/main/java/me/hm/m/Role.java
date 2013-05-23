package me.hm.m;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.alibaba.fastjson.JSONObject;

public class Role {
	Integer roleId;
	String roleName;

	Set<Integer> actions;
	Set<Integer> groups;

	public Role() {
		
	}

	public Role(String roleName) {
		this(null, roleName, new TreeSet<Integer>(), new TreeSet<Integer>());
	}

	public Role(Integer roleId, String roleName) {
		this(roleId, roleName, new TreeSet<Integer>(), new TreeSet<Integer>());
	}

	public Role(Integer roleId, String roleName, Set<Integer> actions,
			Set<Integer> groups) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.actions = actions;
		this.groups = groups;
	}

	public Set<Integer> getGroups() {
		return groups;
	}

	/**
	 * 作减法前先对其置空
	 * 
	 * @param groups
	 */
	public void setGroups(List<Integer> groups) {
		if (groups == null) {
			this.groups.clear();
			return;
		}
		this.groups.addAll(groups);
		return;
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

	public void setActions(Set<Integer> actions) {
		if (actions == null) {
			this.actions.clear();
			return;
		}
		this.actions.addAll(actions);
	}

	public Set<Integer> getActions() {
		return this.actions;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	@Override
	public int hashCode() {
		return roleId;
	}

	@Override
	public boolean equals(Object obj) {
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
