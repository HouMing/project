package name.hm.test;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import name.hm.jpa.ActionMapper;
import name.hm.jpa.ClassroomMapper;
import name.hm.jpa.DepartmentMapper;
import name.hm.jpa.GroupMapper;
import name.hm.jpa.RoleMapper;
import name.hm.jpa.StuaMapper;
import name.hm.jpa.StudentMapper;
import name.hm.jpa.TcaMapper;
import name.hm.jpa.TeacherMapper;
import name.hm.jpa.TitleMapper;
import name.hm.jpa.UserMapper;
import name.hm.jpa.WorkflowMapper;
import name.hm.m.Action;
import name.hm.m.Group;
import name.hm.m.Role;
import name.hm.m.User;
import name.hm.m.Workflow;
import name.hm.s.UserRightService;
import name.hm.test.unit.ActionUnitTest;
import name.hm.test.unit.DepartmentUnitTest;
import name.hm.test.unit.GroupUnitTest;
import name.hm.test.unit.RoleUnitTest;
import name.hm.test.unit.TeacherUnitTest;
import name.hm.test.unit.TitleUnitTest;
import name.hm.test.unit.UserUnitTest;
import name.hm.test.unit.WorkflowUnitTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;


//@ContextConfiguration(locations = {  })
public class BaseServiceTest // extends AbstractJUnit4SpringContextTests
{
	static public String CONF_FILE_NAME = "appContext-MyBATIS.xml";
	static public String LOGGER_APPENDER = "dev";
	
	static private SqlSessionFactory sqlSessionFactory = null;
	static protected Logger logger = Logger.getLogger(LOGGER_APPENDER);
	
	static {
		String resource = CONF_FILE_NAME;
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}