package name.hm.jpa;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import name.hm.pojo.Role;

public interface RoleMapper extends Mapper
{
	final String SELECT_BY_ROLEID = "SELECT * FROM cucgp.`role` WHERE role_id = #{param1}";
	final String SELECT_BY_ROLENAME = "SELECT * FROM cucgp.`role` WHERE role_name = #{param1}";
	final String SELECT_BY_ROLEVALID = "SELECT * FROM cucgp.`role` WHERE valid = #{param1}";
	final String INSERT_ROLE = "INSERT INTO cucgp.`role` (role_id, role_name, valid) VALUES " +
			                    "(#{roleId}, #{roleName}, #{valid})";
	final String UPDATE = "UPDATE cucgp.`role` " +
			              "SET role_name = #{roleName}, valid = #{valid} " +
			              "WHERE role_id = #{roleId}";
	final String DELETE = "DELETE FROM cucgp.`role` WHERE role_id = #{roleId}";
	
	@Insert(INSERT_ROLE)
	Integer insert(Role role);
	
	@Select(SELECT_BY_ROLEID)
	@Results(value = {
			@Result(property="roleId",column="role_id"),
			@Result(property="roleName",column="role_name"),
			@Result(property="valid",column="valid")
	})
	Role selectByRoleId(Integer roleId);
	
	@Select(SELECT_BY_ROLENAME)
	@Results(value = {
			@Result(property="roleId",column="role_id"),
			@Result(property="roleName",column="role_name"),
			@Result(property="valid",column="valid")
	})
	Role selectByRoleName(String roleName);
	
	@Select(SELECT_BY_ROLEVALID)
	@Results(value = {
			@Result(property="roleId",column="role_id"),
			@Result(property="roleName",column="role_name"),
			@Result(property="valid",column="valid")
	})
	List<Role> selectByValid(String valid);
	
	@Update(UPDATE)
	Integer update(Role role);

	@Delete(DELETE)
	Integer delete(Role role);

}
