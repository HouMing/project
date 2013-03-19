package name.hm.jpa;

import java.util.ArrayList;
import java.util.LinkedList;

import name.hm.m.Group;

public interface GroupMapper extends Mapper
{
	final String SELECT_BY_GROUPNAME = "SELECT * FROM `group` WHERE group_name = #{param1}";
	final String SELECT_BY_GROUPVALID = "SELECT * FROM `group` WHERE valid = #{param1}";
	final String UPDATE = "UPDATE `group` " +
			              "SET group_name = #{groupName}, valid = #{valid} " +
			              "WHERE group_id = #{groupId}";
	final String DELETE = "DELETE FROM `group` WHERE group_id = #{groupId}";
	
	Integer insert(Group group);
	
	Group selectByGroupId(Integer groupId);
	
	ArrayList<Group> selectAll();
	
	@Deprecated
	Integer update(Group group);
	
	Integer delete(Group group);

}
