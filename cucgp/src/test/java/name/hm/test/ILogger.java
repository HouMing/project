package name.hm.test;

public interface ILogger
{

	void debug(String msg, boolean b);

	void info(String msg, boolean b);

	void warn(String msg, boolean b);

	void error(String msg, boolean b);

	void fatal(String msg, boolean b);

}
