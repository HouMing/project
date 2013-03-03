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

/**
 * test CRUD of Role Table
 */
public class RoleUnitTest extends BaseTestCase
{
	static public Integer ROLE_ID = 0;
	static public String ROLE_NAME = "CellTest";
	static public String ROLE_VALID = "valid";
	static public String ROLE_INVALID = "invalid";

	static private Integer ret;
	
	@Test
	public void test(){
		insertRole();
		selectRole();
		updateRole();
		deleteRole();
	}
	
	public void insertRole()
	{
		try {
			openTestSession();
			logger.info("start");
			Role role = new Role();
			role.setRoleId(ROLE_ID);
			role.setRoleName(ROLE_NAME);
			role.setValid(ROLE_VALID);
			ret = roleMapper.insert(role);
			if (ret == 1) {
				logger.info(role);
			} else {
				logger.error("insertRole failed! " + role);
			}
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void selectRole()
	{
		try {
			openTestSession();
			logger.info("start");
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			Role role2 = roleMapper.selectByRoleName(ROLE_NAME);
			List<Role> lrole = roleMapper.selectByValid(ROLE_VALID);
			List<Role> lrole2 = roleMapper.selectByValid("in" + ROLE_VALID);
			se.commit();
			logger.info(role.toString());
			logger.info(role2.toString());
			logger.info(lrole.toString());
			logger.info(lrole2.toString());
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * update Role
	 */
	public void updateRole()
	{
		try {
			openTestSession();
			logger.info("start");
			StringBuilder strb = new StringBuilder();
			strb.append("#roleName(\"CellTest\" <--> \"CellTestChange\")\n");
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			strb.append(role + "\n");

			role.setRoleName(ROLE_NAME + "Changed");
			roleMapper.update(role);
			role = roleMapper.selectByRoleId(ROLE_ID);
			se.commit();
			strb.append(role + "\n");

			role.setRoleName(ROLE_NAME);
			roleMapper.update(role);
			role = roleMapper.selectByRoleId(ROLE_ID);
			se.commit();
			strb.append(role + "\n");

			strb.append("#valid('valid' -> 'invalid')\n");
			role.setValid(ROLE_VALID);
			roleMapper.update(role);
			role = roleMapper.selectByRoleId(ROLE_ID);
			se.commit();
			strb.append(role);
			logger.info(strb.toString());
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void deleteRole()
	{
		try {
			openTestSession();
			logger.info("start");
			se.flushStatements();
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			logger.info(roleMapper.delete(role).toString());
			se.commit();
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
