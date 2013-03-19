package name.hm.test.unit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import name.hm.jpa.RoleMapper;
import name.hm.m.Role;
import name.hm.test.BaseTestCase;
import name.hm.test.integration.RoleIntegrationTest;

// PASS UNIT #0306
//TODO 3 PASS, Upgrade - task : #0310
public class RoleUnitTest extends BaseTestCase
{
	public static Role R_Admin = null;
	protected static final String R_Admin_NAME = "管理员";
	protected static final String ROLENAME_ADMINC = "管理员改";

	public static Role R_Teacher = null;
 	protected static final String R_Teacher_NAME = "教师";

 	public static Role R_Student = null;
	protected static final String R_Student_NAME = "学生";
	
	@Test
	public void test()
	{
		beforeTest();
		create();
//		read();
//		update();
		delete();
		afterTest();
	}

	protected void beforeTest()
	{
		logger.info("start RoleUnitTest");
		GroupUnitTest groupUnit = new GroupUnitTest();
		groupUnit.create();
	}

	protected void afterTest()
	{
		clean();
		GroupUnitTest groupUnit = new GroupUnitTest();
		groupUnit.clean();
		logger.info("finish RoleUnitTest");
	}

	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			ArrayList<Integer> xizhuren = new ArrayList<Integer>();
			ArrayList<Integer> teacher = new ArrayList<Integer>();
			ArrayList<Integer> student = new ArrayList<Integer>();
			xizhuren.add(GroupUnitTest.G_Admin.getGroupId());
			xizhuren.add(GroupUnitTest.G_Teacher.getGroupId());
			teacher.add(GroupUnitTest.G_Teacher.getGroupId());
			student.add(GroupUnitTest.G_Student.getGroupId());
			R_Admin = new Role(R_Admin_NAME);
			R_Admin.setGroups(xizhuren);
			R_Teacher = new Role(R_Teacher_NAME);
			R_Teacher.setGroups(teacher);
			R_Student = new Role(R_Student_NAME);
			R_Student.setGroups(student);
			error &=
			roleMapper.insert(R_Admin);
			roleMapper.insertList(R_Admin);
			error &=
			roleMapper.insert(R_Teacher);
			roleMapper.insertList(R_Teacher);
			error &=
			roleMapper.insert(R_Student);
			roleMapper.insertList(R_Student);
			se.commit();
			if (error == 1) {
				logger.info("create Role OK!\n" + R_Admin + "\n" + R_Teacher + "\n" + R_Student);
			} else {
				logger.info("create Role failed\n" + R_Admin + "\n" + R_Teacher + "\n" + R_Student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0306
	public void read()
	{
		try {
			openTestSession();
			Role admin = roleMapper.selectByRoleId(R_Admin.getRoleId());
			se.commit();
			if (admin != null) {
				logger.info("selectByRoleId OK!\n" + admin);
			} else {
				logger.error("selectByRoleId failed\n" + R_Admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0306
	public void update()
	{
//		try {
//			Integer error = 1;
//			openTestSession();
//
//			logger.info("before update:\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER);
//			ROLE_ADMIN.setRoleName(ROLENAME_ADMINC);
//			ROLE_TEACHER.setValid(ROLE_VALID);
//			error = roleMapper.update(ROLE_ADMIN);
//			error = roleMapper.update(ROLE_TEACHER);
//			se.commit();
//
//			ROLE_ADMIN = roleMapper.selectByRoleId(ROLE_ADMIN.getRoleId());
//			ROLE_TEACHER = roleMapper.selectByRoleId(ROLE_TEACHER.getRoleId());
//			se.commit();
//
//			if (error == 1) {
//				logger.info("update OK!\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER);
//			} else {
//				logger.error("update failed\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeTestSession();
//		}
	}

	// PASS CELL #0306
	public void delete()
	{
//		try {
//			Integer error = 1;
//			openTestSession();
//			logger.info("before delete\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER + "\n" + ROLE_STUDENT);
//			error = roleMapper.delete(ROLE_ADMIN);
//			error = roleMapper.delete(ROLE_TEACHER);
//			error = roleMapper.delete(ROLE_STUDENT);
//			se.commit();
//			if (error == 1) {
//				logger.info("delete OK!\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER + "\n" + ROLE_STUDENT);
//			} else {
//				logger.error("delete failed\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER + "\n" + ROLE_STUDENT);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeTestSession();
//		}
	}

	public void clean()
	{
		try {
			Integer error = 1;
			openTestSession();
			ArrayList<Role> l = roleMapper.selectAll();
			se.commit();
			for (Role tmp : l) {
				error &= roleMapper.delete(tmp);
				se.commit();
			}
			Assert.assertEquals(new Integer(1), error);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
