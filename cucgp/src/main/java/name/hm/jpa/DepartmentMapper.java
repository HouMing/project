package name.hm.jpa;

import java.util.LinkedList;

import name.hm.pojo.Department;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

// TODO continue from here
public interface DepartmentMapper extends Mapper
{
	final String INSERT = "INSERT INTO cucgp.`department` (department_name) VALUES (#{departmentName})";
	final String SELECT_ALL = "SELECT * FROM cucgp.`department`";
	final String DELETE = "DELETE FROM cucgp.`department` WHERE department_name = #{departmentName}";
	final String UPDATE = "UPDATE cucgp.`department` SET department_name = #{departmentName} where department_id = #{departmentId}";

	@Select(SELECT_ALL)
	@Results(value = { @Result(property = "departmentName", column = "department_name") })
//	@SelectKey(statement = "SELECT MAX(department_id) AS department_id FROM cucgp.`department`", before = false, keyProperty = "departmentId", resultType = Integer.class)
	@Options(useGeneratedKeys = true, keyProperty = "departmentId", keyColumn = "department_id")
	LinkedList<Department> selectAll();

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "departmentId", keyColumn = "department_id")
	Integer insert(Department department);

	@Delete(DELETE)
	Integer delete(Department department);

	@Update(UPDATE)
	Integer update(Department department);
}
