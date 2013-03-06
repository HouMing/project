package name.hm.test.unit;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import name.hm.jpa.RoleMapper;
import name.hm.pojo.Role;
import name.hm.test.BaseTestCase;
import name.hm.test.integration.RoleIntegrationTest;

// TODO check testUnit
public class RoleUnitTest extends BaseTestCase
{
	static public Integer ROLE_ID = 0;
	static public String ROLE_NAME = "测试角色";
	static public String ROLE_NAMEC = "测试角色改";
	static public String ROLE_NAME1 = "测试角色1";
	static public Role.VALID ROLE_VALID = Role.VALID("valid");
	static public Role.VALID ROLE_INVALID = Role.VALID("invalid");

	@Test
	public void test()
	{
		beforeTest();
		create();
		select();
		update();
		delete();
		afterTest();
	}

	//
	protected void afterTest()
	{
		logger.info("start RoleUnitTest");
	}

	//
	protected void beforeTest()
	{
		logger.info("finish RoleUnitTest");
	}

	// PASS #0305
	public void create()
	{
		try {
			Integer error;
			openTestSession();
			Role role = new Role(ROLE_ID, ROLE_NAME, ROLE_INVALID);
			error = roleMapper.insert(role);
			se.commit();
			if (error == 1) {
				logger.info("create Role OK!\n" + role);
			} else {
				logger.error("create Role failed\n" + role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0305
	public void select()
	{
		try {
			Integer error;
			openTestSession();
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			Role role2 = roleMapper.selectByRoleName(ROLE_NAME);
			List<Role> l = roleMapper.selectByValid(ROLE_VALID);
			List<Role> l2 = roleMapper.selectByValid(ROLE_INVALID);
			se.commit();
			if (role != null) {
				logger.info("selectByRoleId OK!\n" + role);
			} else {
				logger.error("selectByRoleId failed\n" + role);
			}
			if (role2 != null) {
				logger.info("selectByRoleName OK!\n" + role2);
			} else {
				logger.error("selectByRoleName failed\n" + role2);
			}
			if (l.size() > 0 || l2.size() > 0) {
				logger.info("selectByValid OK!\n" + l + l2);
			} else {
				logger.error("selectByValid failed\n" + l + l2);
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
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			logger.info("before update:\n" + role);
			role.setRoleName(ROLE_NAMEC);
			role.setValid(ROLE_VALID);
			error = roleMapper.update(role);
			se.commit();
			if (error == 1) {
				role = roleMapper.selectByRoleId(ROLE_ID);
				se.commit();
				logger.info("update OK!\n" + role);
			} else {
				logger.error("update failed\n" + role);
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
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			error = roleMapper.delete(role);
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + role);
			} else {
				logger.error("delete failed\n" + role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
