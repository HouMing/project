package name.hm.jpa;

import java.util.LinkedList;

import name.hm.pojo.Title;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TitleMapper extends Mapper
{
	final String INSERT = "INSERT INTO cucgp.`title` (title_name) VALUES (#{titleName})";
	final String SELECT_ALL = "SELECT * FROM cucgp.`title`";
	final String DELETE = "DELETE FROM cucgp.`title` WHERE title_name = #{titleName}";
	final String UPDATE = "UPDATE cucgp.`title` SET title_name = #{titleName}";
	
	@Select(SELECT_ALL)
	@Results(value = {
	@Result(property = "titleName", column = "title_name")
	})
	LinkedList<Title> selectAll();

  @Insert(INSERT)
  Integer insert(Title title);
  
  @Delete(DELETE)
  Integer delete(Title title);
  
  @Update(UPDATE)
  Integer update(Title title);
}
