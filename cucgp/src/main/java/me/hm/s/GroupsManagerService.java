package me.hm.s;

import java.util.ArrayList;
import java.util.List;

import me.hm.jpa.GroupDao;
import me.hm.m.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class GroupsManagerService {

	@Autowired
	GroupDao groupDao;

	public List<Group> readGroups() {
		List<Group> groups = new ArrayList<Group>();
		groups.addAll(groupDao.readAll());
		return groups;
	}
	
	public void insertGroup(Group group) {
		groupDao.create(group);
	}

	public void updateGroup(Group group) {
        groupDao.write(group);
	}

	public void deleteGroup(Group group) {
    // TODO   Integer ret =
    groupDao.delete(group);		
	}
	
	public void syncGroup(Group group) {
	  groupDao.read(group);
	}
}