package name.hm.test.integration;

import name.hm.m.Action;
import name.hm.m.Group;
import name.hm.m.Role;
import name.hm.m.User;
import name.hm.m.Workflow;
import name.hm.test.BaseTestCase;
import name.hm.test.unit.ActionUnitTest;
import name.hm.test.unit.DepartmentUnitTest;
import name.hm.test.unit.GroupUnitTest;
import name.hm.test.unit.RoleUnitTest;
import name.hm.test.unit.TeacherUnitTest;
import name.hm.test.unit.TitleUnitTest;
import name.hm.test.unit.UserUnitTest;
import name.hm.test.unit.WorkflowUnitTest;

import org.junit.Test;

public class UserIntegrationTest extends BaseTestCase
{

	@Test
	public void test()
	{
			beforeTest();
			afterTest();
	}

	// PASS CELL #0307
	public void beforeTest()
	{
		try {
			Integer error = 1;
			logger.info("start RoleIntegrationTest");
			ActionUnitTest actionUnit = new ActionUnitTest();
			DepartmentUnitTest departmentUnit = new DepartmentUnitTest();
			GroupUnitTest groupUnit = new GroupUnitTest();
			RoleUnitTest roleUnit = new RoleUnitTest();
			TeacherUnitTest teacherUnit = new TeacherUnitTest();
			TitleUnitTest titleUnit = new TitleUnitTest();
			UserUnitTest userUnit = new UserUnitTest();
			WorkflowUnitTest workflowUnit = new WorkflowUnitTest();
			
			groupUnit.create();
			roleUnit.create();
			actionUnit.create();
			userUnit.create();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0307
	public void afterTest()
	{
		try {
			Group error;
			logger.info("start RoleIntegrationTest");
			ActionUnitTest actionUnit = new ActionUnitTest();
			DepartmentUnitTest departmentUnit = new DepartmentUnitTest();
			GroupUnitTest groupUnit = new GroupUnitTest();
			RoleUnitTest roleUnit = new RoleUnitTest();
			TeacherUnitTest teacherUnit = new TeacherUnitTest();
			TitleUnitTest titleUnit = new TitleUnitTest();
			UserUnitTest userUnit = new UserUnitTest();
			WorkflowUnitTest workflowUnit = new WorkflowUnitTest();
			
			actionUnit.delete();
			departmentUnit.delete();
			groupUnit.delete();
			roleUnit.delete();
			teacherUnit.delete();
			titleUnit.delete();
			userUnit.delete();
			workflowUnit.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
