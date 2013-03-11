package name.hm.test.unit;

import java.util.List;

import junit.framework.Assert;

import name.hm.pojo.Action;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.JUnit4;

// PASS UNIT #0307
//TODO 5 UNIT, Upgrade - task : #0310
public class ActionUnitTest extends BaseTestCase
{
	public static Integer ACTION_ID = null;
	public static final String ACTION_NAME = "测试操作";
	public static final String ACTION_NAMEC = "测试操作改";
	public static final String ACTION_URL = "/test/unit/";
	public static Action action = null;

	public static Integer ACTION_ID1 = null;
	public static final String ACTION_NAME1 = "测试操作1";
	public static Action action1 = null;

	public static Integer ACTION_ID2 = null;
	public static final String ACTION_NAME2 = "测试操作2";
	public static Action action2 = null;

	public static final Action.Status ACTION_VALID = Action.VALID;
	public static final Action.Status ACTION_INVALID = Action.INVALID;

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

	// PASS #0306
	private void beforeTest()
	{
		logger.info("start ActionUnit test");
		RoleUnitTest unitRole = new RoleUnitTest();
		WorkflowUnitTest unitWorkflow = new WorkflowUnitTest();
		unitRole.create();
		unitWorkflow.create();
	}

	// PASS #0306
	private void afterTest()
	{
		RoleUnitTest unitRole = new RoleUnitTest();
		WorkflowUnitTest unitWorkflow = new WorkflowUnitTest();
		unitRole.delete();
		unitWorkflow.delete();
		clean();
		logger.info("finish ActionUnit test");
	}

	// PASS #0306
	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			action = new Action(ACTION_ID, ACTION_NAME, ACTION_URL,
					ACTION_VALID, RoleUnitTest.ROLE_ID, WorkflowUnitTest.WORKFLOW_ID);
			action1 = new Action(ACTION_ID1, ACTION_NAME1, ACTION_URL,
					ACTION_VALID, RoleUnitTest.ROLE_ID1, WorkflowUnitTest.WORKFLOW_ID);
			action2 = new Action(ACTION_ID2, ACTION_NAME2, ACTION_URL,
					ACTION_INVALID, RoleUnitTest.ROLE_ID2, WorkflowUnitTest.WORKFLOW_ID);
			error = actionMapper.insert(action) & error;
			error = actionMapper.insert(action1) & error;
			error = actionMapper.insert(action2) & error;
			se.commit();
			ACTION_ID = action.getActionId();
			ACTION_ID1 = action1.getActionId();
			ACTION_ID2 = action2.getActionId();
			if (error == 1) {
				logger.info("insert Action OK!\n" + action + "\n" + action1 + "\n"
						+ action2);
			} else {
				logger.error("insert Action failed\n" + action + "\n" + action1 + "\n"
						+ action2);
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
			Action tmpAction1 = actionMapper.selectByActionId(ACTION_ID);
			Action tmpAction2 = actionMapper.selectByActionName(ACTION_NAME);
			List<Action> l = actionMapper.selectByActionStatus(ACTION_VALID);
			List<Action> l2 = actionMapper.selectByActionStatus(ACTION_INVALID);
			se.commit();
			if (tmpAction1 != null) {
				logger.info("selectByActionId OK!\n" + tmpAction1);
			} else {
				logger.error("selectByActionName faild\n" + tmpAction1);
			}
			if (tmpAction2 != null) {
				logger.info("selectByActionName OK!\n" + tmpAction2);
			} else {
				logger.error("selectByActionName failed\n" + tmpAction2);
			}
			if (l.size() > 0 || l2.size() > 0) {
				logger.info("selectByActionStatus OK!\n" + l + "\n" + l2);
			} else {
				logger.error("selectByActionStatus failed" + l + "\n" + l2);
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
			if (action == null) {
				logger.error("update failed");
				Assert.fail("update failed");
			} else {
				logger.info("before update\n" + action);
			}
			action.setActionName(ACTION_NAMEC);
			action.setActionStatus(ACTION_INVALID);
			error = actionMapper.update(action);
			se.commit();
			Action tmpAction = actionMapper.selectByActionId(ACTION_ID);
			se.commit();
			if (error == 1) {
				Assert.assertEquals(action, tmpAction);
				Assert.assertNotSame(action, tmpAction);
				logger.info("update OK!\n" + tmpAction);
			} else {
				logger.error("update failed");
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
			Integer error;
			openTestSession();
			Action action = actionMapper.selectByActionId(ACTION_ID);
			logger.info("before delete\n" + action);
			error = actionMapper.delete(action);
			if (error == 1) {
				logger.info("delete OK!");
				action = actionMapper.selectByActionId(ACTION_ID);
				se.commit();
				logger.info("after delete\n" + action);
			} else {
				logger.error("delete failed");
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
			List<Action> l = actionMapper.selectByActionStatus(Action.VALID);
			List<Action> l2 = actionMapper.selectByActionStatus(Action.INVALID);
			se.commit();
			for (Action tmp : l) {
				actionMapper.delete(tmp);
			}
			for (Action tmp : l2) {
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
