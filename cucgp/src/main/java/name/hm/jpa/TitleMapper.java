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
	
	final String SELECT_ALL = "SELECT * FROM cucgp.`title` ORDER BY title_id ASC";
	final String SELECT_BY_ID = "SELECT * FROM cucgp.`title` WHERE title_id = #{param1}";
	
	final String DELETE = "DELETE FROM cucgp.`title` WHERE title_name = #{titleName}";
	final String UPDATE = "UPDATE cucgp.`title` SET title_name = #{titleName} WHERE title_id = #{titleId}";

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "titleId", keyColumn = "title_id")
	Integer insert(Title title);
	
	@Select(SELECT_ALL)
	@Results(value = { 
			@Result(property = "titleId", column = "title_id"),
			@Result(property = "titleName", column = "title_name") 
	})
	@Options(useGeneratedKeys = true, keyProperty = "titleId", keyColumn = "title_id")
	LinkedList<Title> selectAll();

	@Select(SELECT_BY_ID)
	@Results(value = { 
			@Result(property = "titleId", column = "title_id"),
			@Result(property = "titleName", column = "title_name") 
	})
	Title selectByTitleId(Integer titleId);

	@Delete(DELETE)
	@Options(useGeneratedKeys = true, keyProperty = "titleId", keyColumn = "title_id")
	Integer delete(Title title);

	@Update(UPDATE)
	@Options(useGeneratedKeys = true, keyProperty = "titleId", keyColumn = "title_id")
	Integer update(Title title);

}
