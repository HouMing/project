package name.hm.test.cell;

import java.io.IOException;
import java.io.Reader;

import name.hm.jpa.GroupMapper;
import name.hm.jpa.RoleMapper;
import name.hm.test.CellTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(CellTest.class)
public class EnvTest {

	private static SqlSessionFactory sqlSessionFactory = null;

	static {
		String resource = "appContext-MyBATIS.xml";
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				sqlSessionFactory.getConfiguration().addMapper(GroupMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(RoleMapper.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	@Test
	public void sqlSessionTest(){
		SqlSession se = getSqlSessionFactory().openSession();
		System.out.println(se);
		se.commit();
		se.close();
	}
}
