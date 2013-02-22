package name.hm.test.cell;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import name.hm.jpa.GroupMapper;
import name.hm.pojo.Group;
import name.hm.test.CellTest;

//WORKING
/**
 * TODO test ISUD of Group Table
 */
@Category(CellTest.class)
  public class GroupCellTest {
    SqlSessionFactory factory;
    SqlSession se = null;
    GroupMapper mp = null;
    private static Logger logger = Logger.getLogger("celltest");
    @Before
      public void init() {
    	logger.info("start GroupCellTest");
        factory = EnvTest.getSqlSessionFactory();
        se = factory.openSession();
        mp = se.getMapper(GroupMapper.class);
      }

    @After
      public void clean() {
    	logger.info("end GroupCellTest");
        se.close();
      }

    @Test
      public void insertGroup() {
        logger.info("GroupCellTest - insertGroup\n" +
                           " PASSED 2013/02/22 20:00\n" +
                           " insert group\n" +
                           " #groupId(0)\n" +
                           " #groupName(\"CellTest\")\n" +
                           " #valide('invalide')\n");
        try {
          se.flushStatements();
          Group cellTest = new Group();
          cellTest.setGroupId(0);
          cellTest.setGroupName("CellTest");
          cellTest.setValide("invalide");
          mp.insert(cellTest);
          se.commit();
        } catch (Exception e) {
          se.rollback();
          e.printStackTrace();
        }
      }

    /**
     * TODO
     * select group
     * PASSED #groupId(0)
     * #groupName("CellTest")
     * #valide('valide') => #valide('invadile')
     */
    @Test
      public void selectGroup() {
        try {
          se.flushStatements();
          Group grp = mp.selectByGroupId(0);
          Group grp2 = mp.selectByGroupName("CellTest");
          se.commit();
          logger.info(grp.toString());
          logger.info(grp2.toString());
        } catch (Exception e) {
          se.rollback();
          e.printStackTrace();
        }
      }

    /**
     * TODO update group 
     * #groupName("CellTest" <--> "CellTestChange")
     * #valide('invalide' -> 'valide')
     */
    @Test
      public void updateGroup() {
        logger.info("S");
        StringBuilder strb = new StringBuilder();
        try {
          se.flushStatements();
          strb.append("#groupName(\"CellTest\" <--> \"CellTestChange\") \t");
          Group grp = mp.selectByGroupId(0);
          strb.append(grp + "\t");

          grp.setGroupName("CellTestChange");
          mp.update(grp);
          grp = mp.selectByGroupId(0);
          se.commit();
          strb.append(grp + "\t");

          grp.setGroupName("CellTest");
          mp.update(grp);
          grp = mp.selectByGroupId(0);
          se.commit();
          strb.append(grp + "\t");

          strb.append("#valide('invalide' -> 'valide')\t");
          grp.setValide("valide");
          mp.update(grp);
          grp = mp.selectByGroupId(0);
          se.commit();
          strb.append(grp + "\t");

          logger.info(strb.toString());
        }finally {
          se.close();
        }
      }

    /**
     * TODO delete group #groupId(0)
     */
    @Test
      public void deleteGroup() {
      }

  }
