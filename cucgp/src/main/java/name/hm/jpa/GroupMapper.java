package name.hm.jpa;

import name.hm.pojo.Group;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GroupMapper {

	final String SELECT_BY_GROUPID = "SELECT * FROM cucgp.group WHERE group_id = #{param1}";
	final String SELECT_BY_GROUPNAME = "SELECT * FROM cucgp.group WHERE group_name = #{param1}";
	final String INSERT_GROUP = "INSERT INTO cucgp.group (group_id, group_name, valide) VALUES " +
			                    "(#{groupId}, #{groupName}, #{valide})";
	final String UPDATE = "UPDATE cucgp.group " +
			              "SET group_name = #{groupName}, valide = #{valide} " +
			              "WHERE group_id = #{groupId}";
	
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
	
	@Insert(INSERT_GROUP)
	void insert(Group cellTest);

	@Update(UPDATE)
	void update(Group grp);

}
