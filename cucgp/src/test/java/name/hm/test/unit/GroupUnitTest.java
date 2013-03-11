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
//TODO 1 UNIT, Upgrade - task : #0310
public class GroupUnitTest extends BaseTestCase
{
	public static Integer GROUP_ID = null;
	public static String GROUP_NAME = "测试组";
	public static String GROUP_NAMEC = "测试组改";

	public static Integer GROUP_ID1 = null;
	public static String GROUP_NAME1 = "测试组1";

	public static Integer GROUP_ID2 = null;
	public static String GROUP_NAME2 = "测试组2";

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
			Group group = new Group(GROUP_ID, GROUP_NAME, GROUP_VALID);
			Group group1 = new Group(GROUP_ID1, GROUP_NAME1, GROUP_VALID);
			Group group2 = new Group(GROUP_ID2, GROUP_NAME2, GROUP_INVALID);
			error = groupMapper.insert(group) & error;
			error = groupMapper.insert(group1) & error;
			error = groupMapper.insert(group2) & error;
			se.commit();
			if (error == 1) {
				logger.info("insert OK!\n" + group + "\n" + group1 + "\n" + group2);
			} else {
				logger.info("insert failed\n" + group + "\n" + group1 + "\n" + group2);
			}
			GROUP_ID = group.getGroupId();
			GROUP_ID1 = group1.getGroupId();
			GROUP_ID2 = group2.getGroupId();
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
			Group group = groupMapper.selectByGroupId(GROUP_ID);
			Group group1 = groupMapper.selectByGroupName(GROUP_NAME1);
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
			Group group = groupMapper.selectByGroupId(GROUP_ID);
			se.commit();
			logger.info("before update\n" + group);
			group.setGroupName(GROUP_NAMEC);
			error = groupMapper.update(group);
			se.commit();
			if (error == 1) {
				group = groupMapper.selectByGroupId(GROUP_ID);
				se.commit();
				logger.info("update OK!\n" + group);
			} else {
				group = groupMapper.selectByGroupId(GROUP_ID);
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
			Group group = groupMapper.selectByGroupId(GROUP_ID);
			Group group1 = groupMapper.selectByGroupId(GROUP_ID1);
			Group group2 = groupMapper.selectByGroupId(GROUP_ID2);
			se.commit();
			error = groupMapper.delete(group) & error;
			error = groupMapper.delete(group1) & error;
			error = groupMapper.delete(group2) & error;
			se.commit();
			if (error == 1) {
				logger.info("delete OK!\n" + group);
			} else {
				logger.error("delete failed\n" + group);
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
