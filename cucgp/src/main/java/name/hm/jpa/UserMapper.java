package name.hm.jpa;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import name.hm.pojo.User;

public interface UserMapper extends Mapper
{

	final String SELECT_BY_USERID = "SELECT * FROM cucgp.`user` WHERE user_id = #{param1}";
	final String SELECT_BY_USERNAME = "SELECT * FROM cucgp.`user` WHERE user_name = #{param1}";
	final String SELECT_BY_USERVALID = "SELECT * FROM cucgp.`user` WHERE valid = #{param1}";
	final String SELECT_BY_GROUPID = "SELECT * FROM cucgp.`user` WHERE group_id = #{param1}";
	final String INSERT_USER = "INSERT INTO " +
			                   "cucgp.`user` (user_id, group_id, user_name, password, valid, user_home)" +
			                   " VALUES " +
			                   "(#{userId}, #{groupId}, #{userName}, #{password}, #{valid}, #{userHome})";
	final String UPDATE = "UPDATE cucgp.`user` " +
			              "SET user_name = #{userName}, password = #{password}, user_home = #{userHome}, " +
			              "group_id = #{groupId}, valid = #{valid}" +
			              "WHERE user_id = #{userId}";
	final String DELETE = "DELETE FROM cucgp.`user` WHERE user_id = #{userId}";
	
	@Select(SELECT_BY_USERID)
	@Results(value={
			@Result(property="userId", column="user_id"),
			@Result(property="groupId", column="group_id"),
			@Result(property="userName", column="user_name"),
			@Result(property="password", column="password"),
			@Result(property="valid", column="valid"),
			@Result(property="userHome", column="user_home")
	})
	User selectByUserId(Integer userId);
		
	@Select(SELECT_BY_USERNAME)
	@Results(value={
			@Result(property="userId", column="user_id"),
			@Result(property="groupId", column="group_id"),
			@Result(property="userName", column="user_name"),
			@Result(property="password", column="password"),
			@Result(property="valid", column="valid"),
			@Result(property="userHome", column="user_home")
	})
	User selectByUserName(String userName);
		
	@Select(SELECT_BY_USERVALID)
	@Results(value={
			@Result(property="userId", column="user_id"),
			@Result(property="groupId", column="group_id"),
			@Result(property="userName", column="user_name"),
			@Result(property="password", column="password"),
			@Result(property="valid", column="valid"),
			@Result(property="userHome", column="user_home")
	})
	List<User> selectByValid(String valid);
	
	@Select(SELECT_BY_GROUPID)
	@Results(value={
			@Result(property="userId", column="user_id"),
			@Result(property="groupId", column="group_id"),
			@Result(property="userName", column="user_name"),
			@Result(property="password", column="password"),
			@Result(property="valid", column="valid"),
			@Result(property="userHome", column="user_home")
	})
	List<User> selectByGroupId(Integer groupId);

	@Insert(INSERT_USER)
	Integer insert(User cellTest);

  	@Update(UPDATE)
	Integer update(User user);
	
    @Delete(DELETE)
	Integer delete(User user);


}
