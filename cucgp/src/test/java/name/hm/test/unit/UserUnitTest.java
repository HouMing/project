package name.hm.test.unit;

import java.util.List;

import name.hm.jpa.GroupMapper;
import name.hm.jpa.UserMapper;
import name.hm.pojo.Group;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;
import name.hm.test.RoleIntegrationTest;
import name.hm.test.BaseLogger.INFO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * test ISUD of User Table
 */
public class UserUnitTest extends BaseTestCase
{

	public final static Integer USER_ID = 0;
	public final static String USER_NAME = "CellTest";
	public final static String USER_HOME = "/" + USER_NAME + "/";
	public final static String PASSWORD = "123456";

	static Group group = null;

	public void beforeTest() throws Exception
	{
		openTestSession();
		group = new Group();
		group.setGroupId(GroupUnitTest.GROUP_ID);
		group.setGroupName(GroupUnitTest.GROUP_NAME);
		group.setValid("valid");
		groupMapper.insert(group);
		se.commit();
		closeTestSession();
	}

	public void afterTest()
	{
		openTestSession();
		group.setGroupId(GroupUnitTest.GROUP_ID);
		group.setGroupName(GroupUnitTest.GROUP_NAME);
		group.setValid("valid");
		groupMapper.delete(group);
		se.commit();
		closeTestSession();
	}

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

	/**
	 * insert user #userId(0) #userName("CellTest") #password("123456")
	 * #valide('invalide') #userHome("/#userName/")
	 */
	public void insertUser()
	{
		try {
			openTestSession();
			INFO.isTrue("start", false);
			se.flushStatements();
			User user = new User();
			user.setUserId(USER_ID);
			user.setUserName(USER_NAME);
			user.setPassword(PASSWORD);
			user.setGroupId(group.getGroupId());
			user.setValid("invalid");
			user.setUserHome(USER_HOME);
			userMapper.insert(user);
			se.commit();
			INFO.isTrue("end", false);
		} catch (Exception e) {
			se.rollback();
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * select user #userId(0) #userName("CellTest")
	 * #userName("CellTest")+#password("123456") #valide('valide') =>
	 * #valide('invalide')
	 */
	public void selectUser()
	{
		try {
			openTestSession();
			INFO.isTrue("start", false);
			User user = userMapper.selectByUserId(USER_ID);
			User user2 = userMapper.selectByUserName(USER_NAME);
			List<User> luser = userMapper.selectByValid("valid");
			List<User> luser2 = userMapper.selectByValid("invalid");
			List<User> luser3 = userMapper.selectByGroupId(group.getGroupId());
			se.commit();
			INFO.isTrue(user.toString(), false);
			INFO.isTrue(user2.toString(), false);
			INFO.isTrue(luser.toString(), false);
			INFO.isTrue(luser2.toString(), false);
			INFO.isTrue(luser3.toString(), false);
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * update user 
	 */
	public void updateUser()
	{
		try {
			openTestSession();
			INFO.isTrue("start", false);
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
			INFO.isTrue(strb.toString(), false);
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * delete user #userId(0)
	 */
	public void deleteUser()
	{
		try {
			openTestSession();
			INFO.isTrue("start", false);
			User user = userMapper.selectByUserId(USER_ID);
			INFO.isTrue(userMapper.delete(user).toString(), false);
			se.commit();
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

}
