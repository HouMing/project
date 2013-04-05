package name.hm.test.unit;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import name.hm.jpa.DepartmentMapper;
import name.hm.m.Action;
import name.hm.m.Department;
import name.hm.m.User;
import name.hm.test.BaseTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//TODO 7 PASS, Upgrade - task : #0310
public class DepartmentUnitTest extends BaseTestCase
{
public static Department DEPARTMENT0 = null;
protected static String DEPARTMENT0_NAME = "广播电视工程系";
protected static String DEPARTMENT0_NAMEC = "广电工系";

public static Department DEPARTMENT1 = null;
protected static String DEPARTMENT1_NAME = "数字媒体技术";

public static Department DEPARTMENT2 = null;
protected static String DEPARTMENT2_NAME = "自动化";

	@Test
	public void test()
	{
		beforeTest();
		create();
		read();
//		update();
		delete();
		afterTest();
	}

	@Before
	public void beforeTest()
	{
		logger.info("start DepartmentUnitTest");
	}

	@After
	public void afterTest()
	{
		clean();
		logger.info("finish DepartmentUnitTest");
	}

	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			DEPARTMENT0 = new Department(DEPARTMENT0_NAME);
			DEPARTMENT1 = new Department(DEPARTMENT1_NAME);
			DEPARTMENT2 = new Department(DEPARTMENT2_NAME);
			error &= departmentMapper.insert(DEPARTMENT0);
			error &= departmentMapper.insert(DEPARTMENT1);
			error &= departmentMapper.insert(DEPARTMENT2);
			se.commit();
			if (error == 1) {
				logger.info("insert OK!\n" + DEPARTMENT0 + DEPARTMENT1 + DEPARTMENT2);
			} else {
				logger.error("insert failed\n" + DEPARTMENT0 + DEPARTMENT1 + DEPARTMENT2);
				Assert.fail("insert failed");
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void read()
	{
		try {
			openTestSession();
			LinkedList<Department> l = departmentMapper.selectAll();
			se.commit();
			if (l.size() > 0) {
				logger.info("selectAll OK!\n" + l);
			} else {
				logger.error("selectAll failed\n" + l);
				Assert.fail("selectAll failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
/*
	public void update()
	{
		try {
			Integer error = 1;
			openTestSession();
			logger.info("before update\n:" + DEPARTMENT0);
			DEPARTMENT0.setDepartmentName(DEPARTMENT0_NAMEC);
			error &= departmentMapper.update(DEPARTMENT0);
			se.commit();
			if (error == 1) {
				DEPARTMENT0 = departmentMapper.selectByDepartmentId(DEPARTMENT0.getDepartmentId());
				logger.info("update OK!\n");
			} else {
				logger.error("update failed\n");
				Assert.fail("update failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
*/
	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
			LinkedList<Department> l = departmentMapper.selectAll();
			se.commit();
			logger.info("before delete:\n" + l);
			for (; l.size() > 0;) {
				Department tmp = l.pop();
				error &= departmentMapper.delete(tmp);
			}
			l = departmentMapper.selectAll();
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + l);
			} else {
				logger.error("delete failed\n" + l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void clean()
	{
		try {
			openTestSession();
			LinkedList<Department> l = departmentMapper.selectAll();
			se.commit();
			for (Department tmp : l) {
				departmentMapper.delete(tmp);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
