package name.hm.jpa;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import name.hm.pojo.Student;

//TODO IMPL MAPPER
public interface StudentMapper
{
	final String INSERT = "";
	final String SELECT_BY_USERNAME = "";
	final String UPDATE = "";
	final String DELETE = "";

	@Insert(INSERT)
	public Integer insert(Student student);

	@Select(SELECT_BY_USERNAME)
	public Student selectByUserName(String userName);

	@Update(UPDATE)
	public Integer update(Student student);

	@Delete(DELETE)
	public Integer delete(Student student);

}
