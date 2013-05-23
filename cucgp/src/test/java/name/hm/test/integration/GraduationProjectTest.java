package name.hm.test.integration;

import me.hm.s.ActionsManagerService;
import me.hm.s.AuthorizationService;
import me.hm.s.GroupsManagerService;
import me.hm.s.RolesManagerService;
import me.hm.s.StudentsManagerService;
import me.hm.s.TeachersManagerService;
import me.hm.s.UsersManagerService;
import me.hm.s.WorkflowsManagerService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class GraduationProjectTest {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	@Autowired
	public UsersManagerService userService;
	@Autowired
	public AuthorizationService auService;
	@Autowired
	public GroupsManagerService groupService;
	@Autowired
	public RolesManagerService roleService;
	@Autowired
	public ActionsManagerService actionService;
	@Autowired
	public StudentsManagerService studentService;
	@Autowired
	public TeachersManagerService teacherService;
	@Autowired
	public WorkflowsManagerService workflowService;
	
	

}
