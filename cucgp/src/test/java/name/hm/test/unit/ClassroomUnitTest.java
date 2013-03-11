package name.hm.test.unit;

import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import name.hm.pojo.Classroom;
import name.hm.test.BaseTestCase;

//TODO IMPL UNIT
//TODO 6 PASS, Upgrade - task : #0310
public class ClassroomUnitTest extends BaseTestCase
{
	public static Classroom CLASSROOM0 = null;
	public static String CLASSROOM0_NAME = "广播电视工程0班";
	public static String CLASSROOM0_NAMEC = "广播电视工程0班改";

	public static Classroom CLASSROOM1 = null;
	public static String CLASSROOM1_NAME = "广播电视工程1班";

	public static Classroom CLASSROOM2 = null;
	public static String CLASSROOM2_NAME = "广播电视工程2班";

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
		logger.info("start ClassroomUnitTest");
	}

	@After
	public void afterTest()
	{
		clean();
		logger.info("finish ClassroomUnitTest");
	}

	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			CLASSROOM0 = new Classroom(CLASSROOM0_NAME);
			CLASSROOM1 = new Classroom(CLASSROOM1_NAME);
			CLASSROOM2 = new Classroom(CLASSROOM2_NAME);
			error &= classroomMapper.insert(CLASSROOM0);
			error &= classroomMapper.insert(CLASSROOM1);
			error &= classroomMapper.insert(CLASSROOM2);
			se.commit();
			if (error == 1) {
				logger.info("create OK!\n" + CLASSROOM0 + "\n" + CLASSROOM1 + "\n"
						+ CLASSROOM2);
			} else {
				logger.error("create failed\n" + CLASSROOM0 + "\n" + CLASSROOM1 + "\n"
						+ CLASSROOM2);
				Assert.fail("create failed\n");
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
			Classroom classroom0 = classroomMapper.selectByClassroomId(CLASSROOM0
					.getClassroomId());
			Classroom classroom1 = classroomMapper.selectByClassroomName(CLASSROOM1
					.getClassroomName());
			LinkedList<Classroom> l = classroomMapper.selectAll();
			se.commit();

			if (classroom0 != null) {
				logger.info("selectByClassroomId OK!\n" + classroom0);
			} else {
				logger.error("selectByClassroomId failed\n" + CLASSROOM0);
				Assert.fail("selectByClassroomId failed\n");
			}

			if (classroom1 != null) {
				logger.info("selectByClassroomName OK!\n" + classroom1);
			} else {
				logger.error("selectByClassroomName failed\n" + CLASSROOM1);
				Assert.fail("selectByClassroomName failed\n");
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
			Classroom classroom = classroomMapper.selectByClassroomId(CLASSROOM0.getClassroomId());
			se.commit();
			classroom.setClassroomName(CLASSROOM0_NAMEC);
			error &= classroomMapper.update(classroom);
			se.commit();
			if (error == 1) {
				Assert.assertTrue(!classroom.equals(CLASSROOM0));
				CLASSROOM0 = classroomMapper.selectByClassroomId(CLASSROOM0.getClassroomId());
				se.commit();
				logger.info("update OK!\n" + classroom);
			} else {
				logger.error("update failed\n" + CLASSROOM0);
				Assert.fail("update fail\n");
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
			error &= classroomMapper.delete(CLASSROOM0);
			error &= classroomMapper.delete(CLASSROOM1);
			error &= classroomMapper.delete(CLASSROOM2);
			se.commit();
			if (error == 1) {
				logger.info("delete OK!");
			} else {
				logger.error("delete failed\n" + CLASSROOM0);
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
			LinkedList<Classroom> l = classroomMapper.selectAll();
			se.commit();
			for (Classroom tmp : l) {
				classroomMapper.delete(tmp);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
