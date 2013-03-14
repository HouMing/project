package name.hm.orm;

import java.util.LinkedList;
import java.util.List;

import name.hm.m.Group;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMapper extends Mapper
{
	final String SELECT_BY_GROUPID = "SELECT * FROM `group` WHERE group_id = #{param1}";
	final String SELECT_BY_GROUPNAME = "SELECT * FROM `group` WHERE group_name = #{param1}";
	final String SELECT_BY_GROUPVALID = "SELECT * FROM `group` WHERE valid = #{param1}";
	final String INSERT_GROUP = "INSERT INTO `group` (group_name, valid) VALUES " +
			                    "(#{groupName}, #{valid})";
	final String UPDATE = "UPDATE `group` " +
			              "SET group_name = #{groupName}, valid = #{valid} " +
			              "WHERE group_id = #{groupId}";
	final String DELETE = "DELETE FROM `group` WHERE group_id = #{groupId}";
	
	//Basic CRUD method
	@Select(SELECT_BY_GROUPID)
	@Results(value = {
			@Result(property="groupId",column="group_id"),
			@Result(property="groupName",column="group_name"),
			@Result(property="valid",column="valid")
	})
	Group selectByGroupId(Integer groupId);

	@Select(SELECT_BY_GROUPNAME)
	@Results(value = {
			@Result(property="groupId",column="group_id"),
			@Result(property="groupName",column="group_name"),
			@Result(property="valid",column="valid")
	})
	Group selectByGroupName(String groupName);
	
	@Select(SELECT_BY_GROUPVALID)
	@Results(value = {
			@Result(property="groupId",column="group_id"),
			@Result(property="groupName",column="group_name"),
			@Result(property="valid",column="valid")
	})
	LinkedList<Group> selectByValid(Group.Valid valid);
	
	@Insert(INSERT_GROUP)
	@Options(useGeneratedKeys = true, keyProperty = "groupId", keyColumn = "group_id")
	@SelectKey(statement = "select MAX(group_id) AS group_id FROM cucgp.`group`", before = false, keyProperty = "groupId", resultType = Integer.class)
	Integer insert(Group group);

	@Update(UPDATE)
	Integer update(Group grp);

	@Delete(DELETE)
	Integer delete(Group grp);


}
