package name.hm.s.e;

public class ServerException extends Exception
{
	String msg;

	public ServerException(String msg)
	{
		this.msg = msg;
	}
	
	public String getMsg()
	{
		return msg;
	}
}
