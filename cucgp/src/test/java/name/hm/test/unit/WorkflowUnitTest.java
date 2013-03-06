package name.hm.test.unit;

import java.util.List;

import name.hm.pojo.Workflow;
import name.hm.test.BaseTestCase;

import org.junit.Test;

// PASS #0306
public class WorkflowUnitTest extends BaseTestCase
{
	static public Integer WORKFLOW_ID = null;
	static public String WORKFLOW_NAME = "测试工作流";
	static public String WORKFLOW_NAMEC = "测试工作流改";

	static public Integer WORKFLOW_ID1 = null;
	static public String WORKFLOW_NAME1 = "测试工作流1";

	static public Integer WORKFLOW_ID2 = null;
	static public String WORKFLOW_NAME2 = "测试工作流2";

	static public Workflow.STATUS WORKFLOW_STATUS1 = Workflow.getStatus("valid");
	static public Workflow.STATUS WORKFLOW_STATUS2 = Workflow.getStatus("invalid");

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
		logger.info("finish WorkflowUnitTest");
	}

	// PASS #0306
	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			Workflow workflow = new Workflow(WORKFLOW_ID, WORKFLOW_NAME, WORKFLOW_STATUS1);
			Workflow workflow1 = new Workflow(WORKFLOW_ID1, WORKFLOW_NAME1, WORKFLOW_STATUS1);
			Workflow workflow2 = new Workflow(WORKFLOW_ID2, WORKFLOW_NAME2, WORKFLOW_STATUS2);
			error = workflowMapper.insert(workflow) & error; 
			error = workflowMapper.insert(workflow1) & error; 
			error = workflowMapper.insert(workflow2) & error; 
			se.commit();
			WORKFLOW_ID = workflow.getWorkflowId();
			WORKFLOW_ID1 = workflow1.getWorkflowId();
			WORKFLOW_ID2 = workflow2.getWorkflowId();
			if (error == 1) {
				logger.info("create OK!\n" + workflow + "\n" + workflow1 + "\n" + workflow2);
			} else {
				logger.error("create failed\n" + workflow + "\n" + workflow1 + "\n" + workflow2);
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
			Workflow workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			Workflow workflow1 = workflowMapper.selectByWorkflowName(WORKFLOW_NAME);
			List<Workflow> l = workflowMapper
					.selectByWorkflowStatus(WORKFLOW_STATUS1);
			List<Workflow> l2 = workflowMapper
					.selectByWorkflowStatus(WORKFLOW_STATUS2);
			se.commit();
			if (workflow != null) {
				logger.info("selectByWorkflowId OK!\n" + workflow);
			} else {
				logger.info("selectByWorkflowId failed\n" + workflow);
			}
			if (workflow1 != null) {
				logger.info("selectByWorkflowName OK!\n" + workflow1);
			} else {
				logger.error("selectByWorkflowName failed\n" + workflow1);
			}
			if (l.size() > 0 || l2.size() > 0) {
				logger.info("selectByWorkflowStatus OK!\n" + l + "\n" + l2);
			} else {
				logger.error("selectByWorkflowStatus failed\n" + l + "\n" + l2);
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
			Workflow workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			workflow.setWorkflowStatus(WORKFLOW_STATUS2);
			error = workflowMapper.update(workflow);
			se.commit();
			if (error == 1) {
				workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
				se.commit();
				logger.info("update OK!\n" + workflow);
			} else {
				logger.error("update failed\n" + workflow);
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
			Workflow workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			Workflow workflow1 = workflowMapper.selectByWorkflowId(WORKFLOW_ID1);
			Workflow workflow2 = workflowMapper.selectByWorkflowId(WORKFLOW_ID2);
			se.commit();
			error = workflowMapper.delete(workflow) & error;
			error = workflowMapper.delete(workflow1) & error;
			error = workflowMapper.delete(workflow2) & error;
			se.commit();
			workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			workflow1 = workflowMapper.selectByWorkflowId(WORKFLOW_ID1);
			workflow2 = workflowMapper.selectByWorkflowId(WORKFLOW_ID2);
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + workflow + "\n" + workflow1 + "\n" + workflow2);
			} else {
				logger.info("delete failed\n" + workflow + "\n" + workflow1 + "\n" + workflow2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
