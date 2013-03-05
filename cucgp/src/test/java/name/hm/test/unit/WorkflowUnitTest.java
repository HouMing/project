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
public class WorkflowUnitTest extends BaseTestCase
{
	public static Integer WORKFLOW_ID = 0;
	public static Integer WORKFLOW_ID1 = 1;
	public static Integer WORKFLOW_ID2 = 2;
	public static Integer WORKFLOW_ID3 = 3;
	public static String WORKFLOW_NAME = "测试工作流";
	public static String WORKFLOW_NAME1 = "测试工作流1";
	public static String WORKFLOW_NAME11 = "测试工作流1改";
	public static String WORKFLOW_NAME2 = "测试工作流2";
	public static Workflow.STATUS WORKFLOW_STATUS1 = Workflow.getStatus("valid");
	public static Workflow.STATUS WORKFLOW_STATUS2 = Workflow.getStatus("invalid");

	@Test
	public void test()
	{
		beforeTest();
		create();
		selectWorkflow();
		updateWorkflowName();
		deleteWorkflowName();
		afterTest();
	}

	private void afterTest()
	{
		logger.info("start");
	}

	private void beforeTest()
	{
		logger.info("end");
	}

	// FINISH #0001
	public void create()
	{
		try {
			Integer ret;
			openTestSession();
			Workflow workflow = new Workflow(WORKFLOW_ID, WORKFLOW_NAME, WORKFLOW_STATUS1);
			ret = workflowMapper.insert(workflow);
			if (ret == 1) {
				logger.info("create OK! : " + workflow);
			} else {
				logger.error("create fialed : " + workflow);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void selectWorkflow()
	{
		try {
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
