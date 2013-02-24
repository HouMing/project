package name.hm.test;

import java.io.IOException;
import java.io.Reader;

import name.hm.jpa.ActionMapper;
import name.hm.jpa.GroupMapper;
import name.hm.jpa.RoleMapper;
import name.hm.jpa.UserMapper;
import name.hm.jpa.WorkflowMapper;
import name.hm.pojo.Action;
import name.hm.pojo.Group;
import name.hm.pojo.Role;
import name.hm.pojo.User;
import name.hm.pojo.Workflow;
import name.hm.test.BaseLogger.*;
import name.hm.test.unit.ActionUnitTest;
import name.hm.test.unit.GroupUnitTest;
import name.hm.test.unit.RoleUnitTest;
import name.hm.test.unit.UserUnitTest;
import name.hm.test.unit.WorkflowUnitTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class RoleIntegrationTest extends BaseTestCase
{
	private static Role role;
	private static Group group;
	private static User user;
	private static Workflow workflow;
	private static Action action;

	@Test
	public void sqlSessionTest()
	{
		openTestSession();
		System.out.println(se);
		closeTestSession();
	}

	@Test
	public void integrationTest()
	{
		try {
		init();
		} finally{
		clean();
		}
	}

	public void init()
	{
		Integer ret;
		INFO.isTrue("start",false);
		openTestSession();
		
		role = new Role();
		role.setRoleId(RoleUnitTest.ROLE_ID);
		role.setRoleName(RoleUnitTest.ROLE_NAME);
		role.setValid(RoleUnitTest.ROLE_VALID);
		ret = roleMapper.insert(role);
		ERROR.isTrue("insert Role failed", ret == 1);
		se.commit();

		group = new Group();
		group.setGroupId(GroupUnitTest.GROUP_ID);
		group.setGroupName(GroupUnitTest.GROUP_NAME);
		group.setValid("invalid");
		ret = groupMapper.insert(group);
		ERROR.isTrue("insert Role failed", ret == 1);
		se.commit();

		user = new User();
		user.setUserId(UserUnitTest.USER_ID);
		user.setUserName(UserUnitTest.USER_NAME);
		user.setPassword(UserUnitTest.PASSWORD);
		user.setGroupId(group.getGroupId());
		user.setValid("invalid");
		user.setUserHome(UserUnitTest.USER_HOME);
		ret = userMapper.insert(user);
		ERROR.isTrue("insert User failed", ret == 1);
		se.commit();

		workflow = new Workflow();
		workflow.setWorkflowId(WorkflowUnitTest.WORKFLOW_ID);
		workflow.setWorkflowName(WorkflowUnitTest.WORKFLOW_NAME);
		workflow.setWorkflowStatus(WorkflowUnitTest.WORKFLOW_STATUS);
		ret = workflowMapper.insert(workflow);
		ERROR.isTrue("insert Workflow failed", ret == 1);
		se.commit();

		action = new Action();
		action.setActionId(ActionUnitTest.ACTION_ID);
		action.setActionName(ActionUnitTest.ACTION_NAME);
		action.setActionUrl(ActionUnitTest.ACTION_URL);
		action.setRoleId(role.getRoleId());
		action.setWorkflowId(workflow.getWorkflowId());
		action.setActionStatus(ActionUnitTest.ACTION_STATUS);
		ret = actionMapper.insert(action);
		ERROR.isTrue("insert Action failed", ret == 1);
		se.commit();
		
		closeTestSession();
	}

	
	public void clean()
	{
		Integer ret;
		INFO.isTrue("start clean",false);
		if (role != null) {
			ret = roleMapper.delete(role);
			ERROR.isTrue("clean Role failed",ret == 1);
		}
		if (group != null) {
			ret =groupMapper.delete(group);
			ERROR.isTrue("clean Group failed",ret == 1);
		}
		if (user != null) {
			ret = userMapper.delete(user);
			ERROR.isTrue("clean User failed",ret == 1);
		}
		if (workflow != null) {
			ret = workflowMapper.delete(workflow);
			ERROR.isTrue("clean Workflow failed",ret == 1);
		}
		if (action != null) {
			ret = actionMapper.delete(action);
			ERROR.isTrue("clean Role failed",ret == 1);
		}
		se.commit();
		se.close();
		INFO.isTrue("end clean",false);
	}
}
