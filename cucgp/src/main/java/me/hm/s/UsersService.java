package me.hm.s;

import java.util.ArrayList;
import java.util.List;

import me.hm.jpa.UserDao;
import me.hm.m.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class UsersService {

	@Autowired
	UserDao userDao;
	
	public List<User> readUsers() {
		List<User> users = new ArrayList<User>();
		users.addAll(userDao.getAll());
		return users;
	}
	
	public void insertUser(User user) {
	  // TODO Integer ret = 
	  userDao.create(user);
	}

	public Integer updateUser(User t) {
       Integer ret = userDao.write(t);
       return ret;
	}

	public Integer deleteUser(User t) {
       return userDao.delete(t);		
	}

	public void syncGroup(User user) {
		userDao.sync(user);
	}
}