package name.hm.test.cell;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
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
	SqlSessionFactory factory = EnvTest.getSqlSessionFactory();

	/**
	 * TODO insert group #groupId(0) #groupName("CellTest") #valide('invalide')
	 */
	@Test
	public void insertGroup() {
		SqlSession se = factory.openSession();
		try {
			Group cellTest = new Group();
			cellTest.setGroupId(0);
			cellTest.setGroupName("CellTest");
			cellTest.setValide("invalide");
			GroupMapper mapper = se.getMapper(GroupMapper.class);
			mapper.insert(cellTest);
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			se.close();
		}
	}

	/**
	 * TODO select group #groupId(0) #groupName("CellTest") #valide('valide') =>
	 * #valide('invadile')
	 */
	@Test
	public void selectGroup() {
		SqlSession se = factory.openSession();
		try {
			GroupMapper mapper = se.getMapper(GroupMapper.class);
			Group grp = mapper.select(0);
			se.commit();
			System.out.println(grp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			se.close();
		}
	}

	/**
	 * TODO update group #groupName("CellTest" <--> "CellTestChange")
	 * #valide("invalide" -> "valide")
	 */
	@Test
	public void updateGroup() {
		SqlSession se = factory.openSession();
		try {
			
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
