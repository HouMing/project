package name.hm.jpa;

import java.util.LinkedList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import name.hm.pojo.Teacher;

public interface TeacherMapper extends Mapper
{
	final String INSERT = "INSERT INTO cucgp.`teacher` " +
			                  "(user_name, teacher_name, telephone, email, " +
			                  "weibo, introduction, department_name, title_name)" +
			                  "VALUES " +
			                  "(#{userName}, #{teacherName}, #{telephone}, #{email}, " +
			                  "#{weibo}, #{introduction}, #{departmentName}, #{titleName})";
	
	final String SELECT_BY_USER_NAME = "SELECT * FROM cucgp.`teacher` where user_name = #{param1} ORDER BY user_name ASC";
	final String SELECT_BY_DEPARTMENT_NAME = "SELECT * FROM cucgp.`teacher` where department_name = #{param1} ORDER BY user_name ASC";
	final String SELECT_BY_TITLE_NAME = "SELECT * FROM cucgp.`teacher` where title_name = #{param1} ORDER BY user_name ASC";
	
	final String UPDATE ="UPDATE cucgp.`teacher` SET teacher_name = #{teacherName}, telephone = #{telephone},"+
			                 "email = #{email}, weibo = #{weibo}, introduction = #{introduction}, " +
			                 "department_name = #{departmentName}, title_name = #{titleName}";
	final String DELETE = "DELETE FROM cucgp.`teacher` WHERE user_name = #{userName}";
	
	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "userName", keyColumn = "user_name")
	public Integer insert(Teacher teacher);
	
	@Select(SELECT_BY_USER_NAME)
	@Results(value = {
	 @Result(property = "userName", column = "user_name"),
	 @Result(property = "teacherName", column = "teacher_name"),
	 @Result(property = "telephone", column = "telephone"),
	 @Result(property = "email", column = "email"),
	 @Result(property = "weibo", column = "weibo"),
	 @Result(property = "introduction", column = "introduction"),
	 @Result(property = "departmentName", column = "department_name"),
	 @Result(property = "titleName", column = "title_name")
	})
	public Teacher selectByUserName(String userName);
	
	@Select(SELECT_BY_DEPARTMENT_NAME)
	@Results(value = {
			 @Result(property = "userName", column = "user_name"),
			 @Result(property = "teacherName", column = "teacher_name"),
			 @Result(property = "telephone", column = "telephone"),
			 @Result(property = "email", column = "email"),
			 @Result(property = "weibo", column = "weibo"),
			 @Result(property = "introduction", column = "introduction"),
			 @Result(property = "departmentName", column = "department_name"),
			 @Result(property = "titleName", column = "title_name")
			})
	public LinkedList<Teacher> selectByDepartmentName(String departmentName);
	
	@Select(SELECT_BY_TITLE_NAME)
	@Results(value = {
			 @Result(property = "userName", column = "user_name"),
			 @Result(property = "teacherName", column = "teacher_name"),
			 @Result(property = "telephone", column = "telephone"),
			 @Result(property = "email", column = "email"),
			 @Result(property = "weibo", column = "weibo"),
			 @Result(property = "introduction", column = "introduction"),
			 @Result(property = "departmentName", column = "department_name"),
			 @Result(property = "titleName", column = "title_name")
			})
	public LinkedList<Teacher> selectByTitleName(String titleName);
	
	@Update(UPDATE)
	public Integer update(Teacher teacher);
	
	@Delete(DELETE)
	public Integer delete(Teacher teacher);
}