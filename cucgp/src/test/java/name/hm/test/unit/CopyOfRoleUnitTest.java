package name.hm.test.unit;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import name.hm.jpa.RoleMapper;
import name.hm.m.Role;
import name.hm.test.BaseTestCase;
import name.hm.test.integration.RoleIntegrationTest;

// PASS UNIT #0306
//TODO 3 PASS, Upgrade - task : #0310
public class CopyOfRoleUnitTest extends BaseTestCase
{

	@Test
	public void test()
	{
		read();
	}

	// PASS CELL #0306
	public void read()
	{
			try {
				openTestSession();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Role admin = roleMapper.selectByRoleId(1);
			System.out.println(admin);
			se.commit();

	}

}