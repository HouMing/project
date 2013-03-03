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

/**
 * test ISUD of Group Table
 */
public class GroupUnitTest extends BaseTestCase
{
	public static Integer GROUP_ID = 0;
	public static String GROUP_NAME = "CellTest";
	public static String GROUP_NAME2 = "CellTestChanged";
	public static String GROUP_VALID = "valid";
	public static String GROUP_INVALID = "invalid";

	private Integer ret;

	@Test
	public void test()
	{
		insertGroup();
		selectGroup();
		updateGroup();
		deleteGroup();
	}

	public void insertGroup()
	{
		try {
			logger.info("start");
			openTestSession();
			Group group = new Group();
			group.setGroupId(GROUP_ID);
			group.setGroupName(GROUP_NAME);
			group.setValid(GROUP_VALID);
			ret = groupMapper.insert(group);
			se.commit();
			if (ret == 1) {
				logger.info(group.toString());
			} else {
				logger.error("insertGroup failed! " + group);
			}
			logger.info("end");
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

	/**
	 * delete group
	 */
	public void deleteGroup()
	{
		try {
			logger.info("start");
			openTestSession();
			Group grp = groupMapper.selectByGroupId(GROUP_ID);
			logger.info(groupMapper.delete(grp).toString());
			se.commit();
			logger.info("end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}
}
