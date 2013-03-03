package name.hm.test.unit;

import java.util.List;

import name.hm.pojo.Action;
import name.hm.test.BaseTestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

@Deprecated
public class ActionUnitTest extends BaseTestCase
{
	public static Integer ACTION_ID = 0;
	public static String ACTION_NAME = "CellTest";
	public static String ACTION_URL = "/test/test/";
	public static String ACTION_STATUS = "valid";

	@Test
	public void cell()
	{
		insertAction();
		selectAction();
		updateActionName();
		deleteActionName();
	}

	/**
	 * insert Action
	 */
	public void insertAction()
	{
		try {
			openTestSession();
			logger.info("start");
			Action action = new Action();
			action.setActionId(ACTION_ID);
			action.setActionName(ACTION_NAME);
			action.setActionStatus(ACTION_STATUS);
			action.setActionUrl(ACTION_URL);
			action.setRoleId(RoleUnitTest.ROLE_ID);
			action.setWorkflowId(WorkflowUnitTest.WORKFLOW_ID);
			actionMapper.insert(action);
			se.commit();
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * select Action
	 */
	public void selectAction()
	{
		try {
			openTestSession();
			logger.info("start");
			se.flushStatements();
			Action action = actionMapper.selectByActionId(ACTION_ID);
			Action action2 = actionMapper.selectByActionName(ACTION_NAME);
			List<Action> laction = actionMapper.selectByActionStatus("valid");
			List<Action> laction2 = actionMapper.selectByActionStatus("invalid");
			se.commit();
			logger.debug(action.toString());
			logger.debug(action2.toString());
			logger.debug(laction.toString());
			logger.debug(laction2.toString());
			logger.debug("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * update Action
	 */
	public void updateActionName()
	{
		try {
			openTestSession();
			Integer ret;
			logger.info("start");
			Action action = actionMapper.selectByActionId(ACTION_ID);
			if (action == null) {
				logger.error("selectByActionId(ACTION_ID) failed!");
			} else {
				logger.info(action.toString());
			}

			action.setActionName("CellTestChange");
			ret = actionMapper.update(action);
			if (ret != 1) {
				logger.error("update failed");
			} else {
				action = actionMapper.selectByActionId(ACTION_ID);
				logger.info(action);
			}
			se.commit();

			action.setActionName(ACTION_NAME);
			ret = actionMapper.update(action);
			if (ret != 1) {
				logger.error("update failed");
			}
			action = actionMapper.selectByActionId(ACTION_ID);
			se.commit();
			logger.info(action);

			action.setActionStatus("valid");
			actionMapper.update(action);
			action = actionMapper.selectByActionId(ACTION_ID);
			se.commit();
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void deleteActionName()
	{
		try {
			logger.info("start");
			se.flushStatements();
			Action action = actionMapper.selectByActionId(ACTION_ID);
			logger.info(actionMapper.delete(action).toString());
			se.commit();
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
