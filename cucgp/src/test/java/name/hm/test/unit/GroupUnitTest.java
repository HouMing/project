package name.hm.test.unit;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import name.hm.jpa.GroupMapper;
import name.hm.pojo.Department;
import name.hm.pojo.Group;
import name.hm.test.BaseTestCase;

// PASS UNIT #0306
//TODO 1 PASS, Upgrade - task : #0310
public class GroupUnitTest extends BaseTestCase
{
	public static Group G_Teacher = null;
	public static String GROUP_NAME = "教师组";
	public static String GROUP_NAMEC = "教师组改";

	public static Group GROUP_ADMIN = null;
	public static String GROUP_NAME1 = "管理组";

	public static Group G_Student = null;
	public static String GROUP_NAME2 = "学生组";

	public static Group.Valid GROUP_VALID = Group.VALID;
	public static Group.Valid GROUP_INVALID = Group.INVALID;

	@Test
	public void test()
	{
		beforeTest();
		create();
		select();
		update();
		delete();
		afterTest();
	}

	private void beforeTest()
	{
		logger.info("start GroupUnitTest");
	}

	private void afterTest()
	{
		clean();
		logger.info("finish GroupUnitTest");
	}

	// PASS #0305
	synchronized public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			G_Teacher = new Group(GROUP_NAME, GROUP_VALID);
			GROUP_ADMIN = new Group(GROUP_NAME1, GROUP_VALID);
			G_Student = new Group(GROUP_NAME2, GROUP_INVALID);
			error = groupMapper.insert(G_Teacher) & error;
			error = groupMapper.insert(GROUP_ADMIN) & error;
			error = groupMapper.insert(G_Student) & error;
			se.commit();
			if (error == 1) {
				logger.info("insert OK!\n" + G_Teacher + "\n" + GROUP_ADMIN + "\n" + G_Student);
			} else {
				logger.info("insert failed\n" + G_Teacher + "\n" + GROUP_ADMIN + "\n" + G_Student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0305
	public void select()
	{
		try {
			Integer error = 1;
			openTestSession();
			Group group = groupMapper.selectByGroupId(G_Teacher.getGroupId());
			Group group1 = groupMapper.selectByGroupName(G_Student.getGroupName());
			List<Group> l = groupMapper.selectByValid(GROUP_VALID);
			List<Group> l2 = groupMapper.selectByValid(GROUP_INVALID);
			se.commit();
			if (group != null) {
				logger.info("selectByGroupId OK!\n" + group);
			} else {
				logger.error("selectByGroupId failed\n" + group);
			}
			if (group1 != null) {
				logger.info("selectByGroupName OK!\n" + group1);
			} else {
				logger.error("selectByGroupName failed\n" + group1);
			}
			if (l.size() > 0 || l2.size() > 0) {
				logger.info("selectByValid OK!\n" + l + l2);
			} else {
				logger.error("selectByValid failed\n" + l + l2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0305
	public void update()
	{
		try {
			Integer error;
			openTestSession();
			Group group = groupMapper.selectByGroupId(G_Teacher.getGroupId());
			se.commit();
			logger.info("before update\n" + group);
			group.setGroupName(GROUP_NAMEC);
			error = groupMapper.update(group);
			se.commit();
			if (error == 1) {
				group = groupMapper.selectByGroupId(G_Teacher.getGroupId());
				se.commit();
				logger.info("update OK!\n" + group);
			} else {
				group = groupMapper.selectByGroupId(G_Teacher.getGroupId());
				se.commit();
				logger.error("update failed\n" + group);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS #0305
	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
//			Group group = groupMapper.selectByGroupId(GROUP.getGroupId());
//			Group group1 = groupMapper.selectByGroupId(GROUP1.getGroupId());
//			Group group2 = groupMapper.selectByGroupId(GROUP2.getGroupId());
//			se.commit();
			error = groupMapper.delete(G_Teacher) & error;
			error = groupMapper.delete(GROUP_ADMIN) & error;
			error = groupMapper.delete(G_Student) & error;
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + G_Teacher + "\n" + GROUP_ADMIN + "\n" + G_Student);
			} else {
				logger.error("delete failed\n" + G_Teacher + "\n" + GROUP_ADMIN + "\n" + G_Student);
			}
			se.commit();
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
			LinkedList<Group> l = groupMapper.selectByValid(Group.VALID);
			LinkedList<Group> l2 = groupMapper.selectByValid(Group.INVALID);
			se.commit();
			for (Group tmp : l) {
				groupMapper.delete(tmp);
			}
			for (Group tmp : l2) {
				groupMapper.delete(tmp);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
