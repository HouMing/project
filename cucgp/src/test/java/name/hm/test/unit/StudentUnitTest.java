package name.hm.test.unit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import name.hm.m.Student;
import name.hm.test.BaseTestCase;

//TODO 11 IMPL, Upgrade - task : #0310
public class StudentUnitTest extends BaseTestCase implements POJOTest
{
	public static Student STUDENT0 = null;
	public static String STUDENT0_NAME = "张三三";

	public static Student STUDENT1 = null;
	public static String STUDENT1_NAME = "李思思";

	public static String TELEPHONE = "1110723";
	public static String EMAIL = "test@test.com";
	public static String WEIBO = "@涨三三";
	public static String INTRODUCTION = "这是张三三的个人介绍";

	@Override
	@Before
	public void beforeTest()
	{
		logger.info("start StudentUnitTest");
		UserUnitTest unitUser = new UserUnitTest();
		unitUser.create();
		ClassroomUnitTest unitClassroom = new ClassroomUnitTest();
		unitClassroom.create();
		DepartmentUnitTest unitDepart = new DepartmentUnitTest();
		unitDepart.create();
	}

	@Override
	@After
	public void afterTest()
	{
		UserUnitTest unitUser = new UserUnitTest();
		unitUser.clean();
		ClassroomUnitTest unitClassroom = new ClassroomUnitTest();
		unitClassroom.clean();
		DepartmentUnitTest unitDepart = new DepartmentUnitTest();
		unitDepart.clean();
		logger.info("finish StudentUnitTest");
	}

	@Override
	@Test
	public void test()
	{
		create();
		read();
		update();
		delete();
	}

	@Override
	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			STUDENT0 = new Student(UserUnitTest.U_Student.getUserName(),
					STUDENT0_NAME, TELEPHONE, EMAIL, WEIBO, INTRODUCTION,
					ClassroomUnitTest.CLASSROOM0.getClassroomName(),
					DepartmentUnitTest.DEPARTMENT0.getDepartmentName());
			error &= studentMapper.insert(STUDENT0);
			se.commit();
			if (error == 1) {
				logger.info("create OK!\n" + STUDENT0);
			} else {
				logger.error("create failed");
				Assert.fail("create failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	@Override
	public void read()
	{}

	@Override
	public void update()
	{}

	@Override
	public void delete()
	{}

	@Override
	public void clean()
	{}

}
