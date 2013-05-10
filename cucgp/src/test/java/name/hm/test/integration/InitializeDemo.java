package name.hm.test.integration;

import java.util.ArrayList;//{{{

import org.junit.Test;

import name.hm.test.BaseTestCase;
import name.hm.m.*;
import org.apache.commons.codec.digest.*;//}}}
=======

public class InitializeDemo extends BaseTestCase
{

	@Test
	public void in()
	{
		try {
			openTestSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User u_admin = new User("1000", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1000") + "/", null);
		User u_assistant = new User("1001", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1001") + "/", null);
		User u_teacher = new User("1900", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1900") + "/", null);
		User u_student = new User("200910013400", DigestUtils.md5Hex("123456"), "/"+ DigestUtils.md5Hex("200910013400") +"/", null);
    userMapper.insert(u_admin);
    userMapper.insert(u_assistant);
	  userMapper.insert(u_teacher);
	  userMapper.insert(u_student);
	  se.commit();
	  
	  Group g_admin = new Group("系主任");
	  Group g_assistant = new Group("教秘");
	  Group g_teacher = new Group("教师");
	  Group g_student = new Group("学生");
	  groupMapper.insert(g_admin);
	  groupMapper.insert(g_assistant);
	  groupMapper.insert(g_teacher);
	  groupMapper.insert(g_student);
	  se.commit();
	  
	  u_admin.setGroupId(g_admin.getGroupId());
	  u_assistant.setGroupId(g_assistant.getGroupId());
	  u_teacher.setGroupId(g_teacher.getGroupId());
	  u_student.setGroupId(g_student.getGroupId());
	  userMapper.update(u_admin);
	  userMapper.update(u_assistant);
	  userMapper.update(u_teacher);
	  userMapper.update(u_student);
	  se.commit();
	  
	  Role r_admin = new Role("管理员");
	  Role r_teacher = new Role("教师");
	  Role r_student = new Role("学生");
	  Role r_guest = new Role("访客");
	  roleMapper.insert(r_admin);
	  roleMapper.insert(r_teacher);
	  roleMapper.insert(r_student);
	  roleMapper.insert(r_guest);
	  se.commit();
	  g_admin.getRoles().add(r_admin.getRoleId());
	  g_admin.getRoles().add(r_teacher.getRoleId());
	  g_admin.getRoles().add(r_guest.getRoleId());
	  
	  g_assistant.getRoles().add(r_admin.getRoleId());
	  g_assistant.getRoles().add(r_guest.getRoleId());
	  
	  g_teacher.getRoles().add(r_teacher.getRoleId());
	  g_teacher.getRoles().add(r_guest.getRoleId());
	  
	  g_student.getRoles().add(r_student.getRoleId());
	  g_student.getRoles().add(r_guest.getRoleId());
	  
	  ArrayList<Integer> tmp = null;
	  tmp = g_admin.getRoles();
	  for(Integer role : tmp) {
	    groupMapper.setRoles(g_admin.getGroupId(), role);
	  }
	  tmp = g_assistant.getRoles();
	  for(Integer role : tmp) {
	    groupMapper.setRoles(g_assistant.getGroupId(), role);
	  }
	  tmp = g_teacher.getRoles();
	  for(Integer role : tmp) {
	    groupMapper.setRoles(g_teacher.getGroupId(), role);
	  }
	  tmp = g_student.getRoles();
	  for(Integer role : tmp) {
	    groupMapper.setRoles(g_student.getGroupId(), role);
	  }
	  se.commit();

	  Action ac1 = new Action("logout","logout","退出",r_guest.getRoleId());
	  Action ac2 = new Action("manageUser", "admin/manageUser", "管理用户",r_admin.getRoleId());
	  Action ac3 = new Action("manageStudent","admin/manageStudent","管理学生信息",r_admin.getRoleId());
	  Action ac4 = new Action("manageTeacher", "admin/manageTeacher", "管理教师信息", r_admin.getRoleId());
	  
	  Action ac5 = new Action("manageTInfo","teacher/manageTeacherInfo","管理教师信息",r_teacher.getRoleId());
	  Action ac6 = new Action("manageSInfo","teacher/manageStudentInfo","管理学生信息",r_student.getRoleId());
	  actionMapper.insert(ac1);
	  actionMapper.insert(ac2);
	  actionMapper.insert(ac3);
	  actionMapper.insert(ac4);
	  actionMapper.insert(ac5);
	  actionMapper.insert(ac6);
	  se.commit();
	  
	}

	// @Test
	void out()
	{
    
	}
}
