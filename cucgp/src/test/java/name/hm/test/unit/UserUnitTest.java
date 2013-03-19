package name.hm.test.unit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import name.hm.jpa.GroupMapper;
import name.hm.jpa.UserMapper;
import name.hm.m.Group;
import name.hm.m.Title;
import name.hm.m.User;
import name.hm.m.User.Valid;
import name.hm.test.BaseTestCase;
import name.hm.test.integration.RoleIntegrationTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

// TODO 2 PASS, Upgrade - task : #0310
public class UserUnitTest extends BaseTestCase
{
	public static User U_Admin = null;
	protected static final String U_Admin_NAME = "1000";
	protected static final String ADMIN_NAMEC = "1001";
	protected static final String ADMIN_HOME = "/" + U_Admin_NAME + "/";

	public static User U_Teacher = null;
	protected static final String U_Teacher_NAME = "1900";
	protected static final String TEACHER_HOME = "/" + U_Teacher_NAME + "/";

	public static User U_Student = null;
	protected static final String U_Student_NAME= "200910013400";
	protected static final String STUDENT_HOME = "/" + U_Student_NAME + "/";

	protected static String PASSWORD = "123456";
	protected static final User.Valid USER_VALID = User.ConstValid;
	protected static final User.Valid USER_INVALID = User.ConstInvalid;

	@Test
	public void test()
	{
		try {
			beforeTest();
			create();
//			read();
//			update();
			delete();
			afterTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void beforeTest()
	{
		logger.info("start UserUnitTest");
		GroupUnitTest unitGroup = new GroupUnitTest();
		unitGroup.create();
	}

	public void afterTest()
	{
		GroupUnitTest unitGroup = new GroupUnitTest();
		unitGroup.clean();
		clean();
		logger.info("finish UserUnitTest");
	}

	// PASS #0305
	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			U_Admin = new User(U_Admin_NAME, PASSWORD, ADMIN_HOME, USER_VALID, GroupUnitTest.G_Teacher.getGroupId());
			U_Teacher = new User(U_Teacher_NAME, PASSWORD, TEACHER_HOME, USER_VALID, GroupUnitTest.G_Admin.getGroupId());
			U_Student = new User(U_Student_NAME, PASSWORD, STUDENT_HOME, USER_VALID, GroupUnitTest.G_Student.getGroupId());
			error &= userMapper.insert(U_Admin);
			error &= userMapper.insert(U_Teacher);
			error &= userMapper.insert(U_Student);
			se.commit();
			if (error == 1) {
				logger.info("create OK!\n" + U_Admin + "" +"\n"+ U_Teacher +"\n"+ U_Student);
			} else {
				logger.error("create failed\n" + U_Admin + "" +"\n"+ U_Teacher +"\n"+ U_Student);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0305
	public void read()
	{
//		try {
//			openTestSession();
//			User admin = userMapper.selectByUserId(U_Admin.getUserId());
//			User teacher = userMapper.selectByUserName(U_Teacher.getUserName());
//			List<User> l = userMapper.selectByValid(UserUnitTest.USER_VALID);
//			List<User> l2 = userMapper.selectByValid(UserUnitTest.USER_INVALID);
//			List<User> l3 = userMapper.selectByGroupId(GroupUnitTest.G_Admin.getGroupId());
//			se.commit();
//			if (admin != null) {
//				logger.info("selectByUserId OK!\n" + admin);
//			} else {
//				logger.error("selectByUserId failed\n" + U_Admin);
//			}
//
//			if (teacher != null) {
//				logger.info("selectByUserName OK!\n" + teacher);
//			} else {
//				logger.error("selectByUserName failed\n" + teacher);
//			}
//
//			if (l.size() > 0 || l2.size() > 0) {
//				logger.info("selectByValid OK!\n" + l + l2);
//			} else {
//				logger.error("selectByValid failed\n" + l + l2);
//			}
//			
//			if (l3.size() > 0) {
//				logger.info("selectByGroupId OK!\n" + l3);
//			} else {
//				logger.error("selectByGroupId failed\n" + l3);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeTestSession();
//		}
	}

	// PASS #0305
	public void update()
	{
//		try {
//			Integer error;
//			openTestSession();
//			User admin = userMapper.selectByUserId(U_Admin.getUserId());
//			logger.info("before update\n" + admin);
//			admin.setUserName(ADMIN_NAMEC);
//			error = userMapper.update(admin);
//			se.commit();
//			admin = userMapper.selectByUserId(U_Admin.getUserId());
//			se.commit();
//			if (error == 1) {
//				logger.info("update OK!\n" + admin);
//			} else {
//				logger.error("update failed\n" + admin);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeTestSession();
//		}
	}

	// PASS #0305
	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
			error &= userMapper.delete(U_Admin);
			error &= userMapper.delete(U_Teacher);
			error &= userMapper.delete(U_Student);
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + U_Admin +"\n"+ U_Teacher +"\n"+ U_Student);
			} else {
				logger.error("delete failed\n" + U_Admin +"\n"+ U_Teacher +"\n"+ U_Student);
				Assert.fail("delete failed\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void clean()
	{
		try {
			Integer error = 1;
			openTestSession();
			ArrayList<User> users = userMapper.selectAll();
			se.commit();
			for(User user: users) {
				error &= userMapper.delete(user);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

}
