package name.hm.s.e;

public class ServiceException extends Exception
{
	String msg;
	public ServiceException(String msg)
	{
		this.msg = msg;
	}
	
	public String getMsg()
	{
		return msg;
	}
}