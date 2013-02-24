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
import name.hm.test.RoleIntegrationTest;
import name.hm.test.BaseLogger.INFO;

/**
 * test ISUD of Role Table
 */
public class RoleUnitTest extends BaseTestCase
{

	public static Integer ROLE_ID = 0;
	public static String ROLE_NAME = "CellTest";
	public static String ROLE_VALID = "valid";

	/**
	 * insert Role
	 */
	public void insertRole()
	{
		try {
			openTestSession();
			INFO.isTrue("start", false);
			Role role = new Role();
			role.setRoleId(ROLE_ID);
			role.setRoleName(ROLE_NAME);
			role.setValid(ROLE_VALID);
			roleMapper.insert(role);
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * select Role
	 */
	public void selectRole()
	{
		try {
			insertRole();
			openTestSession();
			INFO.isTrue("start", false);
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			Role role2 = roleMapper.selectByRoleName(ROLE_NAME);
			List<Role> lrole = roleMapper.selectByValid(ROLE_VALID);
			List<Role> lrole2 = roleMapper.selectByValid("in" + ROLE_VALID);
			se.commit();
			INFO.isTrue(role.toString(), false);
			INFO.isTrue(role2.toString(), false);
			INFO.isTrue(lrole.toString(), false);
			INFO.isTrue(lrole2.toString(), false);
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * update Role
	 */
	@Test
	public void updateRole()
	{
		try {
			openTestSession();
			INFO.isTrue("start", false);
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
			INFO.isTrue(strb.toString(), false);
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * delete Role
	 */
	@Test
	public void deleteRole()
	{
		try {
			openTestSession();
			INFO.isTrue("start", false);
			se.flushStatements();
			Role role = roleMapper.selectByRoleId(ROLE_ID);
			INFO.isTrue(roleMapper.delete(role).toString(), false);
			se.commit();
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

}
