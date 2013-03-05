package name.hm.test.unit;

import java.util.List;

import name.hm.pojo.Action;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActionUnitTest extends BaseTestCase
{
	static public Integer ACTION_ID = 0;
	static public String ACTION_NAME = "测试操作";
	static public String ACTION_NAMEC = "测试操作改";
	static public String ACTION_URL = "/test/unit/";
	static public Action.STATUS ACTION_VALID = Action.getStatus("valid");
	static public Action.STATUS ACTION_INVALID = Action.getStatus("invalid");

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

	// DOING
	private void beforeTest()
	{
		logger.info("start ActionUnit test");
		GroupUnitTest unitGroup = new GroupUnitTest();
		UserUnitTest unitUser = new UserUnitTest();
		WorkflowUnitTest unitWorkflow = new WorkflowUnitTest();
		unitGroup.create();
		unitUser.create();
		unitWorkflow.create();
	}

	// FINISH
	private void afterTest()
	{
		UserUnitTest unitUser = new UserUnitTest();
		unitUser.delete();
		logger.info("finish ActionUnit test");
	}

	// FINISH
	public void create()
	{
		try {
			Integer ret;
			openTestSession();
			Action action = new Action(ACTION_ID, ACTION_NAME, ACTION_URL,
					ACTION_INVALID, RoleUnitTest.ROLE_ID, WorkflowUnitTest.WORKFLOW_ID);
			ret = actionMapper.insert(action);
			if (ret == 1) {
				logger.info("insert Action OK!");
			} else {
				logger.error("insert Action failed");
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// FINISH #0305
	public void read()
	{
		try {
			openTestSession();
			Action action = actionMapper.selectByActionId(ACTION_ID);
			Action action2 = actionMapper.selectByActionName(ACTION_NAME);
			List<Action> l = actionMapper.selectByActionStatus(ACTION_VALID);
			List<Action> l2 = actionMapper.selectByActionStatus(ACTION_INVALID);
			se.commit();
			if (action != null) {
				logger.info("selectByActionId OK! : " + action);
			} else {
				logger.error("selectByActionName faild : " + action);
			}
			if (action2 != null) {
				logger.info("selectByActionName OK! : " + action2);
			} else {
				logger.error("selectByActionName failed : " + action2);
			}
			if (l.size() > 0 || l2.size() > 0) {
				logger.info("selectByActionStatus OK! : " + l + l2);
			} else {
				logger.error("selectByActionStatus failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// FINISH #0305
	public void update()
	{
		try {
			Integer ret;
			openTestSession();
			Action action = actionMapper.selectByActionId(ACTION_ID);
			if (action == null) {
				logger.error("update failed");
			} else {
				logger.info("before update : " + action);
			}
			action.setActionName(ACTION_NAMEC);
			action.setActionStatus(ACTION_INVALID);
			ret = actionMapper.update(action);
			se.commit();
			if (ret == 1) {
				action = actionMapper.selectByActionId(ACTION_ID);
				se.commit();
				logger.info("update OK! : " + action);
			} else {
				logger.error("update failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// FINISH #0305
	public void delete()
	{
		try {
			Integer ret;
			openTestSession();
			Action action = actionMapper.selectByActionId(ACTION_ID);
			logger.info("before delete : " + action);
			ret = actionMapper.delete(action);
			if (ret == 1) {
				logger.info("delete OK!");
				action = actionMapper.selectByActionId(ACTION_ID);
				se.commit();
				logger.info("after delete : " + action);
			} else {
				logger.error("delete failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
