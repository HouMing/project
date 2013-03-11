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
//TODO 3 PASS, Upgrade - task : #0310
public class RoleUnitTest extends BaseTestCase
{
	public static Role ROLE_ADMIN = null;
	protected static final String ROLENAME_ADMIN = "管理员";
	protected static final String ROLENAME_ADMINC = "管理员改";

	public static Role ROLE_TEACHER = null;
 	protected static final String ROLENAME_TEACHER = "教师";

 	public static Role ROLE_STUDENT = null;
	protected static final String ROLENAME_STUDENT = "学生";
	
	public static final Role.Valid ROLE_VALID = Role.VALID;
	public static final Role.Valid ROLE_INVALID = Role.INVALID;

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
			ROLE_ADMIN = new Role(ROLENAME_ADMIN, ROLE_VALID);
			ROLE_TEACHER = new Role(ROLENAME_TEACHER, ROLE_INVALID);
			ROLE_STUDENT = new Role(ROLENAME_STUDENT, ROLE_INVALID);
			error = roleMapper.insert(ROLE_ADMIN) & error;
			error = roleMapper.insert(ROLE_TEACHER) & error;
			error = roleMapper.insert(ROLE_STUDENT) & error;
			se.commit();
			if (error == 1) {
				logger.info("create Role OK!\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER + "\n" + ROLE_STUDENT);
			} else {
				logger
						.info("create Role failed\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER + "\n" + ROLE_STUDENT);
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
			openTestSession();

			Role admin = roleMapper.selectByRoleId(ROLE_ADMIN.getRoleId());
			Role teacher = roleMapper.selectByRoleName(ROLE_TEACHER.getRoleName());
			List<Role> l = roleMapper.selectByValid(ROLE_VALID);
			List<Role> l2 = roleMapper.selectByValid(ROLE_INVALID);
			se.commit();

			if (admin != null) {
				logger.info("selectByRoleId OK!\n" + admin);
			} else {
				logger.error("selectByRoleId failed\n" + ROLE_ADMIN);
			}
			if (teacher != null) {
				logger.info("selectByRoleName OK!\n" + teacher);
			} else {
				logger.error("selectByRoleName failed\n" + ROLE_TEACHER);
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
			Integer error = 1;
			openTestSession();

			logger.info("before update:\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER);
			ROLE_ADMIN.setRoleName(ROLENAME_ADMINC);
			ROLE_TEACHER.setValid(ROLE_VALID);
			error = roleMapper.update(ROLE_ADMIN) & error;
			error = roleMapper.update(ROLE_TEACHER) & error;
			se.commit();

			ROLE_ADMIN = roleMapper.selectByRoleId(ROLE_ADMIN.getRoleId());
			ROLE_TEACHER = roleMapper.selectByRoleId(ROLE_TEACHER.getRoleId());
			se.commit();

			if (error == 1) {
				logger.info("update OK!\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER);
			} else {
				logger.error("update failed\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER);
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
			logger.info("before delete\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER + "\n" + ROLE_STUDENT);
			error = roleMapper.delete(ROLE_ADMIN) & error;
			error = roleMapper.delete(ROLE_TEACHER) & error;
			error = roleMapper.delete(ROLE_STUDENT) & error;
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER + "\n" + ROLE_STUDENT);
			} else {
				logger.error("delete failed\n" + ROLE_ADMIN + "\n" + ROLE_TEACHER + "\n" + ROLE_STUDENT);
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
			for (Role tmp : l) {
				roleMapper.delete(tmp);
			}
			for (Role tmp : l2) {
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
