package name.hm.test.integration;

import name.hm.pojo.Action;
import name.hm.pojo.Group;
import name.hm.pojo.Role;
import name.hm.pojo.User;
import name.hm.pojo.Workflow;
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

// TODO INTEGRATION #0307
public class RoleIntegrationTest extends BaseTestCase
{
	private static Role role;
	private static Group group;
	private static User user;
	private static Workflow workflow;
	private static Action action;

	@Test
	public void test()
	{
			beforeTest();
			afterTest();
	}

	// TODO CELL #0307
	public void beforeTest()
	{
		try {
			Integer error;
			logger.info("start RoleIntegrationTest");
			ActionUnitTest actionUnit = new ActionUnitTest();
			DepartmentUnitTest departmentUnit = new DepartmentUnitTest();
			GroupUnitTest groupUnit = new GroupUnitTest();
			RoleUnitTest roleUnit = new RoleUnitTest();
			TeacherUnitTest teacherUnit = new TeacherUnitTest();
			TitleUnitTest titleUnit = new TitleUnitTest();
			UserUnitTest userUnit = new UserUnitTest();
			WorkflowUnitTest workflowUnit = new WorkflowUnitTest();
			
			workflowUnit.create();
			actionUnit.create();
			departmentUnit.create();
			groupUnit.create();
			roleUnit.create();
			teacherUnit.create();
			titleUnit.create();
			userUnit.create();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// TODO CELL #0307
	public void afterTest()
	{
		try {
			Integer error;
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
