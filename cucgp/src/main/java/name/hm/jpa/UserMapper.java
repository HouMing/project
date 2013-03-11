package name.hm.jpa;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import name.hm.pojo.User;

public interface UserMapper extends Mapper
{

	final String INSERT_USER = "INSERT INTO "
			+ "cucgp.`user` (user_id, group_id, user_name, password, valid, user_home)"
			+ " VALUES "
			+ "(#{userId}, #{groupId}, #{userName}, #{password}, #{valid}, #{userHome})";
	
	final String SELECT_BY_USERID = "SELECT * FROM cucgp.`user` WHERE user_id = #{param1}";
	final String SELECT_BY_USERNAME = "SELECT * FROM cucgp.`user` WHERE user_name = #{param1}";
	final String SELECT_BY_USERVALID = "SELECT * FROM cucgp.`user` WHERE valid = #{param1} ORDER BY user_id ASC";
	final String SELECT_BY_GROUPID = "SELECT * FROM cucgp.`user` WHERE group_id = #{param1} ORDER BY user_id ASC";
	
	final String UPDATE = "UPDATE cucgp.`user` "
			+ "SET user_name = #{userName}, password = #{password}, user_home = #{userHome}, "
			+ "group_id = #{groupId}, valid = #{valid}" + "WHERE user_id = #{userId}";
	
	final String DELETE = "DELETE FROM cucgp.`user` WHERE user_id = #{userId}";

	final String LAST_INSERT_ID = "SELECT MAX(user_id) FROM cucgp.`user`";
	
	@Select(SELECT_BY_USERID)
	@Results(value = { 
			@Result(property = "userId", column = "user_id"),
			@Result(property = "groupId", column = "group_id"),
			@Result(property = "userName", column = "user_name"),
			@Result(property = "password", column = "password"),
			@Result(property = "valid", column = "valid"),
			@Result(property = "userHome", column = "user_home") })
	User selectByUserId(Integer userId);

	@Select(SELECT_BY_USERNAME)
	@Results(value = { @Result(property = "userId", column = "user_id"),
			@Result(property = "groupId", column = "group_id"),
			@Result(property = "userName", column = "user_name"),
			@Result(property = "password", column = "password"),
			@Result(property = "valid", column = "valid"),
			@Result(property = "userHome", column = "user_home") })
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	User selectByUserName(String userName);

	@Select(SELECT_BY_USERVALID)
	@Results(value = { @Result(property = "userId", column = "user_id"),
			@Result(property = "groupId", column = "group_id"),
			@Result(property = "userName", column = "user_name"),
			@Result(property = "password", column = "password"),
			@Result(property = "valid", column = "valid"),
			@Result(property = "userHome", column = "user_home") })
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	LinkedList<User> selectByValid(User.Valid valid);

	@Select(SELECT_BY_GROUPID)
	@Results(value = { @Result(property = "userId", column = "user_id"),
			@Result(property = "groupId", column = "group_id"),
			@Result(property = "userName", column = "user_name"),
			@Result(property = "password", column = "password"),
			@Result(property = "valid", column = "valid"),
			@Result(property = "userHome", column = "user_home") })
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	LinkedList<User> selectByGroupId(Integer groupId);

	@Insert(INSERT_USER)
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	@SelectKey(before = false, keyProperty = "userId", resultType = Integer.class, statement = { "SELECT MAX(user_id) AS user_id FROM cucgp.`user`" })
	Integer insert(User cellTest);

	@Update(UPDATE)
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	Integer update(User user);

	@Delete(DELETE)
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	Integer delete(User user);

	@Select(LAST_INSERT_ID)
	Integer lastInsertId();

}
