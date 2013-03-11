package name.hm.test.unit;

import java.util.LinkedList;

import junit.framework.Assert;

import name.hm.pojo.Department;
import name.hm.pojo.Teacher;
import name.hm.pojo.Title;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//TODO 10 PASS, Upgrade - task : #0310
public class TeacherUnitTest extends BaseTestCase
{
	public static Teacher TEACHER0 = null;
	protected static String TEACHER0_NAME = "测试老师";
	protected static String TEACHER0_NAMEC = "测试老师改";

	public static Teacher TEACHER1 = null;
	protected static String TEACHER1_NAME = "测试老师1";

	protected static String TELEPHONE = "13400010001";
	protected static String EMAIL = "10197786@qq.com";
	protected static String WEIBO = "@testTeacher";
	protected static String INTRODUCTION = "测试介绍内容如下：。。。。";

	@Test
	public void test()
	{
		create();
		read();
		update();
		delete();
	}

	@Before
	public void beforeTest()
	{
		logger.info("start TeacherUnitTest");
		DepartmentUnitTest unitDepartment = new DepartmentUnitTest();
		unitDepartment.create();
		TitleUnitTest unitTitle = new TitleUnitTest();
		unitTitle.create();
		GroupUnitTest unitGroup = new GroupUnitTest();
		unitGroup.create();
		UserUnitTest unitUser = new UserUnitTest();
		unitUser.create();
	}

	@After
	public void afterTest()
	{
		DepartmentUnitTest unitDepartment = new DepartmentUnitTest();
		unitDepartment.delete();
		TitleUnitTest unitTitle = new TitleUnitTest();
		unitTitle.delete();
		UserUnitTest unitUser = new UserUnitTest();
		unitUser.delete();
		GroupUnitTest unitGroup = new GroupUnitTest();
		unitGroup.delete();
		clean();
		logger.info("end TeacherUnitTest");
	}

	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			TEACHER0 = new Teacher(UserUnitTest.USER_TEACHER.getUserName(), TEACHER0_NAME,
					TELEPHONE, EMAIL, WEIBO, INTRODUCTION,
					DepartmentUnitTest.DEPARTMENT0.getDepartmentName(), TitleUnitTest.TITLE0.getTitleName());
			TEACHER1 = new Teacher(UserUnitTest.USER_ADMIN.getUserName(), TEACHER1_NAME,
					TELEPHONE, EMAIL, WEIBO, INTRODUCTION,
					DepartmentUnitTest.DEPARTMENT0.getDepartmentName(), TitleUnitTest.TITLE0.getTitleName());
			error &= teacherMapper.insert(TEACHER0);
			error &= teacherMapper.insert(TEACHER1);
			se.commit();
			if (error == 1) {
				logger.info("create OK!\n" + "\n" + TEACHER0 + "\n" + TEACHER1);
			} else {
				logger.error("create failed\n" + "\n" + TEACHER0 + "\n" + TEACHER1);
				Assert.fail("create failed");
			}
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
			Teacher teacher = teacherMapper.selectByUserName(UserUnitTest.ADMIN_NAME);
			se.commit();
			if (teacher != null) {
				logger.info("selectByUserName OK!\n" + teacher);
			} else {
				logger.error("selectByUserName failed\n" + teacher);
				Assert.fail("selectByUserName failed\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void update()
	{
		try {
			Integer error = 1;
			openTestSession();
			TEACHER0.setTeacherName(TEACHER0_NAMEC);
			error &= teacherMapper.update(TEACHER0);
			se.commit();
			if (error == 1) {
				logger.info("update OK!\n" + TEACHER0);
			} else {
				logger.error("update failed");
				Assert.fail("update failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
			error &= teacherMapper.delete(TEACHER0);
			error &= teacherMapper.delete(TEACHER1);
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + TEACHER0 + TEACHER1);
			} else {
				logger.error("delete failed\n");
				Assert.fail("delete failed");
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
			LinkedList<Teacher> l = teacherMapper.selectAll();
			se.commit();
			for (Teacher t : l) {
				teacherMapper.delete(t);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

}
