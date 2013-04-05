package name.hm.jpa;

import java.util.ArrayList;
import java.util.LinkedList;

import name.hm.m.Group;

public interface GroupMapper extends Mapper
{
	ArrayList<Integer> getRoles(Integer groupId);
	
	Integer setRoles(Integer groupId, Integer roleId);
	
	Integer insert(Group group);
	
	Group selectByGroupId(Integer groupId);
	
	ArrayList<Group> selectAll();
	
	Integer update(Group group);
	
	Integer delete(Group group);

}
