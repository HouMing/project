package name.hm.test.unit;

import java.util.LinkedList;
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

// PASS UNIT #0306
//TODO 3 UNIT, Upgrade - task : #0310
public class RoleUnitTest extends BaseTestCase
{
	static public Integer ROLE_ID = null;
	static final public String ROLE_NAME = "测试角色";
	static final public String ROLE_NAMEC = "测试角色改";
	
	static public Integer ROLE_ID1 = null;
	static final public String ROLE_NAME1 = "测试角色1";
	
	static public Integer ROLE_ID2 = null;
	static final public String ROLE_NAME2 = "测试角色2";
	static final public Role.Valid ROLE_VALID = Role.VALID;
	static final public Role.Valid ROLE_INVALID = Role.INVALID;

	@Test
	public void test()
	{
		beforeTest();
		create();
		read();
		update();
		delete();
		afterTest();
	}

	// PASS CELL #0306
	protected void beforeTest()
	{
		logger.info("start RoleUnitTest");
	}

	// PASS CELL #0306
	protected void afterTest()
	{
		clean();
		logger.info("finish RoleUnitTest");
	}

	// PASS CELL #0306
	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			Role role = new Role(ROLE_ID, ROLE_NAME, ROLE_VALID);
			Role role1 = new Role(ROLE_ID1, ROLE_NAME1, ROLE_VALID);
			Role role2 = new Role(ROLE_ID2, ROLE_NAME2, ROLE_INVALID);
			error = roleMapper.insert(role) & error;
			error = roleMapper.insert(role1) & error;
			error = roleMapper.insert(role2) & error;
			se.commit();
			ROLE_ID = role.getRoleId();
			ROLE_ID1 = role1.getRoleId();
			ROLE_ID2 = role2.getRoleId();
			if (error == 1) {
				logger.info("create Role OK!\n" + role + "\n" + role1 + "\n" + role2);
			} else {
				logger.info("create Role failed\n" + role + "\n" + role1 + "\n" + role2);
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

	// PASS CELL #0306
	public void update()
	{
		try {
			Integer error;
			openTestSession();
			
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			se.commit();
			
			logger.info("before update:\n" + role);
			role.setRoleName(ROLE_NAMEC);
			role.setValid(ROLE_INVALID);
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

	// PASS CELL #0306
	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			Role role1 = roleMapper.selectByRoleId(ROLE_ID1);
			Role role2 = roleMapper.selectByRoleId(ROLE_ID2);
			se.commit();
			logger.info("before delete\n" + role +"\n" + role1 + "\n"+ role2);
			error = roleMapper.delete(role) & error;
			error = roleMapper.delete(role1) & error;
			error = roleMapper.delete(role2) & error;
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + role +"\n" + role1 + "\n"+ role2);
			} else {
				logger.error("delete failed\n" + role +"\n" + role1 + "\n"+ role2);
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
			LinkedList<Role> l = roleMapper.selectByValid(ROLE_VALID);
			LinkedList<Role> l2 = roleMapper.selectByValid(ROLE_INVALID);
			se.commit();
			for (Role tmp: l) {
				roleMapper.delete(tmp);
			}
			for (Role tmp: l2) {
				roleMapper.delete(tmp);
			}
			se.commit();
			roleMapper.selectByValid(ROLE_INVALID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
