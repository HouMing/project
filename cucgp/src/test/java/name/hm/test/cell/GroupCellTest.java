package name.hm.test.cell;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.alibaba.fastjson.JSON;

import name.hm.jpa.GroupMapper;
import name.hm.pojo.Group;
import name.hm.test.CellTest;

/**
 * test ISUD of Group Table
 */
@Category(CellTest.class)
  public class GroupCellTest {
    static SqlSessionFactory factory;
    static SqlSession se = null;
    static GroupMapper mp = null;
    private static Logger logger = Logger.getLogger("testcell");
    
    Integer GROUP_ID = 0;
    String GROUP_NAME = "CellTest";
    
    @BeforeClass
     static public void init() {
        factory = EnvTest.getSqlSessionFactory();
        se = factory.openSession();
        mp = se.getMapper(GroupMapper.class);
      }

    @AfterClass
     static public void clean() {
        se.close();
      }

    @Test
      public void insertGroup() {
    	logger.info("start");
        logger.info("GroupCellTest - insertGroup\n" +
                    " insert group\n" +
                    " #groupId(GROUP_ID)\n" +
                    " #groupName(\"CellTest\")\n" +
                    " #valide('invalide')\n");
        try {
          se.flushStatements();
          Group cellTest = new Group();
          cellTest.setGroupId(GROUP_ID);
          cellTest.setGroupName(GROUP_NAME);
          cellTest.setValide("invalide");
          mp.insert(cellTest);
          se.commit();
          logger.info("end");
        } catch (Exception e) {
          se.rollback();
          e.printStackTrace();
        }
      }

    /**
     * select group
     * #groupId(GROUP_ID)
     * #groupName(GROUP_NAME)
     * #valide('valide') => #valide('invadile')
     */
    @Test
      public void selectGroup() {
    	logger.info("start");
        try {
          se.flushStatements();
          Group grp = mp.selectByGroupId(GROUP_ID);
          Group grp2 = mp.selectByGroupName(GROUP_NAME);
          List<Group> lgrp = mp.selectByValide("valide");
          List<Group> lgrp2 = mp.selectByValide("invalide");
          se.commit();
          logger.info(grp.toString());
          logger.info(grp2.toString());
          logger.info(lgrp);
          logger.info(lgrp2);
          logger.info("end");
        } catch (Exception e) {
          se.rollback();
          e.printStackTrace();
        }
      }

    /**
     * #groupName("CellTest" <--> "CellTestChange")
     * #valide('invalide' -> 'valide')
     */
    @Test
      public void updateGroup() {
        logger.info("start");
        StringBuilder strb = new StringBuilder();
        try {
          se.flushStatements();
          strb.append("#groupName(\"CellTest\" <--> \"CellTestChange\") \n");
          Group grp = mp.selectByGroupId(GROUP_ID);
          strb.append(grp + "\n");

          grp.setGroupName("CellTestChange");
          mp.update(grp);
          grp = mp.selectByGroupId(GROUP_ID);
          se.commit();
          strb.append(grp + "\n");

          grp.setGroupName(GROUP_NAME);
          mp.update(grp);
          grp = mp.selectByGroupId(GROUP_ID);
          se.commit();
          strb.append(grp + "\n");

          strb.append("#valide('invalide' -> 'valide')\n");
          grp.setValide("valide");
          mp.update(grp);
          grp = mp.selectByGroupId(GROUP_ID);
          se.commit();
          strb.append(grp + "\n");
          logger.info(strb.toString());
          logger.info("end");
        } catch(Exception e) {
        	e.printStackTrace();
        }
      }

    /**
     * TODO delete group #groupId(GROUP_ID)
     */
    @Test
      public void deleteGroup() {
    	try{
    	logger.info("start");
    	se.flushStatements();
    	Group grp = mp.selectByGroupId(GROUP_ID);
    	logger.info(mp.delete(grp));
    	se.commit();
    	logger.info("end");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
      }
  }
