package name.hm.test.integration;

import java.util.ArrayList;

import name.hm.m.Action;
import name.hm.m.Group;
import name.hm.m.Role;
import name.hm.m.User;
import name.hm.m.Workflow;
import name.hm.s.UserRightService;
import name.hm.s.e.LoginException;
import name.hm.s.e.RightException;
import name.hm.test.BaseServiceTest;
import name.hm.test.BaseTestCase;
import name.hm.test.unit.ActionUnitTest;
import name.hm.test.unit.DepartmentUnitTest;
import name.hm.test.unit.GroupUnitTest;
import name.hm.test.unit.RoleUnitTest;
import name.hm.test.unit.TeacherUnitTest;
import name.hm.test.unit.TitleUnitTest;
import name.hm.test.unit.UserUnitTest;
import name.hm.test.unit.WorkflowUnitTest;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRightServiceTest extends BaseServiceTest
{
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	UserRightService userRightService;
	
	{
		userRightService = new UserRightService();
		userRightService.setSqlSessionFactory(sqlSessionFactory);
	}

	@Test
	public void test()
	{
			addTestCase();
			try {
				User user = userRightService.loadUser(UserUnitTest.U_Admin.getUserName(), UserUnitTest.U_Admin.getPassword());
				logger.info("user : " + user);				
				Group group = userRightService.loadGroup(user);
				logger.info("group : " + group);				
				ArrayList<Role> roles = userRightService.loadRoles(user);
				logger.info("roles : " + roles);				
				ArrayList<Action> actions = userRightService.loadActions(user);
				logger.info("actions : " + actions);				
			} catch (LoginException e) {
				e.printStackTrace();
			} catch (RightException e) {
				e.printStackTrace();
			}
	}

	public void addTestCase()
	{
			logger.info("start UserRightServiceTest");
	}

}
