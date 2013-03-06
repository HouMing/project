package name.hm.test.unit;

import java.util.List;

import name.hm.jpa.GroupMapper;
import name.hm.jpa.UserMapper;
import name.hm.pojo.Group;
import name.hm.pojo.User;
import name.hm.pojo.User.VALID;
import name.hm.test.BaseTestCase;
import name.hm.test.integration.RoleIntegrationTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserUnitTest extends BaseTestCase
{
	public static Integer USER_ID = 0;
	public static String USER_NAME = "TestUser";
	public static String USER_NAMEC = "TestUserC";
	public static String USER_HOME = "/" + USER_NAME + "/";
	public static String PASSWORD = "123456";
	static public User.VALID USER_VALID = User.getValid("valid");
	static public User.VALID USER_INVALID = User.getValid("invalid");

	static Group group = null;

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
		try {
			logger.info("start UserUnitTest");
			GroupUnitTest unitGroup = new GroupUnitTest();
			unitGroup.create();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void afterTest()
	{
		try {
			GroupUnitTest unitGroup = new GroupUnitTest();
			unitGroup.delete();
			logger.info("finish UserUnitTest");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0305
	public void create()
	{
		try {
			Integer ret;
			openTestSession();
			User user = new User(USER_ID, USER_NAME, PASSWORD, USER_HOME, USER_VALID,
					GroupUnitTest.GROUP_ID);
			ret = userMapper.insert(user);
			if (ret == 1) {
				logger.info("create OK! : " + user);
			} else {
				logger.error("create failed : " + user);
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
			User user = userMapper.selectByUserId(USER_ID);
			User user2 = userMapper.selectByUserName(USER_NAME);
			List<User> l = userMapper.selectByValid(UserUnitTest.USER_VALID);
			List<User> l2 = userMapper.selectByValid(UserUnitTest.USER_INVALID);
			List<User> l3 = userMapper.selectByGroupId(GroupUnitTest.GROUP_ID);
			se.commit();
			if (user != null) {
				logger.info("selectByUserId OK! : " + user);
			} else {
				logger.error("selectByUserId failed : " + user);
			}

			if (user2 != null) {
				logger.info("selectByUserName OK! : " + user2);
			} else {
				logger.error("selectByUserName failed : " + user2);
			}

			if (l.size() > 0 || l2.size() > 0) {
				logger.info("selectByValid OK!");
			} else {
				logger.error("selectByValid failed");
			}

			if (l3.size() > 0) {
				logger.info("selectByGroupId OK!");
			} else {
				logger.error("selectByGroupId failed");
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
			User user = userMapper.selectByUserId(USER_ID);
			logger.info("before update : " + user);
			user.setUserName(USER_NAMEC);
			error = userMapper.update(user);
			se.commit();
			if (error == 1) {
				user = userMapper.selectByUserId(USER_ID);
				se.commit();
				logger.info("update OK! : " + user);
			} else {
				user = userMapper.selectByUserId(USER_ID);
				se.commit();
				logger.error("update failed : " + user);
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
			Integer error;
			openTestSession();
			User user = userMapper.selectByUserId(USER_ID);
			se.commit();
			error = userMapper.delete(user);
			se.commit();
			if (error == 1) {
				logger.info("delete OK! : " + user);
			} else {
				logger.error("delete failed : " + user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
