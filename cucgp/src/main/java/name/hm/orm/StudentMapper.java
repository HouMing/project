package name.hm.orm;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import name.hm.m.Student;

public interface StudentMapper
{
	final String INSERT = "INSERT INTO cucgp.`student` "
			+ "(user_name, student_name, telephone, email, weibo, introduction, classroom_name, department_name)"
			+ "VALUES "
			+ "(#{userName}, #{studentName}, #{telephone}, #{email}, #{weibo}, #{introduction}, #{classroomName}, #{departmentName})";

	final String SELECT_BY_USERNAME = "SELECT * FROM cucgp.`student` WHERE user_name = #{param1}";

	final String UPDATE = "UPDATE cucgp.`student` SET " +
			"student_name = #{studentName}, telephone = #{telephone}, email = #{email}, weibo = #{weibo}, introduction = #{introduction}, " +
			"classroom_name = #{classroomName}, department_name = #{departmentName} WHERE user_name = #{userName}";

	final String DELETE = "DELETE FROM cucgp.`student` WHERE user_name = #{userName}";

	@Insert(INSERT)
	public Integer insert(Student student);

	@Select(SELECT_BY_USERNAME)
	public Student selectByUserName(String userName);

	@Update(UPDATE)
	public Integer update(Student student);

	@Delete(DELETE)
	public Integer delete(Student student);

}
