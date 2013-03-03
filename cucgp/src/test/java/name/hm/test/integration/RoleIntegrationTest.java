package name.hm.test.integration;

import name.hm.pojo.Action;
import name.hm.pojo.Group;
import name.hm.pojo.Role;
import name.hm.pojo.User;
import name.hm.pojo.Workflow;
import name.hm.test.BaseTestCase;
import name.hm.test.unit.ActionUnitTest;
import name.hm.test.unit.GroupUnitTest;
import name.hm.test.unit.RoleUnitTest;
import name.hm.test.unit.UserUnitTest;
import name.hm.test.unit.WorkflowUnitTest;

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
		try {
			openTestSession();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	@Test
	public void integrationTest()
	{
		try {
			init();
		} finally {
			clean();
		}
	}

	public void init()
	{
		try {
			Integer ret;
			logger.info("start");
			openTestSession();
			role = new Role();
			role.setRoleId(RoleUnitTest.ROLE_ID);
			role.setRoleName(RoleUnitTest.ROLE_NAME);
			role.setValid(RoleUnitTest.ROLE_VALID);
			ret = roleMapper.insert(role);
			if (ret != 1) {
				logger.error("insert Role failed");
			} else {
				logger.info("insert Role successed");
			}
			se.commit();

			group = new Group();
			group.setGroupId(GroupUnitTest.GROUP_ID);
			group.setGroupName(GroupUnitTest.GROUP_NAME);
			group.setValid("invalid");
			ret = groupMapper.insert(group);
			if (ret != 1) {
				logger.error("insert Group failed");
			} else {
				logger.info("insert Group successed");
			}
			se.commit();

			user = new User();
			user.setUserId(UserUnitTest.USER_ID);
			user.setUserName(UserUnitTest.USER_NAME);
			user.setPassword(UserUnitTest.PASSWORD);
			user.setGroupId(group.getGroupId());
			user.setValid("invalid");
			user.setUserHome(UserUnitTest.USER_HOME);
			ret = userMapper.insert(user);
			if (ret != 1) {
				logger.error("insert User failed");
			} else {
				logger.info("insert User successed");
			}
			se.commit();

			workflow = new Workflow();
			workflow.setWorkflowId(WorkflowUnitTest.WORKFLOW_ID);
			workflow.setWorkflowName(WorkflowUnitTest.WORKFLOW_NAME);
			workflow.setWorkflowStatus(WorkflowUnitTest.WORKFLOW_STATUS);
			ret = workflowMapper.insert(workflow);
			if (ret != 1) {
				logger.error("insert Workflow failed");
			} else {
				logger.info("insert Workflow successed");
			}
			se.commit();

			action = new Action();
			action.setActionId(ActionUnitTest.ACTION_ID);
			action.setActionName(ActionUnitTest.ACTION_NAME);
			action.setActionUrl(ActionUnitTest.ACTION_URL);
			action.setRoleId(role.getRoleId());
			action.setWorkflowId(workflow.getWorkflowId());
			action.setActionStatus(ActionUnitTest.ACTION_STATUS);
			ret = actionMapper.insert(action);
			if (ret != 1) {
				logger.error("insert Action failed");
			} else {
				logger.info("insert Action successed");
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void clean()
	{
		try {
			Integer ret;
			openTestSession();
			logger.info("start clean");

			if (role != null) {
				ret = roleMapper.delete(role);
				if (ret != 1) {
					logger.error("clean Role failed");
				} else {
					logger.info("clean Role successed");
				}
			} else {
				logger.warn("no Role for cleaning");
			}

			if (group != null) {
				ret = groupMapper.delete(group);
				if (ret != 1) {
					logger.error("clean Group failed");
				} else {
					logger.info("clean Group successed");
				}
			} else {
				logger.warn("no Group for cleaning");
			}

			if (user != null) {
				ret = userMapper.delete(user);
				if (ret != 1) {
					logger.error("clean User failed");
				} else {
					logger.info("clean User successed");
				}
			} else {
				logger.warn("no User for cleaning");
			}

			if (workflow != null) {
				ret = workflowMapper.delete(workflow);
				if (ret != 1) {
					logger.error("clean Workflow failed");
				} else {
					logger.info("clean Workflow successed");
				}
			} else {
				logger.warn("no Workflow for cleaning");
			}

			if (action != null) {
				ret = actionMapper.delete(action);
				if (ret != 1) {
					logger.error("clean Action failed " + ret);
				} else {
					logger.warn("clean Action successed");
				}
			} else {
				logger.warn("no Action for cleaning");
			}

			se.commit();
			se.close();
			logger.info("end clean");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
