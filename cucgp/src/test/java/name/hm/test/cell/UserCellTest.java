package name.hm.test.cell;

import name.hm.test.CellTest;

import org.junit.Test;
import org.junit.experimental.categories.Category;

//MARK
/**
 * TODO 
 * test ISUD of User Table
 */
@Category(CellTest.class)
public class UserCellTest{
  /**
   * TODO
   * insert user
   * #userId(0)
   * #userName("CellTest")
   * #password("123456")
   * #valide('invalide')
   * #userHome("/#userName/")
   */
	@Test
  public void insertUser() {
  }

  /**
   * TODO
   * select user
   * #userId(0)
   * #userName("CellTest")
   * #userName("CellTest")+#password("123456")
   * #valide('valide') => #valide('invalide')
   */
  public void selectUser() {
  }

  /**
   * TODO
   * update user
   * #valide('valide' <--> 'invalide')
   * #password("123456" <--> "654321")
   * #userHome("/#userName/" <--> "/#userName/#userName/");
   * #groupId(1 <--> 2);
   */
  public void updateUser() {
  }

  /**
   * TODO
   * delete user
   * #userId(0)
   */
  public void deleteUser() {
  }

}