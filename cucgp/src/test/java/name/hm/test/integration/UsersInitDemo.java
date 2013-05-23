package name.hm.test.integration;

//{{{
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import me.hm.m.Action;
import me.hm.m.Group;
import me.hm.m.Role;
import me.hm.m.Student;
import me.hm.m.Teacher;
import me.hm.m.User;
import me.hm.m.Workflow;
import me.hm.s.ActionsManagerService;
import me.hm.s.AuthorizationService;
import me.hm.s.GroupsManagerService;
import me.hm.s.RolesManagerService;
import me.hm.s.StudentsManagerService;
import me.hm.s.TeachersManagerService;
import me.hm.s.UsersManagerService;
import me.hm.s.WorkflowsManagerService;
import name.hm.test.BaseTestCase;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

//}}}

  @RunWith(SpringJUnit4ClassRunner.class)
  @TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
  @ContextConfiguration(locations = { "classpath:test.xml" })
  @TransactionConfiguration(defaultRollback = true)
  public class UsersInitDemo extends BaseTestCase {
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

    public Group gpL = null;
    public Group gpA = null;
    public Group gpT = null;
    public Group gpS = null;

    List<User> users = new ArrayList<User>();
    public Map<String, Group> groups = new HashMap<String, Group>();
    public Map<String, Role> roles = new HashMap<String, Role>();
    public Map<String, Action> actions = new HashMap<String, Action>();

    public Set<User> allUser = new TreeSet<User>();
    public Map<String, User> admin = new HashMap<String, User>();
    public Map<String, User> assist = new HashMap<String, User>();

    public Map<String, User> teacher = new HashMap<String, User>();

    public Map<String, User> student = new HashMap<String, User>();

    public Map<User, Student> ums = new HashMap<User, Student>();
    public Map<User, Teacher> umt = new HashMap<User, Teacher>();
    
    public List<Workflow> workflows = new ArrayList<Workflow>();

    @Test
    public void in() {
      insertGroups();
      insertRoles();
      insertActions();
      insertWorkflow();

      insertLeader();
      insertAssistent();
      insertTeacher();
      insertStudent();

      System.out.println("插入组信息：");
      Set<String> keys = groups.keySet();
      for (String key : keys) {
        groupService.syncGroup(groups.get(key));
        System.out.println(groups.get(key));
      }

      System.out.println("插入角色信息：");
      keys = roles.keySet();
      for (String key : keys) {
        roleService.syncRole(roles.get(key));
        System.out.println(roles.get(key));
      }

      System.out.println("插入权限信息：");
      keys = actions.keySet();
      for (String key : keys) {
        actionService.syncAction(actions.get(key));
        System.out.println(actions.get(key));
      }


      System.out.println("插入人员信息：");
      for (User user : users) {
    	  System.out.println(user);
      }

      System.out.println("插入工作流信息：");
      for (Workflow workflow : workflows) {
    	  System.out.println(workflow);
      }
    }

    private void insertWorkflow() {
    	Workflow workflow = new Workflow("09级工学院流程",0);
    	Workflow workflow2 = new Workflow("09级工学院广电工流程",0);
    	Workflow workflow3 = new Workflow("09级工学院自动化流程",0);
    	Workflow workflow4 = new Workflow("09级工学院数媒流程",0);
    	workflowService.insertWorkflow(workflow);
    	workflowService.insertWorkflow(workflow2);
    	workflowService.insertWorkflow(workflow3);
    	workflowService.insertWorkflow(workflow4);
    	workflows.add(workflow);
    	workflows.add(workflow2);
    	workflows.add(workflow3);
    	workflows.add(workflow4);
	}

	private void insertActions() {
      Action idLogout = new Action("idLogout", "Cucgp.view.Logout", "退出系统");
      Action idUsers = new Action("idUsers", "Cucgp.view.Users", "账户信息管理");
      Action idStudents = new Action("idStudents", "Cucgp.view.Students", "学生信息管理");
      Action idTeachers = new Action("idTeachers", "Cucgp.view.Teachers", "教师信息管理");
      Action idTeacher = new Action("idTeacher", "Cucgp.view.Teacher", "个人信息管理");
      Action idStudent = new Action("idStudent", "Cucgp.view.Student", "个人信息管理");
      Action idUser = new Action("idUser", "Cucgp.view.User", "账户管理");
      Action idWelcome = new Action("idWelcome", "Cucgp.view.Welcome", "首页");
      Action idWorkflow = new Action("idWorkflows", "Cucgp.view.Workflows", "流程管理");
      actions.put("idLogout", idLogout);
      actions.put("idUsers", idUsers);
      actions.put("idStudents", idStudents);
      actions.put("idTeachers", idTeachers);
      actions.put("idTeacher", idTeacher);
      actions.put("idStudent", idStudent);
      actions.put("idUser", idUser);
      actions.put("idWelcome", idWelcome);
      actions.put("idWorkflow", idWorkflow);
      Set<String> keys = actions.keySet();
      for (String key : keys ) {
        actionService.insertAction(actions.get(key));
      }
      //@ Guest
      idLogout.getRoles().add(roles.get("rGuest").getRoleId());
      idWelcome.getRoles().add(roles.get("rGuest").getRoleId());

      idUsers.getRoles().add(roles.get("rAdmin").getRoleId());
      idStudents.getRoles().add(roles.get("rAdmin").getRoleId());
      idTeachers.getRoles().add(roles.get("rAdmin").getRoleId());

      idTeacher.getRoles().add(roles.get("rTeacher").getRoleId());
      idStudent.getRoles().add(roles.get("rStudent").getRoleId());

      idUser.getRoles().add(roles.get("rGuest").getRoleId());
      keys = actions.keySet();
      for (String key : keys) {
        actionService.updateAction(actions.get(key));
      }

    }

    private void insertRoles() {
      Role rAdmin = new Role("管理员");
      Role rTeacher = new Role("教师");
      Role rStudent = new Role("学生");
      Role rGuest = new Role("访客");
      roles.put("rAdmin", rAdmin);
      roles.put("rTeacher", rTeacher);
      roles.put("rStudent", rStudent);
      roles.put("rGuest", rGuest);
      Set<String> keys = roles.keySet();
      for (String key : keys) {
        roleService.insertRole(roles.get(key));
      }

      gpL.getRoles().add(rAdmin.getRoleId());
      gpL.getRoles().add(rTeacher.getRoleId());
      gpL.getRoles().add(rGuest.getRoleId());

      gpA.getRoles().add(rAdmin.getRoleId());
      gpA.getRoles().add(rGuest.getRoleId());

      gpT.getRoles().add(rTeacher.getRoleId());
      gpT.getRoles().add(rGuest.getRoleId());

      gpS.getRoles().add(rStudent.getRoleId());
      gpS.getRoles().add(rGuest.getRoleId());

      keys = groups.keySet();
      for (String key : keys) {
        groupService.updateGroup(groups.get(key));
      }
    }

    private void insertStudent() {
      Student zhang1 = new Student(null, gpS.getGroupId(), "200910013471", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013471") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Student zhang2 = new Student(null, gpS.getGroupId(), "200910013472", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013472") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Student zhang3 = new Student(null, gpS.getGroupId(), "200910013473", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013473") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Student zhang4 = new Student(null, gpS.getGroupId(), "200910013474", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013474") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Student zhang5 = new Student(null, gpS.getGroupId(), "200910013475", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013475") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Student zhang6 = new Student(null, gpS.getGroupId(), "200910013476", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013476") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Student zhang7 = new Student(null, gpS.getGroupId(), "200910013477", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013477") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Student zhang8 = new Student(null, gpS.getGroupId(), "200910013478", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013478") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Student zhang9 = new Student(null, gpS.getGroupId(), "200910013479", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013479") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      
  //    Student zhang10 = new Student(null, gpS.getGroupId(), "200910013479", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("200910013479") + "/", "张1", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
 //     users.add(zhang10);
      
      users.add(zhang1);
      users.add(zhang2);
      users.add(zhang3);
      users.add(zhang4);
      users.add(zhang5);
      users.add(zhang6);
      users.add(zhang7);
      users.add(zhang8);
      users.add(zhang9);
      for (User user : users) {
    	if(user instanceof Student)
         studentService.insertStudent((Student)user);
      }
      for (User user : users) {
        if(user instanceof Student) {
          studentService.updateStudent((Student)user);
        }
      }

    }

    private void insertTeacher() {
      Teacher yangshuang = new Teacher(null, gpT.getGroupId(), "1900", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1900") + "/", "杨", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Teacher shidongxin = new Teacher(null, gpT.getGroupId(), "1901", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1901") + "/", "石", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);
      Teacher wenhui = new Teacher(null, gpT.getGroupId(), "1902", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1902") + "/", "文", "123456789", "123456789@qq.com", "@123456789", "个人介绍", null, null);

      teacher.put("yangshuang", yangshuang);
      teacher.put("shidongxin", shidongxin);
      teacher.put("wenhui", wenhui);
      users.add(yangshuang);
      users.add(shidongxin);
      users.add(wenhui);
      Set<String> keys = teacher.keySet();
      for (String key : keys) {
        userService.insertUser(teacher.get(key));
      }

    }

    private void insertAssistent() {
      User zhong = new User(null, gpA.getGroupId(), "1500", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1500") + "/");
      zhong.setGroupId(gpA.getGroupId());
      admin.put("zhong", zhong);
      userService.insertUser(zhong);
      allUser.add(zhong);
    }

    protected void insertLeader() {
      User jiang= new User(null, gpL.getGroupId(), "1000", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1000") + "/");
      User shi = new User(null, gpL.getGroupId(), "1001", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1001") + "/");
      User yang = new User(null, gpL.getGroupId(), "1002", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1002") + "/");
      User meng = new User(null, gpL.getGroupId(), "1003", DigestUtils.md5Hex("123456"), "/" + DigestUtils.md5Hex("1003") + "/");
      admin.put("jiang", jiang);
      admin.put("shi", shi);
      admin.put("yang", yang);
      admin.put("meng", meng);
      Set<String> keySet = admin.keySet() ;
      for (String str : keySet) {
        userService.insertUser(admin.get(str));
        allUser.add(admin.get(str));
      }
    }

    protected void insertGroups() {
      gpL = new Group("系主任");
      gpA = new Group("教秘");
      gpT = new Group("教师");
      gpS = new Group("学生");
      groups.put("gLeader", gpL);
      groups.put("gAssit", gpA);
      groups.put("gTeacher", gpT);
      groups.put("gStudent", gpS);
      Set<String> keys = groups.keySet();
      for (String key : keys) {
        groupService.insertGroup(groups.get(key));
      }
    }

   @Test
    public void clean() {
      List<User> users = userService.readUsers();
      for (User user : users) {
        userService.deleteUser(user);
      }
      List<Group> groups = groupService.readGroups();
      for (Group group : groups) {
        groupService.deleteGroup(group);
      }
      List<Role> roles = roleService.readRoles();
      for (Role role : roles) {
        roleService.deleteRole(role);
      }
      List<Action> actions = actionService.readActions();
      for (Action action : actions) {
        actionService.deleteAction(action);
      }
      
      List<Workflow> workflows = workflowService.readWorkflows();
      for (Workflow workflow : workflows) {
    	  workflowService.deleteWorkflow(workflow);
      }

    }
  }
