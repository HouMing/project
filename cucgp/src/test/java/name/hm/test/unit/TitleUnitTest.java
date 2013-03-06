package name.hm.test.unit;

import java.util.LinkedList;
import java.util.List;

import name.hm.pojo.Title;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.Test;

// PASS UNIT #0306
public class TitleUnitTest extends BaseTestCase
{
	static public String TITLE_NAME = "测试职称";
	static public String TITLE_NAMEC = "测试职称改";
	static public String TITLE_NAME1 = "测试职称1";
	static public String TITLE_NAME2 = "测试职称2";
	static public String TITLE_NAME3 = "测试职称3";
	static public String TITLE_NAME4 = "测试职称4";

	@Test
	public void test()
	{
		beforeTest();
		create();
		read();
		update();
		delete();
		afterTest();
	}

	// PASS CELL #0306
	public void beforeTest()
	{
		logger.info("start TitleUnitTest");
	}

	public void afterTest()
	{
		logger.info("finish TitleUnitTest");
	}
	
	// PASS CELL #0306
	public void create()
	{
		try {
			Integer error;
			openTestSession();
			Title title = new Title(TITLE_NAME);
			Title title1 = new Title(TITLE_NAME1);
			Title title2 = new Title(TITLE_NAME2);
			Title title3 = new Title(TITLE_NAME3);
			Title title4 = new Title(TITLE_NAME4);
			error = titleMapper.insert(title);
			error = titleMapper.insert(title1) & error;
			error = titleMapper.insert(title2) & error;
			error = titleMapper.insert(title3) & error;
			error = titleMapper.insert(title4) & error;
			if (error == 1) {
				logger.info("insert Title OK!\n" + title + title1 + title2 + title3
						+ title4);
			} else {
				logger.info("insert Title failed\n" + title + title1 + title2 + title3
						+ title4);
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0306
	public void read()
	{
		try {
			Integer error;
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

	// PASS CELL #0306
	public void update()
	{
		try {
			Integer error;
			openTestSession();
			LinkedList<Title> l = titleMapper.selectAll();
			Title title = l.getFirst();
			se.commit();
			logger.info("before update:\n" + l);
			title.setTitleName(TITLE_NAMEC);
			error = titleMapper.update(title);
			se.commit();
			if (error == 1) {
				l = titleMapper.selectAll();
				se.commit();			
				logger.info("update OK!\n" + l);
			} else {
				logger.error("update failed\n" + l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	// PASS CELL #0306
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
}
