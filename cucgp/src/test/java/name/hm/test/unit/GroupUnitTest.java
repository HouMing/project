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
import name.hm.test.BaseLogger.*;

/**
 * test ISUD of Group Table
 */
public class GroupUnitTest extends BaseTestCase
{

	public static final Integer GROUP_ID = 0;
	public static final String GROUP_NAME = "CellTest";

	public void insertGroup()
	{
		INFO.isTrue("start", false);
		try {
			se.flushStatements();
			Group cellTest = new Group();
			cellTest.setGroupId(GROUP_ID);
			cellTest.setGroupName(GROUP_NAME);
			cellTest.setValid("invalid");
			groupMapper.insert(cellTest);
			se.commit();
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * select group #groupId(GROUP_ID) #groupName(GROUP_NAME) #valid('valid') =>
	 * #valid('invadile')
	 */
	@Test
	public void selectGroup()
	{
		insertGroup();
		INFO.isTrue("start", false);
		try {
			se.flushStatements();
			Group grp = groupMapper.selectByGroupId(GROUP_ID);
			Group grp2 = groupMapper.selectByGroupName(GROUP_NAME);
			List<Group> lgrp = groupMapper.selectByValid("valid");
			List<Group> lgrp2 = groupMapper.selectByValid("invalid");
			se.commit();
			INFO.isTrue(grp.toString(), false);
			INFO.isTrue(grp2.toString(), false);
			INFO.isTrue(lgrp.toString(), false);
			INFO.isTrue(lgrp2.toString(), false);
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * #groupName("CellTest" <--> "CellTestChange") #valid('invalid' -> 'valid')
	 */
	@Test
	public void updateGroup()
	{
		INFO.isTrue("start", false);
		StringBuilder strb = new StringBuilder();
		try {
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
			INFO.isTrue(strb.toString(), false);
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * delete group #groupId(GROUP_ID)
	 */
	@Test
	public void deleteGroup()
	{
		try {
			INFO.isTrue("start", false);
			se.flushStatements();
			Group grp = groupMapper.selectByGroupId(GROUP_ID);
			INFO.isTrue(groupMapper.delete(grp).toString(), false);
			se.commit();
			INFO.isTrue("end", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
