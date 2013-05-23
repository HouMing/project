package me.hm.s.e;

public class ServerException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
