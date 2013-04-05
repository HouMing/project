package name.hm.s;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import name.hm.c.BaseController;
import name.hm.jpa.ActionMapper;
import name.hm.jpa.ClassroomMapper;
import name.hm.jpa.DepartmentMapper;
import name.hm.jpa.GroupMapper;
import name.hm.jpa.RoleMapper;
import name.hm.jpa.StuaMapper;
import name.hm.jpa.StudentMapper;
import name.hm.jpa.TaskMapper;
import name.hm.jpa.TcaMapper;
import name.hm.jpa.TeacherMapper;
import name.hm.jpa.TitleMapper;
import name.hm.jpa.UserMapper;
import name.hm.jpa.WorkflowMapper;
import name.hm.s.e.ServiceException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService
{
	protected static Logger logger = Logger.getLogger("cucgp");
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
  protected boolean started = false;
	
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
  {
  	this.sqlSessionFactory = sqlSessionFactory;
  }
  
	protected SqlSessionFactory getSqlSessionFactory()
	{
		return sqlSessionFactory;
	}

	public void startService() throws ServiceException
	{
		try {
			if (!started) {
			se = sqlSessionFactory.openSession();
			Method[] ms = this.getClass().getMethods();
			for (Method m : ms) {
				if (m.getName().matches("^set.*")) {
					Class<?>[] types = m.getParameterTypes();
					if (sqlSessionFactory.getConfiguration().hasMapper(types[0])) {
						Object obj = se.getMapper(types[0]);
						m.invoke(this, obj);
					}
				}
			}
			started = true;
			} else {
			}
		} catch (IllegalAccessException e) {
			logger.error(e.toString());
			throw new ServiceException(this.getClass() + " 服务错误");
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(this.getClass() + " 服务错误");
		} catch (InvocationTargetException e) {
			logger.error(e.toString());
			throw new ServiceException(this.getClass() + " 服务错误");
		}
	}

	public void endService()
	{
		se.close();
		try {
			Method[] ms = this.getClass().getMethods();
			for (Method m : ms) {
				if (m.getName().matches("^set.*")) {
					Class<?>[] types = m.getParameterTypes();
					if (sqlSessionFactory.getConfiguration().hasMapper(types[0])) {
						m.invoke(this, (Object) null);
					}
				}
			}
		} catch (IllegalAccessException e) {
			logger.error(e.toString());
		} catch (InvocationTargetException e) {
			logger.error(e.toString());
		} finally {
			started = false;
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