package name.hm.test;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class BaseLogger implements ILogger
{
	private static Logger logger = Logger.getLogger("celltest");

	@Override
	public void debug(String msg, boolean b)
	{
		synchronized (logger) {
			if (!b) {
				logger.debug(msg);
			}
			Assert.assertTrue(msg, b);
		}
	}

	@Override
	public void info(String msg, boolean b)
	{
		synchronized (logger) {
			if (!b) {
				logger.info(msg);
			}
			Assert.assertTrue(msg, b);
		}
	}

	@Override
	public void warn(String msg, boolean b)
	{
		synchronized (logger) {
			if (!b) {
				logger.warn(msg);
			}
			Assert.assertTrue(msg, b);
		}
	}

	@Override
	public void error(String msg, boolean b)
	{
		synchronized (logger) {
			if (!b) {
				logger.error(msg);
			}
			Assert.assertTrue(msg, b);
		}
	}

	@Override
	public void fatal(String msg, boolean b)
	{
		synchronized (logger) {
			if (!b) {
				logger.fatal(msg);
			}
			Assert.assertTrue(msg, b);
		}
	}
}
