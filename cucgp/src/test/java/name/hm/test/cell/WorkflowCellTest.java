package name.hm.test.cell;


import java.util.List;

import name.hm.jpa.WorkflowMapper;
import name.hm.pojo.Workflow;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

//MARK
/**
 * TODO 
 * test ISUD of Workflow Table
 */
public class WorkflowCellTest{

  static SqlSessionFactory factory;
  static SqlSession se = null;
  static WorkflowMapper mp = null;
  private static Logger logger = Logger.getLogger("testcell");

  static final Integer WORKFLOW_ID = 0;
  static final String WORKFLOW_NAME = "CellTest";

  @BeforeClass
    static public void init() {
      factory = EnvTest.getSqlSessionFactory();
      se = factory.openSession();
      mp = se.getMapper(WorkflowMapper.class);
    }

  @AfterClass
    static public void clean() {
      se.close();
    }

  @Test
    public void cell() {
      insertWorkflow();
      selectWorkflow();
      updateWorkflowName();
      deleteWorkflowName();
    }

  /**
   * TODO
   * insert Workflow
   * #workflowId(0)
   * #workflowName("CellTest");
   * #workflowStatus('unkown')
   */
  public void insertWorkflow() {
    logger.info("start");
    logger.info(
        "WorkflowCellTest - insertWorkflow\n" +
        " insert workflow\n" +
        " #workflowId(WORKFLOW_ID)\n" +
        " #workflowName(\"CellTest\")\n" +
        " #valid('invalid')"
        );
    try {
      se.flushStatements();
      Workflow cellTest = new Workflow();
      cellTest.setWorkflowId(WORKFLOW_ID);
      cellTest.setWorkflowName(WORKFLOW_NAME);
      cellTest.setWorkflowStatus("invalid");
      mp.insert(cellTest);
      se.commit();
      logger.info("end");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * TODO
   * select Workflow
   * #workflowId(0)
   * #workflowName("CellTest")
   * #workflowStatus("Step1") => \
   * #workflowStatus("Step2") => \
   * #workflowStatus("Step3")
   */
  public void selectWorkflow() {
    logger.info("start");
    try {
      se.flushStatements();
      Workflow workflow = mp.selectByWorkflowId(WORKFLOW_ID);
      Workflow workflow2 = mp.selectByWorkflowName(WORKFLOW_NAME);
      List<Workflow> lworkflow = mp.selectByWorkflowStatus("valid");
      List<Workflow> lworkflow2 = mp.selectByWorkflowStatus("invalid");
      se.commit();
      logger.info(workflow.toString());
      logger.info(workflow2.toString());
      logger.info(lworkflow);
      logger.info(lworkflow2);
      logger.info("end");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * TODO
   * update Workflow
   * #workflowName("CellTest" <--> "CellTestChange")
   * #workflowStatus("Step1" <--> "Step2" <--> "Step3")
   */
  public void updateWorkflowName() {
    logger.info("start");
    StringBuilder strb = new StringBuilder();
    try {
      se.flushStatements();
      strb.append("#workflowName(\"CellTest\" <--> \"CellTestChange\") \n");
      Workflow workflow = mp.selectByWorkflowId(WORKFLOW_ID);
      strb.append(workflow + "\n");

      workflow.setWorkflowName("CellTestChange");
      mp.update(workflow);
      workflow = mp.selectByWorkflowId(WORKFLOW_ID);
      se.commit();
      strb.append(workflow + "\n");

      workflow.setWorkflowName(WORKFLOW_NAME);
      mp.update(workflow);
      workflow = mp.selectByWorkflowId(WORKFLOW_ID);
      se.commit();
      strb.append(workflow + "\n");

      strb.append("#valid('invalid' -> 'valid')\n");
      workflow.setWorkflowStatus("valid");
      mp.update(workflow);
      workflow = mp.selectByWorkflowId(WORKFLOW_ID);
      se.commit();
      strb.append(workflow);
      logger.info(strb.toString());
      logger.info("end");
    } catch(Exception e) {
      e.printStackTrace();
    }

  }

  public void deleteWorkflowName() {
    try{
      logger.info("start");
      se.flushStatements();
      Workflow workflow = mp.selectByWorkflowId(WORKFLOW_ID);
      logger.info(mp.delete(workflow));
      se.commit();
      logger.info("end");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
