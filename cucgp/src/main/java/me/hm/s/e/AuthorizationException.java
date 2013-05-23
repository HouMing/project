package me.hm.s.e;


public class AuthorizationException extends ServiceException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Type NoGroup = Type.valueOf("noGroup");
	public static final Type NoRole = Type.valueOf("noRole");
	public static final Type NoAction = Type.valueOf("noAction");
	public static final Type IllegalAction = Type.valueOf("illegalAction");
	public static final Type NoLogin = Type.valueOf("noLogin");
	public static final Type NoUser = Type.valueOf("noUser");
	public static final Type WrongPassword = Type.valueOf("wrongPassword");
	public static final Type InvalidUser = Type.valueOf("invalidUser");
	public static final Type NoPermission = Type.valueOf("noPermission");

	public AuthorizationException(Type type)
	{
		super(type.toString());
	}
	
	public String getMsg() {
		return this.getMessage();
	}

	enum Type
	{
		noLogin("未登录"), noUser("无此用户"), wrongPassword("密码错误"), invalidUser("无效用户"), noGroup(
				"无分组"), noRole("无角色"), noAction("无操作"), illegalAction("非法操作"), noPermission("无权执行该操作");
		String value;

		Type(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return value;
		}
	}
}
