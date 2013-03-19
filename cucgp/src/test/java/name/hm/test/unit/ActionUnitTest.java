package name.hm.test.unit;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import name.hm.m.Action;
import name.hm.m.User;
import name.hm.test.BaseTestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.JUnit4;

// PASS UNIT #0307
//TODO 5 PASS, Upgrade - task : #0310
public class ActionUnitTest extends BaseTestCase
{
	public static Action ACTION_ADMIN = null;
	protected static final String ACTION_ADMIN_NAME = "管理员操作";
	protected static final String ACTION_ADMIN_NAMEC = "管理员操作改";
	protected static final String ACTION_ADMIN_URL = "/admin/unit/test.do";
	protected static final String ACTION_ADMIN_DESCRIPTION = "/admin/unit/test.do";
	

	public static Action ACTION_TEACHER = null;
	protected static final String ACTION_TEACHER_NAME = "教师操作";
	protected static final String ACTION_TEACHER_URL = "/teacher/unit/test.do";
	protected static final String ACTION_TEACHER_DESCRIPTION = "/admin/unit/test.do";
	
	public static Action ACTION_STUDENT = null;
	protected static final String ACTION_STUDENT_NAME = "学生操作";
	protected static final String ACTION_STUDENT_URL = "/student/unit/test.do";
	protected static final String ACTION_STUDENT_DESCRIPTION = "/admin/unit/test.do";
	

	@Test
	public void test()
	{
		create();
		read();
		update();
		delete();
	}

	// PASS #0306
	@Before
	public void beforeTest()
	{
		logger.info("start ActionUnit test");
		RoleUnitTest unitRole = new RoleUnitTest();
		WorkflowUnitTest unitWorkflow = new WorkflowUnitTest();
		unitRole.create();
		unitWorkflow.create();
	}

	// PASS #0306
	@After
	public void afterTest()
	{
		RoleUnitTest unitRole = new RoleUnitTest();
		WorkflowUnitTest unitWorkflow = new WorkflowUnitTest();
		unitRole.clean();
		unitWorkflow.clean();
		clean();
		logger.info("finish ActionUnit test");
	}

	// PASS #0306
	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			ACTION_ADMIN = new Action(ACTION_ADMIN_NAME, 
					ACTION_ADMIN_URL,
					ACTION_ADMIN_DESCRIPTION, 
					RoleUnitTest.R_Admin.getRoleId());
			ACTION_TEACHER = new Action(ACTION_TEACHER_NAME, 
					ACTION_TEACHER_URL, 
					ACTION_TEACHER_DESCRIPTION, 
					RoleUnitTest.R_Teacher.getRoleId());
			ACTION_STUDENT = new Action(ACTION_STUDENT_NAME,
					ACTION_STUDENT_URL,
					ACTION_STUDENT_DESCRIPTION,
					RoleUnitTest.R_Student.getRoleId());
			error = actionMapper.insert(ACTION_ADMIN) & error;
			error = actionMapper.insert(ACTION_TEACHER) & error;
			error = actionMapper.insert(ACTION_STUDENT) & error;
			se.commit();
			if (error == 1) {
				logger.info("insert Action OK!\n" + ACTION_ADMIN + "\n" + ACTION_TEACHER + "\n"
						+ ACTION_STUDENT);
			} else {
				logger.error("insert Action failed\n" + ACTION_ADMIN + "\n" + ACTION_TEACHER + "\n"
						+ ACTION_STUDENT);
				Assert.fail("insert Action failed\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0306
	public void read()
	{
		try {
			openTestSession();
			Action action_admin = actionMapper.selectByActionId(ACTION_ADMIN.getActionId());
			Action action_teacher = actionMapper.selectByActionName(ACTION_TEACHER.getActionName());
			se.commit();
			if (action_admin != null) {
				Assert.assertEquals(action_admin, ACTION_ADMIN);
				logger.info("selectByActionId OK!\n" + action_admin);
			} else {
				logger.error("selectByActionName faild\n" + ACTION_ADMIN);
				Assert.fail("selectByActionName faild\n");
			}
			if (action_teacher != null) {
				Assert.assertEquals(action_teacher, ACTION_TEACHER);
				logger.info("selectByActionName OK!\n" + action_teacher);
			} else {
				logger.error("selectByActionName failed\n" + ACTION_TEACHER);
				Assert.fail("selectByActionName failed\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0306
	public void update()
	{
		try {
			Integer error;
			openTestSession();
			if (ACTION_ADMIN == null) {
				logger.error("update failed");
				Assert.fail("update failed");
			} else {
				logger.info("before update\n" + ACTION_ADMIN);
			}
			Action action_admin = actionMapper.selectByActionId(ACTION_ADMIN.getActionId());
			se.commit();
			action_admin.setActionName(ACTION_ADMIN_NAMEC);
			error = actionMapper.update(action_admin);
			se.commit();
			action_admin = actionMapper.selectByActionId(ACTION_ADMIN.getActionId());
			se.commit();
			if (error == 1) {
				Assert.assertTrue(!ACTION_ADMIN.equals(action_admin));
				logger.info("update OK!\n" + action_admin);
			} else {
				logger.error("update failed");
				Assert.fail("update failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0306
	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
			error = actionMapper.delete(ACTION_ADMIN) & error;
			error = actionMapper.delete(ACTION_TEACHER) & error;
			error = actionMapper.delete(ACTION_STUDENT) & error;
			se.commit();
			if (error == 1) {
				logger.info("delete OK!");
			} else {
				logger.error("delete failed");
				Assert.fail("delete failed");
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
      ArrayList<Action> l = actionMapper.selectAll();			
			se.commit();
			for (Action tmp : l) {
				actionMapper.delete(tmp);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
