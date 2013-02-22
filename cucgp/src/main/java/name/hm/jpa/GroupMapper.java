package name.hm.jpa;

import name.hm.pojo.Group;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

public interface GroupMapper {

	final String SELECT_BY_ID = "SELECT * FROM cucgp.group WHERE group_id = #{param1}";
	final String INSERT_GROUP = "INSERT INTO cucgp.group (group_id, group_name, valide) VALUES " +
			                    "(#{groupId}, #{groupName}, #{valide})";
	
	//Basic CRUD method
	@Select(SELECT_BY_ID)
	@Results(value = {
			@Result(property="groupId",column="group_id"),
			@Result(property="groupName",column="group_name"),
			@Result(property="valide",column="valide")
	})
	Group select(Integer id);

	@Insert(INSERT_GROUP)
	void insert(Group cellTest);
}
