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

// PASS #0306
public class WorkflowUnitTest extends BaseTestCase
{
	public static Integer WORKFLOW_ID = 0;
	public static Integer WORKFLOW_ID1 = 1;
	public static Integer WORKFLOW_ID2 = 2;
	public static Integer WORKFLOW_ID3 = 3;
	public static String WORKFLOW_NAME = "测试工作流";
	public static String WORKFLOW_NAMEC = "测试工作流改";
	public static String WORKFLOW_NAME1 = "测试工作流1";
	public static String WORKFLOW_NAME2 = "测试工作流2";
	public static Workflow.STATUS WORKFLOW_STATUS1 = Workflow.getStatus("valid");
	public static Workflow.STATUS WORKFLOW_STATUS2 = Workflow.getStatus("invalid");

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
			Integer ret;
			openTestSession();
			Workflow workflow = new Workflow(WORKFLOW_ID, WORKFLOW_NAME, WORKFLOW_STATUS1);
			ret = workflowMapper.insert(workflow);
			if (ret == 1) {
				logger.info("create OK!\n" + workflow);
			} else {
				logger.error("create fialed\n" + workflow);
			}
			se.commit();
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
			Workflow workflow2 = workflowMapper.selectByWorkflowName(WORKFLOW_NAME);
			List<Workflow> l = workflowMapper.selectByWorkflowStatus(WORKFLOW_STATUS1);
			List<Workflow> l2 = workflowMapper.selectByWorkflowStatus(WORKFLOW_STATUS2);
			se.commit();
			if (workflow != null) {
				logger.info("selectByWorkflowId OK!\n" + workflow);
			} else {
				logger.info("selectByWorkflowId failed\n" + workflow);
			}
			if (workflow2 != null) {
				logger.info("selectByWorkflowName OK!\n" + workflow2);
			} else {
				logger.error("selectByWorkflowName failed\n" + workflow2);
			}
			if (l.size() > 0 || l2.size() > 0) {
				logger.info("selectByWorkflowStatus OK!\n" + l + l2);
			} else {
				logger.error("selectByWorkflowStatus failed\n" + l + l2);
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
			Integer error;
			openTestSession();
			Workflow workflow = workflowMapper.selectByWorkflowId(WORKFLOW_ID);
			se.commit();
			error = workflowMapper.delete(workflow);
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + workflow);
			} else {
				logger.error("delete failed\n" + workflow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
