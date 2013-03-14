package name.hm.test.unit;

import java.util.LinkedList;
import java.util.List;

import name.hm.m.Workflow;
import name.hm.test.BaseTestCase;

import org.junit.Test;

// PASS #0306
//TODO 4 PASS, Upgrade - task : #0310
public class WorkflowUnitTest extends BaseTestCase
{
  public static Workflow WORKFLOW_TEST = null;
  protected static final String WORKFLOW_NAME = "2013年毕设";
  protected static final String WORKFLOW_NAMEC = "2013年毕设改";

  public static Workflow WORKFLOW_2012 = null;
  protected static final String WORKFLOW_NAME1 = "2012年毕设";

  public static Workflow WORKFLOW_2011 = null;
  protected static final String WORKFLOW_NAME2 = "2011年毕设";

  public static Workflow.Status WORKFLOW_STATUS_APPLYING = Workflow.APPLYING;
  public static Workflow.Status WORKFLOW_STATUS_PUBLISHING = Workflow.PUBLISHING;

  @Test
    public void test()
    {
      try {
        beforeTest();
        create();
        read();
        update();
        delete();
        afterTest();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  private void beforeTest()
  {
    logger.info("start WorkflowUnitTest");
  }

  private void afterTest()
  {
    clean();
    logger.info("finish WorkflowUnitTest");
  }

  // PASS #0306
  public void create()
  {
    try {
      Integer error = 1;
      openTestSession();
      WORKFLOW_TEST = new Workflow(WORKFLOW_NAME, WORKFLOW_STATUS_APPLYING);
      WORKFLOW_2012 = new Workflow(WORKFLOW_NAME1, WORKFLOW_STATUS_APPLYING);
      WORKFLOW_2011 = new Workflow(WORKFLOW_NAME2, WORKFLOW_STATUS_APPLYING);
      error = workflowMapper.insert(WORKFLOW_TEST) & error; 
      error = workflowMapper.insert(WORKFLOW_2012) & error; 
      error = workflowMapper.insert(WORKFLOW_2011) & error; 
      se.commit();
      if (error == 1) {
        logger.info("create OK!\n" + WORKFLOW_TEST + "\n" + WORKFLOW_2012 + "\n" + WORKFLOW_2011);
      } else {
        logger.error("create failed\n" + WORKFLOW_TEST + "\n" + WORKFLOW_2012 + "\n" + WORKFLOW_2011);
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
      Integer error;
      openTestSession();
      Workflow workflow_2013 = workflowMapper.selectByWorkflowId(WORKFLOW_TEST.getWorkflowId());
      Workflow workflow_2012 = workflowMapper.selectByWorkflowName(WORKFLOW_2012.getWorkflowName());
      List<Workflow> l = workflowMapper.selectByWorkflowStatus(Workflow.APPLYING);
      se.commit();
      if (workflow_2013 != null) {
        logger.info("selectByWorkflowId OK!\n" + workflow_2013);
      } else {
        logger.info("selectByWorkflowId failed\n" + WORKFLOW_TEST);
      }
      if (workflow_2012 != null) {
        logger.info("selectByWorkflowName OK!\n" + workflow_2012);
      } else {
        logger.error("selectByWorkflowName failed\n" + WORKFLOW_2012);
      }
      if (l.size() > 0 ) {
        logger.info("selectByWorkflowStatus OK!\n" + l);
      } else {
        logger.error("selectByWorkflowStatus failed\n" + l);
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
      WORKFLOW_TEST.setWorkflowStatus(WORKFLOW_STATUS_PUBLISHING);
      error = workflowMapper.update(WORKFLOW_TEST);
      se.commit();
      WORKFLOW_TEST = workflowMapper.selectByWorkflowId(WORKFLOW_TEST.getWorkflowId());
      se.commit();
      if (error == 1) {
        logger.info("update OK!\n" + WORKFLOW_TEST);
      } else {
        logger.error("update failed\n" + WORKFLOW_TEST);
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
      Workflow workflow_2013 = workflowMapper.selectByWorkflowId(WORKFLOW_TEST.getWorkflowId());
      Workflow workflow_2012 = workflowMapper.selectByWorkflowId(WORKFLOW_2012.getWorkflowId());
      Workflow workflow_2011 = workflowMapper.selectByWorkflowId(WORKFLOW_2011.getWorkflowId());
      se.commit();
      error = workflowMapper.delete(workflow_2013) & error;
      error = workflowMapper.delete(workflow_2012) & error;
      error = workflowMapper.delete(workflow_2011) & error;
      se.commit();
      if (error == 1) {
        logger.info("delete OK!\n" + workflow_2013 + "\n" + workflow_2012 + "\n" + workflow_2011);
      } else {
        logger.info("delete failed\n" + workflow_2013 + "\n" + workflow_2012 + "\n" + workflow_2011);
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
      LinkedList<Workflow> l = workflowMapper.selectByWorkflowStatus(Workflow.APPLYING);
      LinkedList<Workflow> l2 = workflowMapper.selectByWorkflowStatus(Workflow.PUBLISHING);
      se.commit();
      for (Workflow tmp : l) {
        workflowMapper.delete(tmp);
      }
      for (Workflow tmp : l2) {
        workflowMapper.delete(tmp);
      }
      se.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeTestSession();
    }
  }

}
