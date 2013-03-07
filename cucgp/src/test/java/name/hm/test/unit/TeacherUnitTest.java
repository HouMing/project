package name.hm.test.unit;

import java.util.LinkedList;

import name.hm.pojo.Department;
import name.hm.pojo.Teacher;
import name.hm.pojo.Title;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.Test;

// PASS UNIT #0306
public class TeacherUnitTest extends BaseTestCase
{
	static public String TEACHER_NAME = "测试老师";
	static public String TEACHER_NAMEC = "测试老师改";
	
	static public String TEACHER_NAME1 = "测试老师1";
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
		beforeTest();
		create();
		read();
		update();
		delete();
		afterTest();
	}

	// PASS CELL #0306
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

	// PASS CELL #0306
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

	// PASS CELL #0306
	public void create()
	{
		try {
			openTestSession();
			User user = userMapper.selectByUserId(UserUnitTest.USER_ID);
			User user1 = userMapper.selectByUserId(UserUnitTest.USER_ID1);
			User user2 = userMapper.selectByUserId(UserUnitTest.USER_ID2);
			se.commit();
			Teacher teacher = new Teacher(user.getUserName(), TEACHER_NAME,
					TELEPHONE, EMAIL, WEIBO, INTRODUCTION,
					DepartmentUnitTest.DEPARTMENT_NAME, TitleUnitTest.TITLE_NAME);
			Teacher teacher1 = new Teacher(user1.getUserName(), TEACHER_NAME,
					TELEPHONE, EMAIL, WEIBO, INTRODUCTION,
					DepartmentUnitTest.DEPARTMENT_NAME, TitleUnitTest.TITLE_NAME);
			Teacher teacher2 = new Teacher(user2.getUserName(), TEACHER_NAME,
					TELEPHONE, EMAIL, WEIBO, INTRODUCTION,
					DepartmentUnitTest.DEPARTMENT_NAME, TitleUnitTest.TITLE_NAME);
			teacherMapper.insert(teacher);
			teacherMapper.insert(teacher1);
			teacherMapper.insert(teacher2);
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0306
	public void read()
	{
		try {
			openTestSession();
			Teacher teacher = teacherMapper.selectByUserName(UserUnitTest.USER_NAME);
			LinkedList<Teacher> l = teacherMapper
					.selectByDepartmentName(DepartmentUnitTest.DEPARTMENT_NAME);
			LinkedList<Teacher> l2 = teacherMapper
					.selectByTitleName(TitleUnitTest.TITLE_NAME);
			se.commit();
			if (teacher != null) {
				logger.info("selectByUserName OK!\n" + teacher);
			} else {
				logger.error("selectByUserName failed\n" + teacher);
			}
			if (l.size() > 0) {
				logger.info("selectByDepartmentName OK!\n" + l);
			} else {
				logger.error("selectByDepartmentName failed\n" + l);
			}
			if (l2.size() > 0) {
				logger.info("selectByTitleName OK!\n" + l2);
			} else {
				logger.error("selectByTitleName failed\n" + l2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0306
	public void update()
	{
		try {
			Integer error;
			openTestSession();
			Teacher teacher = teacherMapper.selectByUserName(UserUnitTest.USER_NAME);
			se.commit();
			teacher.setTeacherName(TEACHER_NAMEC);
			teacherMapper.update(teacher);
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0306
	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
			Teacher teacher = teacherMapper.selectByUserName(UserUnitTest.USER_NAME);
			Teacher teacher1 = teacherMapper.selectByUserName(UserUnitTest.USER_NAME1);
			Teacher teacher2 = teacherMapper.selectByUserName(UserUnitTest.USER_NAME2);
			se.commit();
			error = teacherMapper.delete(teacher1) & error;
			error = teacherMapper.delete(teacher) & error;
      error = teacherMapper.delete(teacher2) & error;			
      se.commit();
      teacher = teacherMapper.selectByUserName(UserUnitTest.USER_NAME);
			teacher1 = teacherMapper.selectByUserName(UserUnitTest.USER_NAME1);
			teacher2 = teacherMapper.selectByUserName(UserUnitTest.USER_NAME2);
			se.commit();
      if (error == 1) {
      	logger.info("delete OK!\n" + teacher + teacher1 + teacher2);
      } else {
       	logger.info("delete failed\n" + teacher + teacher1 + teacher2);
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
