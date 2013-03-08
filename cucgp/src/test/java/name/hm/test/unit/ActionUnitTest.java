package name.hm.test.unit;

import java.util.List;

import name.hm.pojo.Action;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

// PASS UNIT #0307
public class ActionUnitTest extends BaseTestCase
{
	static public Integer ACTION_ID = null;
	static final public String ACTION_NAME = "测试操作";
	static final public String ACTION_NAMEC = "测试操作改";

	static public Integer ACTION_ID1 = null;
	static final public String ACTION_NAME1 = "测试操作1";

	static public Integer ACTION_ID2 = null;
	static final public String ACTION_NAME2 = "测试操作2";

	static public String ACTION_URL = "/test/unit/";
	static public Action.STATUS ACTION_VALID = Action.VALID;
	static public Action.STATUS ACTION_INVALID = Action.INVALID;

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
			Action action = new Action(ACTION_ID, ACTION_NAME, ACTION_URL,
					ACTION_VALID, RoleUnitTest.ROLE_ID, WorkflowUnitTest.WORKFLOW_ID);
			Action action1 = new Action(ACTION_ID1, ACTION_NAME1, ACTION_URL,
					ACTION_VALID, RoleUnitTest.ROLE_ID1, WorkflowUnitTest.WORKFLOW_ID);
			Action action2 = new Action(ACTION_ID2, ACTION_NAME2, ACTION_URL,
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
			Action action = actionMapper.selectByActionId(ACTION_ID);
			Action action1 = actionMapper.selectByActionName(ACTION_NAME);
			List<Action> l = actionMapper.selectByActionStatus(ACTION_VALID);
			List<Action> l2 = actionMapper.selectByActionStatus(ACTION_INVALID);
			se.commit();
			if (action != null) {
				logger.info("selectByActionId OK!\n" + action);
			} else {
				logger.error("selectByActionName faild\n" + action);
			}
			if (action1 != null) {
				logger.info("selectByActionName OK!\n" + action1);
			} else {
				logger.error("selectByActionName failed\n" + action1);
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
			Integer ret;
			openTestSession();
			Action action = actionMapper.selectByActionId(ACTION_ID);
			if (action == null) {
				logger.error("update failed");
			} else {
				logger.info("before update\n" + action);
			}
			action.setActionName(ACTION_NAMEC);
			action.setActionStatus(ACTION_INVALID);
			ret = actionMapper.update(action);
			se.commit();
			if (ret == 1) {
				action = actionMapper.selectByActionId(ACTION_ID);
				se.commit();
				logger.info("update OK!\n" + action);
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
