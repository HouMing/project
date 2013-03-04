package name.hm.test.unit;

import name.hm.pojo.Department;
import name.hm.pojo.Teacher;
import name.hm.pojo.Title;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.Test;

public class TeacherUnitTest extends BaseTestCase
{
	static public String USER_NAME = UserUnitTest.USER_NAME;
	static public String TEACHER_NAME = "测试老师";
	static public String TEACHER_NAME2 = "测试老师2";
	static public String TELEPHONE = "13461370223";
	static public String EMAIL = "10197786@qq.com";
	static public String WEIBO = "@testTeacher";
	static public String INTRODUCTION = "测试介绍内容如下：。。。。";
	static public String DEPARTMENT_NAME = DepartmentUnitTest.DEPARTMENT_NAME;
	static public String TITLE_NAME = TitleUnitTest.TITLE_NAME;

	@Test
	public void test()
	{
		try {
			beforeTest();
			insertTeacher();
			selectTeacher();
			updateTeacher();
			updateTeacher();
		} finally {
			afterTest();
		}
	}

	public void updateTeacher()
	{
		try {
			openTestSession();
			Teacher teacher = teacherMapper.selectByUserName(UserUnitTest.USER_NAME);
			if (teacher != null) {
				teacher.setTeacherName(TEACHER_NAME2);
			} else {
				logger.error("updateTeacher failed!");
			}
			teacherMapper.update(teacher);
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void selectTeacher()
	{
		try {
			openTestSession();
			Teacher teacher = teacherMapper.selectByUserName(UserUnitTest.USER_NAME);
			if (teacher != null) {
				logger.info(teacher);
			} else {
				logger.error(teacher);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void insertTeacher()
	{
		try {
			openTestSession();
			User user = userMapper.selectByUserId(UserUnitTest.USER_ID);
			Teacher teacher = new Teacher();
			teacher.setUserName(user.getUserName());
			teacher.setTeacherName(TEACHER_NAME);
			teacher.setTelephone(TELEPHONE);
			teacher.setEmail(EMAIL);
			teacher.setWeibo(WEIBO);
			teacher.setIntroduction(INTRODUCTION);
			teacher.setDepartmentName(DepartmentUnitTest.DEPARTMENT_NAME);
			teacher.setTitleName(TitleUnitTest.TITLE_NAME);
			teacherMapper.insert(teacher);
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void beforeTest()
	{
		logger.info("start TeacherUnitTest");
		DepartmentUnitTest unitDepartment = new DepartmentUnitTest();
		unitDepartment.insertDepartment();
		TitleUnitTest unitTitle = new TitleUnitTest();
		unitTitle.insertTitle();
		GroupUnitTest unitGroup = new GroupUnitTest();
		unitGroup.insertGroup();
		UserUnitTest unitUser = new UserUnitTest();
		unitUser.insertUser();
	}

	public void afterTest()
	{
		DepartmentUnitTest unitOne = new DepartmentUnitTest();
		unitOne.deleteDepartment();
		TitleUnitTest unitTitle = new TitleUnitTest();
		unitTitle.deleteTitle();
		UserUnitTest unitUser = new UserUnitTest();
		unitUser.deleteUser();
		GroupUnitTest unitGroup = new GroupUnitTest();
		unitGroup.deleteGroup();
		logger.info("end TeacherUnitTest");
	}
}
