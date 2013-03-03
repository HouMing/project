package name.hm.test.unit;

import java.util.List;

import name.hm.jpa.GroupMapper;
import name.hm.jpa.UserMapper;
import name.hm.pojo.Group;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;
import name.hm.test.integration.RoleIntegrationTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * test CRUD of User Table
 */
public class UserUnitTest extends BaseTestCase
{
	public static Integer USER_ID = 0;
	public static String USER_NAME = "1000";
	public static String USER_HOME = "/" + USER_NAME + "/";
	public static String PASSWORD = "123456";
	public static String USER_VALID = "valid";
	public static String USER_INVALID = "invalid";

	static Group group = null;

	@Test
	public void test()
	{
		try {
			beforeTest();
			insertUser();
			selectUser();
			updateUser();
			deleteUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void beforeTest()
	{
		try {
			logger.info("start UserUnitTest");
			openTestSession();
			group = new Group();
			group.setGroupId(GroupUnitTest.GROUP_ID);
			group.setGroupName(GroupUnitTest.GROUP_NAME);
			group.setValid(GroupUnitTest.GROUP_VALID);
			groupMapper.insert(group);
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void afterTest()
	{
		try {
			openTestSession();
			group.setGroupId(GroupUnitTest.GROUP_ID);
			group.setGroupName(GroupUnitTest.GROUP_NAME);
			group.setValid(GroupUnitTest.GROUP_VALID);
			groupMapper.delete(group);
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void insertUser()
	{
		try {
			openTestSession();
			logger.info("start");
			User user = new User();
			user.setUserId(USER_ID);
			user.setUserName(USER_NAME);
			user.setPassword(PASSWORD);
			user.setGroupId(GroupUnitTest.GROUP_ID);
			user.setValid(USER_VALID);
			user.setUserHome(USER_HOME);
			userMapper.insert(user);
			se.commit();
			logger.info("end");
		} catch (Exception e) {
			se.rollback();
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void selectUser()
	{
		try {
			openTestSession();
			logger.info("start");
			User user = userMapper.selectByUserId(USER_ID);
			User user2 = userMapper.selectByUserName(USER_NAME);
			List<User> luser = userMapper.selectByValid("valid");
			List<User> luser2 = userMapper.selectByValid("invalid");
			List<User> luser3 = userMapper.selectByGroupId(group.getGroupId());
			se.commit();
			logger.info(user.toString());
			logger.info(user2.toString());
			logger.info(luser.toString());
			logger.info(luser2.toString());
			logger.info(luser3.toString());
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void updateUser()
	{
		try {
			openTestSession();
			logger.info("start");
			StringBuilder strb = new StringBuilder();
			strb.append("#userName(\"CellTest\" <--> \"CellTestChange\") \n");
			User user = userMapper.selectByUserId(USER_ID);
			strb.append(user + "\n");

			user.setUserName("CellTestChange");
			userMapper.update(user);
			user = userMapper.selectByUserId(USER_ID);
			se.commit();
			strb.append(user + "\n");

			user.setUserName(USER_NAME);
			userMapper.update(user);
			user = userMapper.selectByUserId(USER_ID);
			se.commit();
			strb.append(user + "\n");

			strb.append("#valid('invalid' -> 'valid')\n");
			user.setValid("valid");
			userMapper.update(user);
			user = userMapper.selectByUserId(USER_ID);
			se.commit();
			strb.append(user);
			logger.info(strb.toString());
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void deleteUser()
	{
		try {
			openTestSession();
			logger.info("start");
			User user = userMapper.selectByUserId(USER_ID);
			logger.info(userMapper.delete(user).toString());
			se.commit();
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
