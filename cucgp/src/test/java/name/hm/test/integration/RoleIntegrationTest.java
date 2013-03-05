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
			beforeTest();
		} finally {
			afterTest();
		}
	}

	public void beforeTest()
	{
		try {
			Integer ret;
			logger.info("start");
			openTestSession();
			role = new Role(RoleUnitTest.ROLE_ID, RoleUnitTest.ROLE_NAME,
					Role.VALID(RoleUnitTest.ROLE_VALID));
			ret = roleMapper.insert(role);
			if (ret != 1) {
				logger.error("insert Role failed");
			} else {
				logger.info("insert Role OK!");
			}
			se.commit();

			group = new Group(GroupUnitTest.GROUP_ID, GroupUnitTest.GROUP_NAME,
					Group.VALID(GroupUnitTest.GROUP_VALID));
			ret = groupMapper.insert(group);
			if (ret != 1) {
				logger.error("insert Group failed" + group);
			} else {
				logger.info("insert Group OK!");
			}
			se.commit();

			user = new User(UserUnitTest.USER_ID, UserUnitTest.USER_NAME,
					UserUnitTest.PASSWORD, UserUnitTest.USER_HOME,
					User.VALID(UserUnitTest.USER_VALID), GroupUnitTest.GROUP_ID);
			ret = userMapper.insert(user);
			if (ret != 1) {
				logger.error("insert User failed");
			} else {
				logger.info("insert User OK!");
			}
			se.commit();

			workflow = new Workflow(WorkflowUnitTest.WORKFLOW_ID,
					WorkflowUnitTest.WORKFLOW_NAME, Workflow.getStatus("valid"));
			ret = workflowMapper.insert(workflow);
			if (ret != 1) {
				logger.error("insert Workflow failed");
			} else {
				logger.info("insert Workflow OK!");
			}
			se.commit();

			action = new Action(ActionUnitTest.ACTION_ID, ActionUnitTest.ACTION_NAME,
					ActionUnitTest.ACTION_URL, ActionUnitTest.ACTION_VALID,
					role.getRoleId(), workflow.getWorkflowId());
			ret = actionMapper.insert(action);
			if (ret != 1) {
				logger.error("insert Action failed");
			} else {
				logger.info("insert Action OK!");
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void afterTest()
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
					logger.info("clean Role OK!");
				}
			} else {
				logger.warn("no Role");
			}

			if (group != null) {
				ret = groupMapper.delete(group);
				if (ret != 1) {
					logger.error("clean Group failed");
				} else {
					logger.info("clean Group OK!");
				}
			} else {
				logger.warn("no Group");
			}

			if (user != null) {
				ret = userMapper.delete(user);
				if (ret != 1) {
					logger.error("clean User failed");
				} else {
					logger.info("clean User OK!");
				}
			} else {
				logger.warn("no User");
			}

			if (workflow != null) {
				ret = workflowMapper.delete(workflow);
				if (ret != 1) {
					logger.error("clean Workflow failed");
				} else {
					logger.info("clean Workflow OK!");
				}
			} else {
				logger.warn("no Workflow");
			}

			if (action != null) {
				ret = actionMapper.delete(action);
				if (ret != 1) {
					logger.error("clean Action failed " + ret);
				} else {
					logger.warn("clean Action OK!");
				}
			} else {
				logger.warn("no Action");
			}

			se.commit();
			se.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
