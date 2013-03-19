package name.hm.jpa;

import java.util.LinkedList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import name.hm.m.Classroom;

// TODO TEST MP Upgrade - task : #0310
public interface ClassroomMapper
{
	final String INSERT = "INSERT INTO cucgp.`classroom` (classroom_id, classroom_name) VALUES (#{classroomId}, #{classroomName})";
	
	final String SELECT_BY_CLASSROOM_ID = "SELECT * FROM cucgp.`classroom` WHERE classroom_id = #{param1}";
	final String SELECT_BY_CLASSROOM_NAME = "SELECT * FROM cucgp.`classroom` WHERE classroom_name = #{param1}";
	final String SELECT_ALL = "SELECT * FROM cucgp.`classroom` ORDER BY classroom_id ASC";
	
	final String UPDATE = "UPDATE cucgp.`classroom` SET classroom_name = #{classroomName} WHERE classroom_id = #{classroomId}";

	final String DELETE = "DELETE FROM cucgp.`classroom` WHERE classroom_id = #{classroomId}";
	
//	@Insert(INSERT)
	@SelectKey(before = false, keyProperty = "classroomId", resultType = Integer.class, statement = { "SELECT MAX(classroom_id) AS classroom_id FROM cucgp.`classroom`" })
	@Options(useGeneratedKeys = true, keyProperty = "classroomId", keyColumn = "classroom_id")
	Integer insert(Classroom cLASSROOM2);

//	@Select(SELECT_BY_CLASSROOM_ID)
	@Results(value = {
	@Result(property = "classroomId", column = "classroom_id"),
	@Result(property = "classroomName", column = "classroom_name")
	})
	Classroom selectByClassroomId(Integer classroomId);


//	@Select(SELECT_BY_CLASSROOM_NAME)
	@Results(value = {
	@Result(property = "classroomId", column = "classroom_id"),
	@Result(property = "classroomName", column = "classroom_name")
	})
	Classroom selectByClassroomName(String classroomName);

//	@Select(SELECT_ALL)
	@Results(value = {
	@Result(property = "classroomId", column = "classroom_id"),
	@Result(property = "classroomName", column = "classroom_name")
	})
	LinkedList<Classroom> selectAll();

//	@Update(UPDATE)
	Integer update(Classroom classroom);

//	@Delete(DELETE)
	Integer delete(Classroom cLASSROOM2);
	
}
