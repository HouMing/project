package name.hm.test.unit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public interface POJOTest
{

	@Before
	public void beforeTest();

	@After
	public void afterTest();

	@Test
	public void test();
	
	public void create();

	public void read();

	public void update();

	public void delete();

	public void clean();
}
