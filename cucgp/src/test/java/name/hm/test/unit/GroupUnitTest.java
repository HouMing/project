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

public class GroupUnitTest extends BaseTestCase
{
	public static Integer GROUP_ID = 0;
	public static String GROUP_NAME = "测试组";
	public static String GROUP_NAME2 = "测试组改";
	public static Group.VALID GROUP_VALID = Group.getValid("valid");
	public static Group.VALID GROUP_INVALID = Group.getValid("invalid");

	private Integer ret;

	@Test
	public void test()
	{
		create();
		selectGroup();
		updateGroup();
		delete();
	}

	// FINISH
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

	public void selectGroup()
	{
		try {
			logger.info("start");
			openTestSession();
			Group grp = groupMapper.selectByGroupId(GROUP_ID);
			Group grp2 = groupMapper.selectByGroupName(GROUP_NAME);
			List<Group> lgrp = groupMapper.selectByValid(GROUP_VALID);
			List<Group> lgrp2 = groupMapper.selectByValid(GROUP_INVALID);
			se.commit();
			logger.info(grp.toString());
			logger.info(grp2.toString());
			logger.info(lgrp.toString());
			logger.info(lgrp2.toString());
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void updateGroup()
	{
		try {
			logger.info("start");
			openTestSession();
			StringBuilder strb = new StringBuilder();
			se.flushStatements();
			strb.append("#groupName(\"CellTest\" <--> \"CellTestChange\") \n");
			Group grp = groupMapper.selectByGroupId(GROUP_ID);
			strb.append(grp + "\n");

			grp.setGroupName("CellTestChange");
			groupMapper.update(grp);
			grp = groupMapper.selectByGroupId(GROUP_ID);
			se.commit();
			strb.append(grp + "\n");

			grp.setGroupName(GROUP_NAME);
			groupMapper.update(grp);
			grp = groupMapper.selectByGroupId(GROUP_ID);
			se.commit();
			strb.append(grp + "\n");

			strb.append("#valid('invalid' -> 'valid')\n");
			grp.setValid("valid");
			groupMapper.update(grp);
			grp = groupMapper.selectByGroupId(GROUP_ID);
			se.commit();
			strb.append(grp);
			logger.info(strb.toString());
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// FINISH #0305
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
