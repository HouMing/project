package name.hm.jpa;

import java.util.List;

import name.hm.pojo.Group;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GroupMapper {

	final String SELECT_BY_GROUPID = "SELECT * FROM cucgp.group WHERE group_id = #{param1}";
	final String SELECT_BY_GROUPNAME = "SELECT * FROM cucgp.group WHERE group_name = #{param1}";
	final String SELECTL_BY_GROUPVALIDE = "SELECT * FROM cucgp.group WHERE valide = #{param1}";
	final String INSERT_GROUP = "INSERT INTO cucgp.group (group_id, group_name, valide) VALUES " +
			                    "(#{groupId}, #{groupName}, #{valide})";
	final String UPDATE = "UPDATE cucgp.group " +
			              "SET group_name = #{groupName}, valide = #{valide} " +
			              "WHERE group_id = #{groupId}";
	final String DELETE = "DELETE FROM cucgp.group WHERE group_id = #{groupId}";
	
	//Basic CRUD method
	@Select(SELECT_BY_GROUPID)
	@Results(value = {
			@Result(property="groupId",column="group_id"),
			@Result(property="groupName",column="group_name"),
			@Result(property="valide",column="valide")
	})
	Group selectByGroupId(Integer groupId);

	@Select(SELECT_BY_GROUPNAME)
	@Results(value = {
			@Result(property="groupId",column="group_id"),
			@Result(property="groupName",column="group_name"),
			@Result(property="valide",column="valide")
	})
	Group selectByGroupName(String groupName);
	
	@Select(SELECTL_BY_GROUPVALIDE)
	@Results(value = {
			@Result(property="groupId",column="group_id"),
			@Result(property="groupName",column="group_name"),
			@Result(property="valide",column="valide")
	})
	List<Group> selectByValide(String valide);
	
	@Insert(INSERT_GROUP)
	Integer insert(Group cellTest);

	@Update(UPDATE)
	Integer update(Group grp);

	@Delete(DELETE)
	Integer delete(Group grp);


}
