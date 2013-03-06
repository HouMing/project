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
	public static Integer USER_ID = null;
	public static String USER_NAME = "TestUser";
	public static String USER_NAMEC = "TestUserC";
	public static String USER_HOME = "/" + USER_NAME + "/";

	public static Integer USER_ID1 = null;
	public static String USER_NAME1 = "TestUser1";
	public static String USER_HOME1 = "/" + USER_NAME1 + "/";

	public static Integer USER_ID2 = null;
	public static String USER_NAME2 = "TestUser2";
	public static String USER_HOME2 = "/" + USER_NAME2 + "/";

	public static String PASSWORD = "123456";
	static public User.VALID USER_VALID = User.getValid("valid");
	static public User.VALID USER_INVALID = User.getValid("invalid");

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
			Integer error;
			openTestSession();
			User user = new User(null, USER_NAME, PASSWORD, USER_HOME, USER_VALID,
					GroupUnitTest.GROUP_ID);
			User user1 = new User(null, USER_NAME1, PASSWORD, USER_HOME1, USER_VALID,
					GroupUnitTest.GROUP_ID);
			User user2 = new User(null, USER_NAME2, PASSWORD, USER_HOME2, USER_VALID,
					GroupUnitTest.GROUP_ID);
			error = userMapper.insert(user);
			error = userMapper.insert(user1);
			error = userMapper.insert(user2);
			if (error == 1) {
				logger.info("create OK!\n" + user + user1 + user2);
			} else {
				logger.error("create failed : " + user + user1 + user2);
			}
			se.commit();
			USER_ID = user.getUserId();
			USER_ID1 = user1.getUserId();
			USER_ID2 = user2.getUserId();
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
				logger.info("selectByUserId OK!\n" + user);
			} else {
				logger.error("selectByUserId failed\n" + user);
			}

			if (user2 != null) {
				logger.info("selectByUserName OK!\n" + user2);
			} else {
				logger.error("selectByUserName failed\n" + user2);
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
			User user1 = userMapper.selectByUserId(USER_ID1);
			User user2 = userMapper.selectByUserId(USER_ID2);
			se.commit();
			error = userMapper.delete(user);
			error = userMapper.delete(user1) & error;
			error = userMapper.delete(user2) & error;
			se.commit();
			user = userMapper.selectByUserId(USER_ID);
			user1 = userMapper.selectByUserId(USER_ID1);
			user2 = userMapper.selectByUserId(USER_ID2);
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + user + user1 + user2);
			} else {
				logger.error("delete failed\n" + user + user1 + user2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
