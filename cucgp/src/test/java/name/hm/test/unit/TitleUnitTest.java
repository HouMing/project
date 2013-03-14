package name.hm.test.unit;

import java.util.LinkedList;
import java.util.List;

import name.hm.m.Title;
import name.hm.m.User;
import name.hm.test.BaseTestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//TODO 8 PASS, Upgrade - task : #0310
public class TitleUnitTest extends BaseTestCase
{
	public static Title TITLE0 = null;
	protected static final String TITLE0_NAME = "测试职称";
	protected static final String TITLE0_NAMEC = "测试职称改";
	
	public static Title TITLE1 = null;
	protected static final String TITLE1_NAME = "教授";
	
	public static Title TITLE2 = null;
	protected static final String TITLE2_NAME = "副教授";
	
	@Test
	public void test()
	{
		create();
		read();
		update();
		delete();
	}

	@Before
	public void beforeTest()
	{
		logger.info("start TitleUnitTest");
	}

	@After
	public void afterTest()
	{
		clean();
		logger.info("finish TitleUnitTest");
	}
	
	public void create()
	{
		try {
			Integer error = 1;
			openTestSession();
			TITLE0 = new Title(TITLE0_NAME);
			TITLE1 = new Title(TITLE1_NAME);
			TITLE2 = new Title(TITLE2_NAME);
			error &= titleMapper.insert(TITLE0);
			error &= titleMapper.insert(TITLE1);
			error &= titleMapper.insert(TITLE2);
			if (error == 1) {
				logger.info("insert Title OK!\n" +"\n"+ TITLE0 +"\n"+ TITLE1 +"\n"+ TITLE2);
			} else {
				logger.error("insert Title failed\n" +"\n"+ TITLE0 +"\n"+ TITLE1 +"\n"+ TITLE2);
				Assert.fail("insert Title failed");
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void read()
	{
		try {
			Integer error = 1;
			openTestSession();
			LinkedList<Title> list = titleMapper.selectAll();
			se.commit();
			if (list.size() > 0) {
				logger.info("selectAll OK!\n" + list);
			} else {
				logger.error("selectAll failed\n" + list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void update()
	{
		try {
			Integer error = 1;
			openTestSession();
			logger.info("before update:\n" + TITLE0);
			TITLE0.setTitleName(TITLE0_NAMEC);
			error &= titleMapper.update(TITLE0);
			se.commit();
			if (error == 1) {
				TITLE0 = titleMapper.selectByTitleId(TITLE0.getTitleId());
				se.commit();			
				logger.info("update OK!\n" + TITLE0);
			} else {
				logger.error("update failed\n" + TITLE0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void delete()
	{
		try {
			Integer error = 1;
			openTestSession();
			LinkedList<Title> l = titleMapper.selectAll();
			se.commit();
			logger.info("before delete:\n" + l);
			for (Title tmp: l) {
			  error = titleMapper.delete(tmp) & error;
			}
			se.commit();
			if (error == 1) {
				l = titleMapper.selectAll();
				se.commit();
				logger.info("delete Title OK!\n" + l);
			} else {
				logger.error("delete Title failed" + l);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	private void clean()
	{
		try {
			openTestSession();
			LinkedList<Title> l = titleMapper.selectAll();
			se.commit();
			for (Title tmp : l) {
				titleMapper.delete(tmp);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

}
