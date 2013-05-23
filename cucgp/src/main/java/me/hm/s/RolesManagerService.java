package me.hm.s;

import java.util.ArrayList;
import java.util.List;

import me.hm.jpa.RoleDao;
import me.hm.m.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class RolesManagerService {

	@Autowired
	RoleDao roleDao;

	public List<Role> readRoles() {
		List<Role> roles = new ArrayList<Role>();
		roles.addAll(roleDao.readAll());
		return roles;
	}

	public void insertRole(Role role) {
	  //TODO Integer ret = 
	  roleDao.create(role);
	}

	public void updateRole(Role role) {
       // TODO Integer ret = 
       roleDao.write(role);
	}
	
	public void syncRole(Role role) {
		roleDao.read(role);
	}

	public void deleteRole(Role role) {
       roleDao.delete(role);		
	}

}