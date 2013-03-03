package name.hm.test.unit;

import java.util.List;

import name.hm.jpa.WorkflowMapper;
import name.hm.pojo.Workflow;
import name.hm.test.BaseTestCase;
import name.hm.test.integration.RoleIntegrationTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * test ISUD of Workflow Table
 */
@Deprecated
public class WorkflowUnitTest extends BaseTestCase
{
	public static Integer WORKFLOW_ID = 0;
	public static String WORKFLOW_NAME = "CellTest";
	public static String WORKFLOW_STATUS = "valid";

	@Test
	public void test()
	{
		insertWorkflow();
		selectWorkflow();
		updateWorkflowName();
		deleteWorkflowName();
	}

	public void insertWorkflow()
	{
		try {
			openTestSession();
			logger.info("start");
			Workflow cellTest = new Workflow();
			cellTest.setWorkflowId(WORKFLOW_ID);
			cellTest.setWorkflowName(WORKFLOW_NAME);
			cellTest.setWorkflowStatus(WORKFLOW_STATUS);
			workflowMapper.insert(cellTest);
			se.commit();
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void selectWorkflow()
	{
		logger.info("start");
		try {
			se.flushStatements();
			Workflow workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			Workflow workflow2 = workflowMapper.selectByWorkflowName(WORKFLOW_NAME);
			List<Workflow> lworkflow = workflowMapper
					.selectByWorkflowStatus(WORKFLOW_STATUS);
			List<Workflow> lworkflow2 = workflowMapper
					.selectByWorkflowStatus(WORKFLOW_STATUS + "2");
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

	public void updateWorkflowName()
	{
		logger.info("start");
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
			logger.info(strb.toString());
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteWorkflowName()
	{
		try {
			logger.info("start");
			se.flushStatements();
			Workflow workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			logger.info(workflowMapper.delete(workflow));
			se.commit();
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
