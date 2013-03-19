package name.hm.jpa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import name.hm.m.Role;


// @Repository
public interface RoleMapper extends Mapper
{
	public Integer insert(Role role);
	public Integer insertList(Role role);

	public ArrayList<Role> selectAll();
	public Role selectByRoleId(Integer roleId);

	@Deprecated
	public Integer update(Role role);

	public Integer delete(Role role);
	
	final String SELECT_ALL = "SELECT * FROM cucgp.`role` ORDER BY role_id ASC";
	final String SELECT_BY_GROUPID = "SELECT * FROM cucgp.`role` LEFT JOIN ORDER BY role_id ASC";
	final String SELECT_BY_ROLENAME = "SELECT * FROM cucgp.`role` WHERE role_name = #{param1}";
	final String SELECT_BY_ROLEVALID = "SELECT * FROM cucgp.`role` WHERE valid = #{param1} ORDER BY role_id ASC";

	final String UPDATE = "UPDATE cucgp.`role` "
			+ "SET role_name = #{roleName}, valid = #{valid} "
			+ "WHERE role_id = #{roleId}";

	@Deprecated
	public ArrayList<Role> selectByGroupId(Integer groupId);

	@Deprecated
	public Role selectByRoleName(String roleName);


}
