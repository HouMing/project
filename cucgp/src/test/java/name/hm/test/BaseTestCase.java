package name.hm.test;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import name.hm.jpa.*;

public class BaseTestCase implements ITestCase, ILogger
{
	static public String CONF_FILE_NAME = "appContext-MyBATIS.xml";
	static public String LOGGER_APPENDER = "celltest";
	
	static protected Logger logger = Logger.getLogger(LOGGER_APPENDER); 
	static private SqlSessionFactory sqlSessionFactory = null;
	static protected SqlSession se = null;

	protected RoleMapper roleMapper = null;
	protected GroupMapper groupMapper = null;
	protected UserMapper userMapper = null;
	protected WorkflowMapper workflowMapper = null;
	protected ActionMapper actionMapper = null;
	protected TeacherMapper teacherMapper = null;
	protected DepartmentMapper departmentMapper = null;
	protected TitleMapper titleMapper = null;

	static {
		String resource = CONF_FILE_NAME;
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				Method[] ms = BaseTestCase.class.getMethods();
				for (Method m: ms) {
					if (m.getName().matches("^set.*")) {
						Class<?>[] types = m.getParameterTypes();
						sqlSessionFactory.getConfiguration().addMapper(types[0]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openTestSession() throws Exception
	{
		if ( se == null) {
		  se = sqlSessionFactory.openSession();
		} else {
			throw new Exception("openTestSession failed!");
		}
		Class c = this.getClass();
		Method[] ms = c.getMethods();
		for (Method m: ms) {
			if (m.getName().matches("^set.*")) {
				Class<?>[] types = m.getParameterTypes();
				Object obj = se.getMapper(types[0]);
				m.invoke(this, obj);
			}
		}
	}

	public void closeTestSession()
	{
		Class c = this.getClass();
		Method[] ms = c.getMethods();
		for (Method m : ms) {
			if(m.getName().matches("^set.*")) {
				try {
					m.invoke(this,(Object)null);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		if (se != null) {
			se.close();
			se = null;
		}
	}

	public RoleMapper getRoleMapper()
	{
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper)
	{
		this.roleMapper = roleMapper;
	}

	public GroupMapper getGroupMapper()
	{
		return groupMapper;
	}

	public void setGroupMapper(GroupMapper groupMapper)
	{
		this.groupMapper = groupMapper;
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

	public ActionMapper getActionMapper()
	{
		return actionMapper;
	}

	public void setActionMapper(ActionMapper actionMapper)
	{
		this.actionMapper = actionMapper;
	}

	public TeacherMapper getTeacherMapper()
	{
		return teacherMapper;
	}

	public void setTeacherMapper(TeacherMapper teacherMapper)
	{
		this.teacherMapper = teacherMapper;
	}

	public DepartmentMapper getDepartmentMapper()
	{
		return departmentMapper;
	}

	public void setDepartmentMapper(DepartmentMapper departmentMapper)
	{
		this.departmentMapper = departmentMapper;
	}

	public TitleMapper getTitleMapper()
	{
		return titleMapper;
	}

	public void setTitleMapper(TitleMapper titleMapper)
	{
		this.titleMapper = titleMapper;
	}
}