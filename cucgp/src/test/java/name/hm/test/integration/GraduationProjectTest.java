package name.hm.test.integration;

import me.hm.s.ActionsService;
import me.hm.s.AuthorizationService;
import me.hm.s.GroupsService;
import me.hm.s.RolesService;
import me.hm.s.StudentService;
import me.hm.s.TeachersService;
import me.hm.s.UsersService;
import me.hm.s.WorkflowsService;

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
	public UsersService userService;
	@Autowired
	public AuthorizationService auService;
	@Autowired
	public GroupsService groupService;
	@Autowired
	public RolesService roleService;
	@Autowired
	public ActionsService actionService;
	@Autowired
	public StudentService studentService;
	@Autowired
	public TeachersService teacherService;
	@Autowired
	public WorkflowsService workflowService;
	
}
