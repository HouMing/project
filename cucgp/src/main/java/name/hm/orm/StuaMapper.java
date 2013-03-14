package name.hm.orm;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import name.hm.m.Stua;
import name.hm.m.Teacher;

public interface StuaMapper
{
	final String INSERT = "Insert INTO cucgp.`stua` (stua_id, tca_id, status, teacher_user_name, student_user_name)"
			+ "VALUES (#{stuaId}, #{tcaId}, #{status}, #{teacherUserName}, #{studentUserName})";

	final String SELECT_BY_STUAID = "select * from cucgp.`stua` WHERE stua_id = #{stuaId}";

	final String UPDATE = "UPDATE cucgp.`stua` SET tca_id = #{tcaId}, status = #{status}, teacher_user_name = #{teacherUserName}, student_user_name = #{studentUserName} WHERE tca_id = #{tcaId}";

	final String DELETE = "DELETE FROM cucgp.`stua` WHERE tca_id = #{tcaId}";

	@Insert(INSERT)
	@SelectKey(before = false, keyProperty = "stuaId", resultType = Integer.class, statement = "SELECT MAX(stua_id) AS stua_id FROM cucgp.`stua`")
	public Integer insert(Stua stua);

	@Select(SELECT_BY_STUAID)
	@Results(value = { @Result(column = "stua_id", property = "tcaId"),
			@Result(column = "status", property = "status"),
			@Result(column = "tca_id", property = "tcaId"),
			@Result(column = "teacher_user_name", property = "teacherUserName"),
			@Result(column = "student_user_name", property = "studentUserName") })
	public Stua selectByStuaId(Integer stuaId);

	@Update(UPDATE)
	public Integer update(Stua stua);
	
	@Delete(DELETE)
	public Integer delete(Stua stua);
}
