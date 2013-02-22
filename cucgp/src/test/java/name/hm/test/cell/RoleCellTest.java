package name.hm.test.cell;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import name.hm.jpa.RoleMapper;
import name.hm.pojo.Role;
//MARK
/**
 * TODO 
 * test ISUD of Role Table
 */
public class RoleCellTest {

  static SqlSessionFactory factory;
  static SqlSession se = null;
  static RoleMapper mp = null;
  private static Logger logger = Logger.getLogger("testcell");

  Integer ROLE_ID = 0;
  String ROLE_NAME = "CellTest";

  @BeforeClass
    static public void init() {
      factory = EnvTest.getSqlSessionFactory();
      se = factory.openSession();
      mp = se.getMapper(RoleMapper.class);
    }

  @AfterClass
    static public void clean() {
      se.close();
    }

  /**
   * TODO
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
        " #valid('invalid')"
        );
    try {
      se.flushStatements();
      Role cellTest = new Role();
      cellTest.setRoleId(ROLE_ID);
      cellTest.setRoleName(ROLE_NAME);
      cellTest.setValid("invalid");
      mp.insert(cellTest);
      se.commit();
      logger.info("end");
    } catch (Exception e) {
      se.rollback();
      e.printStackTrace();
    }
  }

  /**
   * TODO
   * select Role
   * #roleId(ROLE_ID)
   * #roleName(ROLE_NAME)
   * #valid('valid') => #valid('invadile')
   */
  @Test
    public void selectRole() {
      insertRole();
      logger.info("start");
      try {
        se.flushStatements();
        Role role = mp.selectByRoleId(ROLE_ID);
        Role role2 = mp.selectByRoleName(ROLE_NAME);
        List<Role> lrole = mp.selectByValid("valid");
        List<Role> lrole2 = mp.selectByValid("invalid");
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
   * TODO
   * update Role
   * #roleName(ROLE_NAME <--> "CellTestChange")
   * #valid('invalid' <--> 'valid')
   */
  @Test
    public void updateRole() {
      logger.info("start");
      StringBuilder strb = new StringBuilder();
      try {
        se.flushStatements();
        strb.append("#roleName(\"CellTest\" <--> \"CellTestChange\") \n");
        Role role = mp.selectByRoleId(ROLE_ID);
        strb.append(role + "\n");

        role.setRoleName("CellTestChange");
        mp.update(role);
        role = mp.selectByRoleId(ROLE_ID);
        se.commit();
        strb.append(role + "\n");

        role.setRoleName(ROLE_NAME);
        mp.update(role);
        role = mp.selectByRoleId(ROLE_ID);
        se.commit();
        strb.append(role + "\n");

        strb.append("#valid('invalid' -> 'valid')\n");
        role.setValid("valid");
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
   * TODO
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
