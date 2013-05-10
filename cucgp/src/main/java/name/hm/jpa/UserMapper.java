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
	
	Integer update(User user);

	Integer delete(User user);


}
