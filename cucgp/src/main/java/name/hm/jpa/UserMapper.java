package name.hm.jpa;

import java.util.ArrayList;
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
import org.springframework.stereotype.Repository;

import name.hm.m.Group;
import name.hm.m.User;


public interface UserMapper extends Mapper
{
	User selectByUserName(String userName);
	
	ArrayList<User> selectAll();	
	
	Integer insert(User cellTest);
	
	@Deprecated
	Integer update(User user);

	Integer delete(User user);
	
	/*
	final String INSERT_USER = "INSERT INTO "
			+ "cucgp.`user` (user_id, group_id, user_name, password, valid, user_home)"
			+ " VALUES "
			+ "(#{userId}, #{groupId}, #{userName}, #{password}, #{valid}, #{userHome})";
	
	final String SELECT_BY_USERID = "SELECT * FROM cucgp.`user` WHERE user_id = #{param1}";
	final String SELECT_BY_USERVALID = "SELECT * FROM cucgp.`user` WHERE valid = #{param1} ORDER BY user_id ASC";
	final String SELECT_BY_GROUPID = "SELECT * FROM cucgp.`user` WHERE group_id = #{param1} ORDER BY user_id ASC";
	
	final String UPDATE = "UPDATE cucgp.`user` "
			+ "SET user_name = #{userName}, password = #{password}, user_home = #{userHome}, "
			+ "group_id = #{groupId}, valid = #{valid}" + "WHERE user_id = #{userId}";
	
	final String DELETE = "DELETE FROM cucgp.`user` WHERE user_id = #{userId}";

	final String LAST_INSERT_ID = "SELECT MAX(user_id) FROM cucgp.`user`";
  */
 



}
