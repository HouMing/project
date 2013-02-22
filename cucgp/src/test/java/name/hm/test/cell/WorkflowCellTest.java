package name.hm.test.cell;

import name.hm.test.CellTest;

import org.junit.Test;
import org.junit.experimental.categories.Category;

//MARK
/**
 * TODO 
 * test ISUD of Workflow Table
 */
@Category(CellTest.class)
public class WorkflowCellTest{

  /**
   * TODO
   * insert Workflow
   * #workflowId(0)
   * #workflowName("CellTest");
   * #workflowStatus('unkown')
   */
	@Test
  public void insertWorkflow() {
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
  }

  /**
   * TODO
   * update Workflow
   * #workflowName("CellTest" <--> "CellTestChange")
   * #workflowStatus("Step1" <--> "Step2" <--> "Step3")
   */
  public void changeWorkflowName() {
  }
}
