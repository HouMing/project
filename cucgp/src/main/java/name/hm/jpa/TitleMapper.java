package name.hm.jpa;

import java.util.LinkedList;

import name.hm.pojo.Title;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TitleMapper extends Mapper
{
	final String INSERT = "INSERT INTO cucgp.`title` (title_name) VALUES (#{titleName})";
	final String SELECT_ALL = "SELECT * FROM cucgp.`title`";
	final String DELETE = "DELETE FROM cucgp.`title` WHERE title_name = #{titleName}";
	final String UPDATE = "UPDATE cucgp.`title` SET title_name = #{titleName} WHERE title_id = #{titleId}";

	@Select(SELECT_ALL)
	@Results(value = { 
			@Result(property = "titleId", column = "title_id"),
			@Result(property = "titleName", column = "title_name") 
	})
	@Options(useGeneratedKeys = true, keyProperty = "titleId", keyColumn = "title_id")
	LinkedList<Title> selectAll();

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "titleId", keyColumn = "title_id")
	Integer insert(Title title);

	@Delete(DELETE)
	@Options(useGeneratedKeys = true, keyProperty = "titleId", keyColumn = "title_id")
	Integer delete(Title title);

	@Update(UPDATE)
	@Options(useGeneratedKeys = true, keyProperty = "titleId", keyColumn = "title_id")
	Integer update(Title title);
}
