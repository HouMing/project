package name.hm.test.integration;

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
import name.hm.test.unit.ActionUnitTest;
import name.hm.test.unit.GroupUnitTest;
import name.hm.test.unit.RoleUnitTest;
import name.hm.test.unit.UserUnitTest;
import name.hm.test.unit.WorkflowUnitTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

public class RoleIntegrationTest
{

	private static final Logger logger = Logger.getLogger("testcell");

	private static SqlSessionFactory sqlSessionFactory = null;
	private static SqlSession se = null;

	private static Role role;
	private static Group group;
	private static User user;
	private static Workflow workflow;
	private static Action action;

	private static RoleMapper roleMapper;
	private static GroupMapper groupMapper;
	private static UserMapper userMapper;
	private static WorkflowMapper workflowMapper;
	private static ActionMapper actionMapper;


	static {
		String resource = "appContext-MyBATIS.xml";
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				sqlSessionFactory.getConfiguration().addMapper(GroupMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(RoleMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(WorkflowMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ActionMapper.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory()
	{
		return sqlSessionFactory;
	}

	@Test
	public void sqlSessionTest()
	{
		SqlSession se = getSqlSessionFactory().openSession();
		System.out.println(se);
		se.commit();
		se.close();
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

	public static void init()
	{
		logger.info("start");
		se = sqlSessionFactory.openSession();
		roleMapper = se.getMapper(RoleMapper.class);
		groupMapper = se.getMapper(GroupMapper.class);
		userMapper = se.getMapper(UserMapper.class);
		workflowMapper = se.getMapper(WorkflowMapper.class);
		actionMapper = se.getMapper(ActionMapper.class);

		role = new Role();
		role.setRoleId(RoleUnitTest.ROLE_ID);
		role.setRoleName(RoleUnitTest.ROLE_NAME);
		role.setValid(RoleUnitTest.ROLE_VALID);
		logger.info(role);
		roleMapper.insert(role);
		se.commit();

		group = new Group();
		group.setGroupId(GroupUnitTest.GROUP_ID);
		group.setGroupName(GroupUnitTest.GROUP_NAME);
		group.setValid("invalid");
		groupMapper.insert(group);
		se.commit();

		user = new User();
		user.setUserId(UserUnitTest.USER_ID);
		user.setUserName(UserUnitTest.USER_NAME);
		user.setPassword(UserUnitTest.PASSWORD);
		user.setGroupId(group.getGroupId());
		user.setValid("invalid");
		user.setUserHome(UserUnitTest.USER_HOME);
		userMapper.insert(user);
		se.commit();

		workflow = new Workflow();
		workflow.setWorkflowId(WorkflowUnitTest.WORKFLOW_ID);
		workflow.setWorkflowName(WorkflowUnitTest.WORKFLOW_NAME);
		workflow.setWorkflowStatus(WorkflowUnitTest.WORKFLOW_STATUS);
		workflowMapper.insert(workflow);
		se.commit();

		action = new Action();
		action.setActionId(ActionUnitTest.ACTION_ID);
		action.setActionName(ActionUnitTest.ACTION_NAME);
		action.setActionUrl(ActionUnitTest.ACTION_URL);
		action.setRoleId(role.getRoleId());
		action.setWorkflowId(workflow.getWorkflowId());
		action.setActionStatus(ActionUnitTest.ACTION_STATUS);
		logger.info(action);
		actionMapper.insert(action);
		se.commit();
	}

	public void clean()
	{
		logger.info("start clean");
		if (role != null) {
			Integer ret = roleMapper.delete(role);
			if (ret > 0) {
				logger.info("delete test Role");
			}
		}
		if (group != null) {
			Integer ret =groupMapper.delete(group);
			if (ret > 0) {
				logger.info("delete test Group");
			}
		}
		if (user != null) {
			Integer ret = userMapper.delete(user);
			if (ret > 0) {
				logger.info("delete test User");
			}
		}
		if (workflow != null) {
			Integer ret = workflowMapper.delete(workflow);
			if (ret > 0) {
				logger.info("delete test Workflow");
			}
		}
		if (action != null) {
			Integer ret = actionMapper.delete(action);
			if (ret > 0) {
				logger.error("delete test Action, the action should be cascading deleted");
			}
		}
		se.commit();
		se.close();
		logger.info("end clean");
	}

}
