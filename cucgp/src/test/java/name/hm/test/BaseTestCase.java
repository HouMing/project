package name.hm.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import name.hm.jpa.ActionMapper;
import name.hm.jpa.GroupMapper;
import name.hm.jpa.RoleMapper;
import name.hm.jpa.UserMapper;
import name.hm.jpa.WorkflowMapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;

public class BaseTestCase implements ITestCase
{
	static SqlSessionFactory sqlSessionFactory = null;
	MapperScannerConfigurer
	protected static SqlSession se = null;

	protected RoleMapper roleMapper = null;
	protected GroupMapper groupMapper = null;
	protected UserMapper userMapper = null;
	protected WorkflowMapper workflowMapper = null;
	protected ActionMapper actionMapper = null;

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

	public void openTestSession()
	{
		se = sqlSessionFactory.openSession();
		roleMapper = se.getMapper(RoleMapper.class);
		groupMapper = se.getMapper(GroupMapper.class);
		userMapper = se.getMapper(UserMapper.class);
		workflowMapper = se.getMapper(WorkflowMapper.class);
		actionMapper = se.getMapper(ActionMapper.class);
	}

	public void closeTestSession()
	{
		if (roleMapper != null) {
			roleMapper = null;
		}
		if (groupMapper != null) {
			groupMapper = null;
		}
		if (userMapper != null) {
			userMapper = null;
		}
		if (workflowMapper != null) {
			workflowMapper = null;
		}
		if (actionMapper != null) {
			actionMapper = null;
		}
		if (se != null) {
			se.close();
			se = null;
		}
	}

}