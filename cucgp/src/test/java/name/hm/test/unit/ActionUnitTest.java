package name.hm.test.unit;

import java.util.List;

import name.hm.jpa.ActionMapper;
import name.hm.pojo.Action;
import name.hm.test.integration.RoleIntegrationTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActionUnitTest {

	  static SqlSessionFactory factory;
	  static SqlSession se = null;
	  static ActionMapper mp = null;
	  private static Logger logger = Logger.getLogger("testcell");

	  public static final Integer ACTION_ID = 0;
	  public static final String ACTION_NAME = "CellTest";
	  public static final String ACTION_URL = "/test/test/";
	  public static final String ACTION_STATUS = "valid";

	  @BeforeClass
	    static public void init() {
		  RoleIntegrationTest.init();
	      factory = RoleIntegrationTest.getSqlSessionFactory();
	      se = factory.openSession();
	      mp = se.getMapper(ActionMapper.class);
	    }

	  @AfterClass
	    static public void clean() {
	      se.close();
	    }

	  @Test
	    public void cell() {
	      insertAction();
	      selectAction();
	      updateActionName();
	      deleteActionName();
	    }

	  /**
	   * insert Action
	   * #actionId(0)
	   * #actionName("CellTest");
	   * #actionStatus('unkown')
	   */
	  public void insertAction() {
	    logger.info("start");
	    logger.info(
	        "ActionCellTest - insertAction\n" +
	        " insert action\n" +
	        " #actionId(ACTION_ID)\n" +
	        " #actionName(ACTION_NAME)\n" +
	        " #valid(ACTION_VALID)"
	        );
	    try {
	      se.flushStatements();
	      Action action = new Action();
	      action.setActionId(ACTION_ID);
	      action.setActionName(ACTION_NAME);
	      action.setActionStatus(ACTION_STATUS);
	      action.setActionUrl(ACTION_URL);
	      action.setRoleId(RoleUnitTest.ROLE_ID);
	      action.setWorkflowId(WorkflowUnitTest.WORKFLOW_ID);
	      mp.insert(action);
	      se.commit();
	      logger.info("end");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  /**
	   * select Action
	   * #actionId(0)
	   * #actionName("CellTest")
	   * #actionStatus("Step1") => \
	   * #actionStatus("Step2") => \
	   * #actionStatus("Step3")
	   */
	  public void selectAction() {
	    logger.info("start");
	    try {
	      se.flushStatements();
	      Action action = mp.selectByActionId(ACTION_ID);
	      Action action2 = mp.selectByActionName(ACTION_NAME);
	      List<Action> laction = mp.selectByActionStatus("valid");
	      List<Action> laction2 = mp.selectByActionStatus("invalid");
	      se.commit();
	      logger.info(action.toString());
	      logger.info(action2.toString());
	      logger.info(laction);
	      logger.info(laction2);
	      logger.info("end");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  /**
	   * TODO
	   * update Action
	   * #actionName("CellTest" <--> "CellTestChange")
	   * #actionStatus("Step1" <--> "Step2" <--> "Step3")
	   */
	  public void updateActionName() {
	    logger.info("start");
	    StringBuilder strb = new StringBuilder();
	    try {
	      se.flushStatements();
	      strb.append("#actionName(\"CellTest\" <--> \"CellTestChange\") \n");
	      Action action = mp.selectByActionId(ACTION_ID);
	      strb.append(action + "\n");

	      action.setActionName("CellTestChange");
	      mp.update(action);
	      action = mp.selectByActionId(ACTION_ID);
	      se.commit();
	      strb.append(action + "\n");

	      action.setActionName(ACTION_NAME);
	      mp.update(action);
	      action = mp.selectByActionId(ACTION_ID);
	      se.commit();
	      strb.append(action + "\n");

	      strb.append("#valid('invalid' -> 'valid')\n");
	      action.setActionStatus("valid");
	      mp.update(action);
	      action = mp.selectByActionId(ACTION_ID);
	      se.commit();
	      strb.append(action);
	      logger.info(strb.toString());
	      logger.info("end");
	    } catch(Exception e) {
	      e.printStackTrace();
	    }

	  }

	  public void deleteActionName() {
	    try{
	      logger.info("start");
	      se.flushStatements();
	      Action action = mp.selectByActionId(ACTION_ID);
	      logger.info(mp.delete(action));
	      se.commit();
	      logger.info("end");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}
