package name.hm.jpa;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import name.hm.pojo.Role;

public interface RoleMapper extends Mapper
{
	final String INSERT_ROLE = "INSERT INTO cucgp.`role` (role_id, role_name, valid) VALUES " +
			"(#{roleId}, #{roleName}, #{valid})";
	final String SELECT_BY_ROLEID = "SELECT * FROM cucgp.`role` WHERE role_id = #{param1}";
	final String SELECT_BY_ROLENAME = "SELECT * FROM cucgp.`role` WHERE role_name = #{param1}";
	final String SELECT_BY_ROLEVALID = "SELECT * FROM cucgp.`role` WHERE valid = #{param1}";
	final String UPDATE = "UPDATE cucgp.`role` " +
			              "SET role_name = #{roleName}, valid = #{valid} " +
			              "WHERE role_id = #{roleId}";
	final String DELETE = "DELETE FROM cucgp.`role` WHERE role_id = #{roleId}";
	final String LAST_INSERT_ID = "SELECT MAX(role_id) AS role_id FROM cucgp.`role`";
	
	@Insert(INSERT_ROLE)
	@SelectKey(statement = "SELECT MAX(role_id + 1) AS role_id FROM cucgp.`role`", 
	           keyProperty = "roleId", 
	           resultType = Integer.class, 
	           before = false)
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	Integer insert(Role role);
	
	@Select(LAST_INSERT_ID)
	Integer lastInsertId();
	
	@Select(SELECT_BY_ROLEID)
	@Results(value = {
			@Result(property="roleId",column="role_id"),
			@Result(property="roleName",column="role_name"),
			@Result(property="valid",column="valid")
	})
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	Role selectByRoleId(Integer roleId);
	
	@Select(SELECT_BY_ROLENAME)
	@Results(value = {
			@Result(property="roleId",column="role_id"),
			@Result(property="roleName",column="role_name"),
			@Result(property="valid",column="valid")
	})
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	Role selectByRoleName(String roleName);
	
	@Select(SELECT_BY_ROLEVALID)
	@Results(value = {
			@Result(property="roleId",column="role_id"),
			@Result(property="roleName",column="role_name"),
			@Result(property="valid",column="valid")
	})
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	List<Role> selectByValid(Role.VALID valid);
	
	@Update(UPDATE)
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	Integer update(Role role);

	@Delete(DELETE)
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	Integer delete(Role role);

}
