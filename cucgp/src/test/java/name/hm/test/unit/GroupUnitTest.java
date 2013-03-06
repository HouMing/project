package name.hm.test.unit;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import name.hm.jpa.GroupMapper;
import name.hm.pojo.Group;
import name.hm.test.BaseTestCase;

// PASS UNIT #0306
public class GroupUnitTest extends BaseTestCase
{
	public static Integer GROUP_ID = 0;
	public static String GROUP_NAME = "测试组";
	public static String GROUP_NAMEC = "测试组改";
	public static String GROUP_NAME2 = "测试组2";
	public static Group.VALID GROUP_VALID = Group.getValid("valid");
	public static Group.VALID GROUP_INVALID = Group.getValid("invalid");

	private Integer ret;

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
		logger.info("finish GroupUnitTest");
	}

	// PASS #0305
	public void create()
	{
		try {
			Integer ret;
			openTestSession();
			Group group = new Group(GROUP_ID, GROUP_NAME, GROUP_VALID);
			ret = groupMapper.insert(group);
			se.commit();
			if (ret == 1) {
				logger.info("insert OK! : " + group);
			} else {
				logger.error("insert failed! " + group);
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
			Integer error;
			openTestSession();
			Group group = groupMapper.selectByGroupId(GROUP_ID);
			Group group2 = groupMapper.selectByGroupName(GROUP_NAME);
			List<Group> l = groupMapper.selectByValid(GROUP_VALID);
			List<Group> l2 = groupMapper.selectByValid(GROUP_INVALID);
			se.commit();
			if (group != null) {
				logger.info("selectByGroupId OK!\n" + group);
			} else {
				logger.error("selectByGroupId failed\n" + group);
			}
			if (group2 != null) {
				logger.info("selectByGroupName OK!\n" + group2);
			} else {
				logger.error("selectByGroupName failed\n" + group2);
			}
			if (l.size() > 0 || l2.size() > 0) {
				logger.info("selectByValid OK!\n" + l + l2);
			} else {
				logger.error("selectByValid failed!");
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
			logger.info("before update:\n" + group);
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
				logger.error("update OK!\n" + group);
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
			Integer ret;
			openTestSession();
			Group group = groupMapper.selectByGroupId(GROUP_ID);
			ret = groupMapper.delete(group);
			if (ret == 1) {
				logger.info("delete OK! : " + group);
			} else {
				logger.error("delete failed : " + group);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
