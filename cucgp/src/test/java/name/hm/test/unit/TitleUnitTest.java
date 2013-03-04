package name.hm.test.unit;

import java.util.LinkedList;

import name.hm.pojo.Title;
import name.hm.pojo.User;
import name.hm.test.BaseTestCase;

import org.junit.Test;

public class TitleUnitTest extends BaseTestCase
{
	static public String TITLE_NAME = "测试职称";
	static public String TITLE_NAME2 = "测试职称2";

	@Test
	public void test()
	{
		beforeTest();
		insertTitle();
		selectTitle();
		updateTitle();
		deleteTitle();
		afterTest();
	}

	public void deleteTitle()
	{
		try {
			Integer ret;
			openTestSession();
			Title title = titleMapper.selectAll().getFirst();
	    ret = titleMapper.delete(title);
	    if (ret == 1) {
	    	logger.info("delete Title successfully");
	    } else {
	    	logger.error("delete Title failed");
	    }
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void updateTitle()
	{
		try {
			Integer ret;
			openTestSession();
			Title title = titleMapper.selectAll().getFirst();
			title.setTitleName(TITLE_NAME2);
			ret = titleMapper.update(title);
			if(ret == 1) {
				logger.info("update Title successfully");
			} else {
				logger.error("update Title failed!");
			}
			se.commit();
			title.setTitleName(TITLE_NAME);
			ret = titleMapper.update(title);
			if(ret == 1) {
				logger.info("update Title successfully");
			} else {
				logger.error("update Title failed!");
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void selectTitle()
	{
		try {
			openTestSession();
			LinkedList<Title> list = titleMapper.selectAll();
			if (list.size() == 1) {
				logger.info("selectAll : " + list);
			} else {
				logger.error("selectAll : " + list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void insertTitle()
	{
		try {
			Integer ret;
			openTestSession();
			Title title = new Title();
			title.setTitleName(TITLE_NAME);
			ret = titleMapper.insert(title);
			if (ret == 1) {
				logger.info("insert Title successfully!");
			} else {
				logger.error("insert Title failed!");
			}
			se.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTestSession();
		}
	}

	public void beforeTest()
	{
		logger.info("start");
	}

	public void afterTest()
	{
		logger.info("end");
	}

}
