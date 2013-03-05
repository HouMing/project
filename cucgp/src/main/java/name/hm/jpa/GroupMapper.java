package name.hm.jpa;

import java.util.List;

import name.hm.pojo.Group;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GroupMapper extends Mapper
{
	final String SELECT_BY_GROUPID = "SELECT * FROM `group` WHERE group_id = #{param1}";
	final String SELECT_BY_GROUPNAME = "SELECT * FROM `group` WHERE group_name = #{param1}";
	final String SELECT_BY_GROUPVALID = "SELECT * FROM `group` WHERE valid = #{param1}";
	final String INSERT_GROUP = "INSERT INTO `group` (group_id, group_name, valid) VALUES " +
			                    "(#{groupId}, #{groupName}, #{valid})";
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
	List<Group> selectByValid(Group.VALID valid);
	
	@Insert(INSERT_GROUP)
	Integer insert(Group cellTest);

	@Update(UPDATE)
	Integer update(Group grp);

	@Delete(DELETE)
	Integer delete(Group grp);


}
