package name.hm.test.unit;

import java.util.List;

import name.hm.jpa.GroupMapper;
import name.hm.jpa.UserMapper;
import name.hm.pojo.Group;
import name.hm.pojo.User;
import name.hm.test.integration.RoleIntegrationTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * test ISUD of User Table
 */
public class UserUnitTest{

static SqlSessionFactory factory;
  static SqlSession se = null;
  static UserMapper mp = null;
  private static Logger logger = Logger.getLogger("testcell");

  public final static Integer USER_ID = 0;
  public final static String USER_NAME = "CellTest";
  public final static String USER_HOME = "/" + USER_NAME + "/";
  public final static String PASSWORD = "123456";
  static Group grp = null;

  @BeforeClass
    static public void init() {
      factory = RoleIntegrationTest.getSqlSessionFactory();
      se = factory.openSession();
      mp = se.getMapper(UserMapper.class);
      GroupMapper mp2 = se.getMapper(GroupMapper.class);
      grp = new Group();
      grp.setGroupId(GroupUnitTest.GROUP_ID);
      grp.setGroupName(GroupUnitTest.GROUP_NAME);
      grp.setValid("valid");
      mp2.insert(grp);
      se.commit();
    }

  @AfterClass
    static public void clean() {
      GroupMapper mp2 = se.getMapper(GroupMapper.class);
      grp.setGroupId(GroupUnitTest.GROUP_ID);
      grp.setGroupName(GroupUnitTest.GROUP_NAME);
      grp.setValid("valid");
      mp2.delete(grp);
      se.commit();
      se.close();
    }

  @Test
  public void cell() {
	  insertUser();
	  selectUser();
	  updateUser();
	  deleteUser();
  }

  /**
   * insert user
   * #userId(0)
   * #userName("CellTest")
   * #password("123456")
   * #valide('invalide')
   * #userHome("/#userName/")
   */
  public void insertUser() {
      logger.info("start");
      logger.info(
          "UserCellTest - insertUser\n" +
          " insert User\n" +
          " #userId(USER_ID)\n" +
          " #userName(USER_NAME)\n" +
          " #valid('invalid')"
          );
      try {
        se.flushStatements();
        User user = new User();
        user.setUserId(USER_ID);
        user.setUserName(USER_NAME);
        user.setPassword(PASSWORD );
        user.setGroupId(grp.getGroupId());
        user.setValid("invalid");
        user.setUserHome(USER_HOME);
        mp.insert(user);
        se.commit();
        logger.info("end");
      } catch (Exception e) {
        se.rollback();
        e.printStackTrace();
      }
  }

  /**
   * select user
   * #userId(0)
   * #userName("CellTest")
   * #userName("CellTest")+#password("123456")
   * #valide('valide') => #valide('invalide')
   */
  public void selectUser() {
    logger.info("start");
    try {
      se.flushStatements();
      User user = mp.selectByUserId(USER_ID);
      User user2 = mp.selectByUserName(USER_NAME);
      List<User> luser = mp.selectByValid("valid");
      List<User> luser2 = mp.selectByValid("invalid");
      List<User> luser3 = mp.selectByGroupId(grp.getGroupId());
      se.commit();
      logger.info(user.toString());
      logger.info(user2.toString());
      logger.info(luser);
      logger.info(luser2);
      logger.info(luser3);
      logger.info("end");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * update user
   * #valide('valide' <--> 'invalide')
   * #password("123456" <--> "654321")
   * #userHome("/#userName/" <--> "/#userName/#userName/");
   * #groupId(1 <--> 2);
   */
  public void updateUser() {
    logger.info("start");
    StringBuilder strb = new StringBuilder();
    try {
      se.flushStatements();
      strb.append("#userName(\"CellTest\" <--> \"CellTestChange\") \n");
      User user = mp.selectByUserId(USER_ID);
      strb.append(user + "\n");

      user.setUserName("CellTestChange");
      mp.update(user);
      user = mp.selectByUserId(USER_ID);
      se.commit();
      strb.append(user + "\n");

      user.setUserName(USER_NAME);
      mp.update(user);
      user = mp.selectByUserId(USER_ID);
      se.commit();
      strb.append(user + "\n");

      strb.append("#valid('invalid' -> 'valid')\n");
      user.setValid("valid");
      mp.update(user);
      user = mp.selectByUserId(USER_ID);
      se.commit();
      strb.append(user);
      logger.info(strb.toString());
      logger.info("end");
    } catch(Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * delete user
   * #userId(0)
   */
  public void deleteUser() {
    try{
      logger.info("start");
      se.flushStatements();
      User user = mp.selectByUserId(USER_ID);
      logger.info(mp.delete(user));
      se.commit();
      logger.info("end");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
