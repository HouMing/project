package name.hm.test.unit;

import java.util.LinkedList;

import name.hm.pojo.Department;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.Test;

public class DepartmentUnitTest extends BaseTestCase
{
	static public String DEPARTMENT_NAME = "测试系";
	static public String DEPARTMENT_NAME2 = "测试系2";

	@Test
	public void test()
	{
		beforeTest();
		insertDepartment();
		selectDepartment();
		updateDepartment();
		deleteDepartment();
		afterTest();
	}

	public void deleteDepartment()
	{
		try {
			Integer ret;
			openTestSession();
			Department department = departmentMapper.selectAll().getFirst();
	    ret = departmentMapper.delete(department);
	    if (ret == 1) {
	    	logger.info("delete Department OK!");
	    } else {
	    	logger.error("delete Department failed");
	    }
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void updateDepartment()
	{
		try {
			Integer ret;
			openTestSession();
			Department department = departmentMapper.selectAll().getFirst();
			department.setDepartmentName(DEPARTMENT_NAME2);
			ret = departmentMapper.update(department);
			if(ret == 1) {
				logger.info("update Department OK!");
			} else {
				logger.error("update Department failed!");
			}
			se.commit();
			department.setDepartmentName(DEPARTMENT_NAME);
			ret = departmentMapper.update(department);
			if(ret == 1) {
				logger.info("update Department OK!");
			} else {
				logger.error("update Department failed!");
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void selectDepartment()
	{
		try {
			openTestSession();
			LinkedList<Department> list = departmentMapper.selectAll();
			if (list.size() == 1) {
				logger.info("selectAll : " + list);
			} else {
				logger.error("selectAll : " + list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void insertDepartment()
	{
		try {
			Integer ret;
			openTestSession();
			Department department = new Department();
			department.setDepartmentName(DEPARTMENT_NAME);
			ret = departmentMapper.insert(department);
			if (ret == 1) {
				logger.info("insert Department OK!");
			} else {
				logger.error("insert Department failed!");
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void beforeTest()
	{
		logger.info("start");
	}

	public void afterTest()
	{
		logger.info("end");
	}

}
