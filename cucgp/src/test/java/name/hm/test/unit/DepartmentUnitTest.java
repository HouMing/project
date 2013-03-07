package name.hm.test.unit;

import java.util.LinkedList;
import java.util.List;

import name.hm.jpa.DepartmentMapper;
import name.hm.pojo.Action;
import name.hm.pojo.Department;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.Test;

// PASS UNIT #0307
public class DepartmentUnitTest extends BaseTestCase
{
	static public Integer DEPARTMENT_ID = null;
	static public String DEPARTMENT_NAME = "测试系";
	static public String DEPARTMENT_NAMEC = "测试系改";

	static public Integer DEPARTMENT_ID1 = null;
	static public String DEPARTMENT_NAME1 = "测试系1";

	static public Integer DEPARTMENT_ID2 = null;
	static public String DEPARTMENT_NAME2 = "测试系2";

	@Test
	public void test()
	{
		beforeTest();
		create();
		read();
		update();
		delete();
		afterTest();
	}

	// PASS CELL #0307
	public void beforeTest()
	{
		logger.info("start DepartmentUnitTest");
	}

	// PASS CELL #0307
	public void afterTest()
	{
		clean();
		logger.info("finish DepartmentUnitTest");
	}

	// PASS CELL #0307
	public void create()
	{
		try {
			Integer error;
			openTestSession();
			Department department = new Department(DEPARTMENT_NAME);
			Department department1 = new Department(DEPARTMENT_NAME1);
			Department department2 = new Department(DEPARTMENT_NAME2);
			error = departmentMapper.insert(department);
			error = departmentMapper.insert(department1) & error;
			error = departmentMapper.insert(department2) & error;
			se.commit();
			if (error == 1) {
				logger.info("insert OK!\n" + department + department1 + department2);
			} else {
				logger
						.error("insert failed\n" + department + department1 + department2);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0307
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0307
	public void update()
	{
		try {
			Integer error = 1;
			openTestSession();
			LinkedList<Department> l = departmentMapper.selectAll();
			se.commit();
			logger.info("before update\n:" + l);
			String pre;
			String cur;
			pre = l.get(0).getDepartmentName();
			cur = pre;
			l.get(0).setDepartmentName(DEPARTMENT_NAMEC);
			for (int i = 1; i < l.size(); ++i) {
				cur = l.get(i).getDepartmentName();
				l.get(i).setDepartmentName(pre);
				pre = cur;
			}
			for (int i = 0; i < l.size(); ++i) {
				error = departmentMapper.update(l.get(i)) & error;
			}
			se.commit();
			l = departmentMapper.selectAll();
			se.commit();
			if (error == 1) {
				logger.info("update OK!\n" + l);
			} else {
				logger.error("update failed\n" + l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0307
	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
			LinkedList<Department> l = departmentMapper.selectAll();
			se.commit();
			for (; l.size() > 0;) {
				Department tmp = l.pop();
				error = departmentMapper.delete(tmp) & error;
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
