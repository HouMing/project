package name.hm.test.unit;

import name.hm.pojo.Teacher;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.Test;

public class TeacherUnitTest extends BaseTestCase
{
	static public String USER_NAME = UserUnitTest.USER_NAME;
	static public String TEACHER_NAME = "测试老师";
	static public String TELEPHONE = "13461370223";
	static public String EMAIL = "10197786@qq.com";
	static public String WEIBO = "@testTeacher";
	static public String INTRODUCTION = "测试介绍内容如下：。。。。";
	static public String DEPARTMENT_NAME = "待替换";
	static public String TITLE_NAME = "待替换";

	@Test
	public void test()
	{
		beforeTest();
		insertTeacher();
		selectTeacher();
		updateTeacher();
		updateTeacher();
		afterTest();
	}

	public void updateTeacher()
	{
		// TODO Auto-generated method stub

	}

	public void selectTeacher()
	{
		// TODO Auto-generated method stub

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
			teacher.setDepartmentName(DEPARTMENT_NAME);
			teacher.setTitleName(TITLE_NAME);
			teacherMapper.insert(teacher);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void beforeTest()
	{
		UserUnitTest unitOne = new UserUnitTest();
		unitOne.insertUser();
	}

	public void afterTest()
	{
		UserUnitTest unitOne = new UserUnitTest();
		unitOne.deleteUser();
	}

}
