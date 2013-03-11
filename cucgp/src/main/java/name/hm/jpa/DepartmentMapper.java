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

public interface DepartmentMapper extends Mapper
{
	final String INSERT = "INSERT INTO cucgp.`department` (department_name) VALUES (#{departmentName})";
	
	final String SELECT_ALL = "SELECT * FROM cucgp.`department` ORDER BY department_id ASC";
	final String SELECT_BY_ID = "SELECT * FROM cucgp.`department` WHERE department_id = #{param1}";
	final String SELECT_BY_NAME = "SELECT * FROM cucgp.`department` WHERE department_name = #{param1}";
	
	final String UPDATE = "UPDATE cucgp.`department` SET department_name = #{departmentName} where department_id = #{departmentId}";
	final String DELETE = "DELETE FROM cucgp.`department` WHERE department_name = #{departmentName}";

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "departmentId", keyColumn = "department_id")
	Integer insert(Department department);
	
	@Select(SELECT_ALL)
	@Results(value = {
			@Result(property = "departmentId", column = "department_id"),
			@Result(property = "departmentName", column = "department_name") 
			})
	LinkedList<Department> selectAll();
	
	@Select(SELECT_BY_ID)
	@Results(value = {
			@Result(property = "departmentId", column = "department_id"),
			@Result(property = "departmentName", column = "department_name") 
			})
	Department selectByDepartmentId(Integer departmentId);

	@Select(SELECT_BY_NAME)
	@Results(value = {
			@Result(property = "departmentId", column = "department_id"),
			@Result(property = "departmentName", column = "department_name") 
			})
	Department selectByDepartmentName(String departmentName);
	
	@Delete(DELETE)
	Integer delete(Department department);

	@Update(UPDATE)
	Integer update(Department department);

}
