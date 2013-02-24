package name.hm.test.unit;

import java.util.List;

import name.hm.pojo.Action;
import name.hm.test.BaseTestCase;
import name.hm.test.BaseLogger.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActionUnitTest extends BaseTestCase
{
	private ActionUnitTest unit = null;

	public static final Integer ACTION_ID = 0;
	public static final String ACTION_NAME = "CellTest";
	public static final String ACTION_URL = "/test/test/";
	public static final String ACTION_STATUS = "valid";

	@Test
	public void cell()
	{
		openTestSession();
		insertAction();
		selectAction();
		updateActionName();
		deleteActionName();
		closeTestSession();
	}

	/**
	 * insert Action #actionId(0) #actionName("CellTest"); #actionStatus('unkown')
	 */
	public void insertAction()
	{
		try {
			openTestSession();
			INFO.isTrue("start", false);
			INFO.isTrue("ActionCellTest - insertAction\n" + " insert action\n"
					+ " #actionId(ACTION_ID)\n" + " #actionName(ACTION_NAME)\n"
					+ " #valid(ACTION_VALID)", false);
			se.flushStatements();
			Action action = new Action();
			action.setActionId(ACTION_ID);
			action.setActionName(ACTION_NAME);
			action.setActionStatus(ACTION_STATUS);
			action.setActionUrl(ACTION_URL);
			action.setRoleId(RoleUnitTest.ROLE_ID);
			action.setWorkflowId(WorkflowUnitTest.WORKFLOW_ID);
			actionMapper.insert(action);
			se.commit();
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * select Action #actionId(0) #actionName("CellTest") #actionStatus("Step1")
	 * => \ #actionStatus("Step2") => \ #actionStatus("Step3")
	 */
	public void selectAction()
	{
		try {
			INFO.isTrue("start", false);
			se.flushStatements();
			Action action = actionMapper.selectByActionId(ACTION_ID);
			Action action2 = actionMapper.selectByActionName(ACTION_NAME);
			List<Action> laction = actionMapper.selectByActionStatus("valid");
			List<Action> laction2 = actionMapper.selectByActionStatus("invalid");
			se.commit();
			INFO.isTrue(action.toString(), false);
			INFO.isTrue(action2.toString(), false);
			INFO.isTrue(laction.toString(), false);
			INFO.isTrue(laction2.toString(), false);
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * update Action
	 */
	public void updateActionName()
	{
		INFO.isTrue("start", false);
		StringBuilder strb = new StringBuilder();
		try {
			se.flushStatements();
			strb.append("#actionName(\"CellTest\" <--> \"CellTestChange\") \n");
			Action action = actionMapper.selectByActionId(ACTION_ID);
			strb.append(action + "\n");

			action.setActionName("CellTestChange");
			actionMapper.update(action);
			action = actionMapper.selectByActionId(ACTION_ID);
			se.commit();
			strb.append(action + "\n");

			action.setActionName(ACTION_NAME);
			actionMapper.update(action);
			action = actionMapper.selectByActionId(ACTION_ID);
			se.commit();
			strb.append(action + "\n");

			strb.append("#valid('invalid' -> 'valid')\n");
			action.setActionStatus("valid");
			actionMapper.update(action);
			action = actionMapper.selectByActionId(ACTION_ID);
			se.commit();
			strb.append(action);
			INFO.isTrue(strb.toString(), false);
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteActionName()
	{
		try {
			INFO.isTrue("start", false);
			se.flushStatements();
			Action action = actionMapper.selectByActionId(ACTION_ID);
			INFO.isTrue(actionMapper.delete(action).toString(), false);
			se.commit();
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
