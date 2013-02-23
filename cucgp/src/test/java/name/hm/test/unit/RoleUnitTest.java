package name.hm.test.unit;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import name.hm.jpa.RoleMapper;
import name.hm.pojo.Role;
import name.hm.test.integration.RoleIntegrationTest;

/**
 * test ISUD of Role Table
 */
public class RoleUnitTest {

  static SqlSessionFactory factory;
  static SqlSession se = null;
  static RoleMapper mp = null;
  private static Logger logger = Logger.getLogger("testcell");

  public static Integer ROLE_ID = 0;
  public static String ROLE_NAME = "CellTest";
  public static String ROLE_VALID = "valid";

  @BeforeClass
    static public void init() {
      factory = RoleIntegrationTest.getSqlSessionFactory();
      se = factory.openSession();
      mp = se.getMapper(RoleMapper.class);
    }

  @AfterClass
    static public void clean() {
      se.close();
    }

  /**
   * insert Role
   * #roleId(ROLE_ID)
   * #roleName(ROLE_NAME)
   * #valid('invalid')
   */
  public void insertRole() {
    logger.info("start");
    logger.info(
        "RoleCellTest - insertRole\n" +
        " insert Role\n" +
        " #roleId(ROLE_ID)\n" +
        " #roleName(ROLE_NAME)\n" +
        " #valid(ROLE_VALID)"
        );
    try {
      se.flushStatements();
      Role cellTest = new Role();
      cellTest.setRoleId(ROLE_ID);
      cellTest.setRoleName(ROLE_NAME);
      cellTest.setValid(ROLE_VALID);
      mp.insert(cellTest);
      se.commit();
      logger.info("end");
    } catch (Exception e) {
      se.rollback();
      e.printStackTrace();
    }
  }

  /**
   * select Role
   * #roleId(ROLE_ID)
   * #roleName(ROLE_NAME)
   * #valid('valid') => #valid('invadile')
  @Test
   */
    public void selectRole() {
      insertRole();
      logger.info("start");
      try {
        se.flushStatements();
        Role role = mp.selectByRoleId(ROLE_ID);
        Role role2 = mp.selectByRoleName(ROLE_NAME);
        List<Role> lrole = mp.selectByValid(ROLE_VALID);
        List<Role> lrole2 = mp.selectByValid("in" + ROLE_VALID);
        se.commit();
        logger.info(role.toString());
        logger.info(role2.toString());
        logger.info(lrole);
        logger.info(lrole2);
        logger.info("end");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  /**
   * update Role
   */
  @Test
    public void updateRole() {
      logger.info("start");
      StringBuilder strb = new StringBuilder();
      try {
        se.flushStatements();
        strb.append("#roleName(\"CellTest\" <--> \"CellTestChange\")\n");
        Role role = mp.selectByRoleId(ROLE_ID);
        strb.append(role + "\n");

        role.setRoleName(ROLE_NAME + "Changed");
        mp.update(role);
        role = mp.selectByRoleId(ROLE_ID);
        se.commit();
        strb.append(role + "\n");

        role.setRoleName(ROLE_NAME);
        mp.update(role);
        role = mp.selectByRoleId(ROLE_ID);
        se.commit();
        strb.append(role + "\n");

        strb.append("#valid('valid' -> 'invalid')\n");
        role.setValid(ROLE_VALID);
        mp.update(role);
        role = mp.selectByRoleId(ROLE_ID);
        se.commit();
        strb.append(role);
        logger.info(strb.toString());
        logger.info("end");
      } catch(Exception e) {
        e.printStackTrace();
      }
    }

  /**
   * delete Role
   * #roleId(ROLE_ID)
   */
  @Test
    public void deleteRole() {
      try{
        logger.info("start");
        se.flushStatements();
        Role role = mp.selectByRoleId(ROLE_ID);
        logger.info(mp.delete(role));
        se.commit();
        logger.info("end");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

}
