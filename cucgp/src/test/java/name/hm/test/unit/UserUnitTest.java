package name.hm.test.unit;

import java.util.LinkedList;
import java.util.List;

import name.hm.jpa.GroupMapper;
import name.hm.jpa.UserMapper;
import name.hm.pojo.Group;
import name.hm.pojo.Title;
import name.hm.pojo.User;
import name.hm.pojo.User.Valid;
import name.hm.test.BaseTestCase;
import name.hm.test.integration.RoleIntegrationTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

// TODO 2 PASS, Upgrade - task : #0310
public class UserUnitTest extends BaseTestCase
{
	public static User USER_ADMIN = null;
	protected static final String ADMIN_NAME = "1000";
	protected static final String ADMIN_NAMEC = "1001";
	protected static final String ADMIN_HOME = "/" + ADMIN_NAME + "/";

	public static User USER_TEACHER = null;
	protected static final String TEACHER_NAME = "1900";
	protected static final String TEACHER_HOME = "/" + TEACHER_NAME + "/";

	public static User USER_STUDENT = null;
	protected static final String STUDENT_NAME= "200910013400";
	protected static final String STUDENT_HOME = "/" + STUDENT_NAME + "/";

	protected static String PASSWORD = "123456";
	protected static final User.Valid USER_VALID = User.VALID;
	protected static final User.Valid USER_INVALID = User.INVALID;

	@Test
	public void test()
	{
		try {
			beforeTest();
			create();
			read();
			update();
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
			USER_ADMIN = new User(ADMIN_NAME, PASSWORD, ADMIN_HOME, USER_VALID, GroupUnitTest.G_Teacher.getGroupId());
			USER_TEACHER = new User(TEACHER_NAME, PASSWORD, TEACHER_HOME, USER_VALID, GroupUnitTest.GROUP_ADMIN.getGroupId());
			USER_STUDENT = new User(STUDENT_NAME, PASSWORD, STUDENT_HOME, USER_VALID, GroupUnitTest.G_Student.getGroupId());
			error = userMapper.insert(USER_ADMIN) & error;
			error = userMapper.insert(USER_TEACHER) & error;
			error = userMapper.insert(USER_STUDENT) & error;
			if (error == 1) {
				logger.info("create OK!\n" + USER_ADMIN + "" +"\n"+ USER_TEACHER +"\n"+ USER_STUDENT);
			} else {
				logger.error("create failed\n" + USER_ADMIN + "" +"\n"+ USER_TEACHER +"\n"+ USER_STUDENT);
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
		try {
			openTestSession();
			User admin = userMapper.selectByUserId(USER_ADMIN.getUserId());
			User teacher = userMapper.selectByUserName(USER_TEACHER.getUserName());
			List<User> l = userMapper.selectByValid(UserUnitTest.USER_VALID);
			List<User> l2 = userMapper.selectByValid(UserUnitTest.USER_INVALID);
			List<User> l3 = userMapper.selectByGroupId(GroupUnitTest.GROUP_ADMIN.getGroupId());
			se.commit();
			if (admin != null) {
				logger.info("selectByUserId OK!\n" + admin);
			} else {
				logger.error("selectByUserId failed\n" + USER_ADMIN);
			}

			if (teacher != null) {
				logger.info("selectByUserName OK!\n" + teacher);
			} else {
				logger.error("selectByUserName failed\n" + teacher);
			}

			if (l.size() > 0 || l2.size() > 0) {
				logger.info("selectByValid OK!\n" + l + l2);
			} else {
				logger.error("selectByValid failed\n" + l + l2);
			}
			
			if (l3.size() > 0) {
				logger.info("selectByGroupId OK!\n" + l3);
			} else {
				logger.error("selectByGroupId failed\n" + l3);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0305
	public void update()
	{
		try {
			Integer error;
			openTestSession();
			User admin = userMapper.selectByUserId(USER_ADMIN.getUserId());
			logger.info("before update\n" + admin);
			admin.setUserName(ADMIN_NAMEC);
			error = userMapper.update(admin);
			se.commit();
			admin = userMapper.selectByUserId(USER_ADMIN.getUserId());
			se.commit();
			if (error == 1) {
				logger.info("update OK!\n" + admin);
			} else {
				logger.error("update failed\n" + admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0305
	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
			error = userMapper.delete(USER_ADMIN) & error;
			error = userMapper.delete(USER_TEACHER) & error;
			error = userMapper.delete(USER_STUDENT) & error;
			se.commit();
			USER_ADMIN = userMapper.selectByUserId(USER_ADMIN.getUserId());
			USER_TEACHER = userMapper.selectByUserId(USER_TEACHER.getUserId());
			USER_STUDENT = userMapper.selectByUserId(USER_STUDENT.getUserId());
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + USER_ADMIN +"\n"+ USER_TEACHER +"\n"+ USER_STUDENT);
			} else {
				logger.error("delete failed\n" + USER_ADMIN +"\n"+ USER_TEACHER +"\n"+ USER_STUDENT);
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
			openTestSession();
			LinkedList<User> l = userMapper.selectByValid(User.VALID);
			LinkedList<User> l2 = userMapper.selectByValid(User.INVALID);
			se.commit();
			for (User tmp : l) {
				userMapper.delete(tmp);
			}
			for (User tmp : l2) {
				userMapper.delete(tmp);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

}
