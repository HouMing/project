package name.hm.test.unit;

import java.util.List;

import name.hm.jpa.WorkflowMapper;
import name.hm.pojo.Workflow;
import name.hm.test.BaseTestCase;
import name.hm.test.RoleIntegrationTest;
import name.hm.test.BaseLogger.INFO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

//MARK
/**
 * test ISUD of Workflow Table
 */
public class WorkflowUnitTest extends BaseTestCase
{
	public static final Integer WORKFLOW_ID = 0;
	public static final String WORKFLOW_NAME = "CellTest";
	public static final String WORKFLOW_STATUS = "valid";

	@Test
	public void test()
	{
		insertWorkflow();
		selectWorkflow();
		updateWorkflowName();
		deleteWorkflowName();
	}

	/**
	 * insert Workflow
	 */
	public void insertWorkflow()
	{
		try {
			openTestSession();
			INFO.isTrue("start", false);
			Workflow cellTest = new Workflow();
			cellTest.setWorkflowId(WORKFLOW_ID);
			cellTest.setWorkflowName(WORKFLOW_NAME);
			cellTest.setWorkflowStatus(WORKFLOW_STATUS);
			workflowMapper.insert(cellTest);
			se.commit();
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	/**
	 * select Workflow #workflowId(0) #workflowName("CellTest")
	 * #workflowStatus("Step1") => \ #workflowStatus("Step2") => \
	 * #workflowStatus("Step3")
	 */
	public void selectWorkflow()
	{
		INFO.isTrue("start");
		try {
			se.flushStatements();
			Workflow workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			Workflow workflow2 = workflowMapper.selectByWorkflowName(WORKFLOW_NAME);
			List<Workflow> lworkflow = workflowMapper
					.selectByWorkflowStatus(WORKFLOW_STATUS);
			List<Workflow> lworkflow2 = workflowMapper
					.selectByWorkflowStatus(WORKFLOW_STATUS + "2");
			se.commit();
			INFO.isTrue(workflow.toString());
			INFO.isTrue(workflow2.toString());
			INFO.isTrue(lworkflow);
			INFO.isTrue(lworkflow2);
			INFO.isTrue("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * update Workflow #workflowName("CellTest" <--> "CellTestChange")
	 * #workflowStatus("Step1" <--> "Step2" <--> "Step3")
	 */
	public void updateWorkflowName()
	{
		INFO.isTrue("start");
		StringBuilder strb = new StringBuilder();
		try {
			se.flushStatements();
			strb.append("#workflowName(\"CellTest\" <--> \"CellTestChange\") \n");
			Workflow workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			strb.append(workflow + "\n");

			workflow.setWorkflowName("CellTestChange");
			workflowMapper.update(workflow);
			workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			se.commit();
			strb.append(workflow + "\n");

			workflow.setWorkflowName(WORKFLOW_NAME);
			workflowMapper.update(workflow);
			workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			se.commit();
			strb.append(workflow + "\n");

			strb.append("#valid('valid' -> 'invalid')\n");
			workflow.setWorkflowStatus("in" + WORKFLOW_STATUS);
			workflowMapper.update(workflow);
			workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			se.commit();
			strb.append(workflow);
			INFO.isTrue(strb.toString());
			INFO.isTrue("end");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteWorkflowName()
	{
		try {
			INFO.isTrue("start");
			se.flushStatements();
			Workflow workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			INFO.isTrue(workflowMapper.delete(workflow));
			se.commit();
			INFO.isTrue("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
