package name.hm.c.login;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import name.hm.jpa.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController
{
	protected SqlSessionFactory sqlSessionFactory;

	protected ActionMapper actionMapper;
	protected ClassroomMapper classrommMapper;
	protected DepartmentMapper departmentMapper;
	protected GroupMapper groupMapper;
	protected RoleMapper roleMapper;
	protected StuaMapper stuaMapper;
	protected StudentMapper studentMapper;
	protected TaskMapper taskMapper;
	protected TcaMapper tcaMapper;
	protected TeacherMapper teacherMapper;
	protected TitleMapper titleMapper;
	protected UserMapper userMapper;
	protected WorkflowMapper workflowMapper;

	protected SqlSession se;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
	{
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void openSession()
	{
		try {
			se = sqlSessionFactory.openSession();
			Method[] ms = BaseController.class.getMethods();
			for (Method m : ms) {
				if (m.getName().matches("^set.*")) {
					Class<?>[] types = m.getParameterTypes();
					if (sqlSessionFactory.getConfiguration().hasMapper(types[0])) {
						Object obj = se.getMapper(types[0]);
						m.invoke(this, obj);
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void closeSession()
	{
		se.close();
		try {
			Method[] ms = BaseController.class.getMethods();
			for (Method m : ms) {
				if (m.getName().matches("^set.*")) {
					Class<?>[] types = m.getParameterTypes();
					if (sqlSessionFactory.getConfiguration().hasMapper(types[0])) {
						m.invoke(this, (Object) null);
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public ActionMapper getActionMapper()
	{
		return actionMapper;
	}

	public void setActionMapper(ActionMapper actionMapper)
	{
		this.actionMapper = actionMapper;
	}

	public ClassroomMapper getClassrommMapper()
	{
		return classrommMapper;
	}

	public void setClassrommMapper(ClassroomMapper classrommMapper)
	{
		this.classrommMapper = classrommMapper;
	}

	public DepartmentMapper getDepartmentMapper()
	{
		return departmentMapper;
	}

	public void setDepartmentMapper(DepartmentMapper departmentMapper)
	{
		this.departmentMapper = departmentMapper;
	}

	public GroupMapper getGroupMapper()
	{
		return groupMapper;
	}

	public void setGroupMapper(GroupMapper groupMapper)
	{
		this.groupMapper = groupMapper;
	}

	public RoleMapper getRoleMapper()
	{
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper)
	{
		this.roleMapper = roleMapper;
	}

	public StuaMapper getStuaMapper()
	{
		return stuaMapper;
	}

	public void setStuaMapper(StuaMapper stuaMapper)
	{
		this.stuaMapper = stuaMapper;
	}

	public StudentMapper getStudentMapper()
	{
		return studentMapper;
	}

	public void setStudentMapper(StudentMapper studentMapper)
	{
		this.studentMapper = studentMapper;
	}

	public TaskMapper getTaskMapper()
	{
		return taskMapper;
	}

	public void setTaskMapper(TaskMapper taskMapper)
	{
		this.taskMapper = taskMapper;
	}

	public TcaMapper getTcaMapper()
	{
		return tcaMapper;
	}

	public void setTcaMapper(TcaMapper tcaMapper)
	{
		this.tcaMapper = tcaMapper;
	}

	public TeacherMapper getTeacherMapper()
	{
		return teacherMapper;
	}

	public void setTeacherMapper(TeacherMapper teacherMapper)
	{
		this.teacherMapper = teacherMapper;
	}

	public TitleMapper getTitleMapper()
	{
		return titleMapper;
	}

	public void setTitleMapper(TitleMapper titleMapper)
	{
		this.titleMapper = titleMapper;
	}

	public UserMapper getUserMapper()
	{
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper)
	{
		this.userMapper = userMapper;
	}

	public WorkflowMapper getWorkflowMapper()
	{
		return workflowMapper;
	}

	public void setWorkflowMapper(WorkflowMapper workflowMapper)
	{
		this.workflowMapper = workflowMapper;
	}

}
