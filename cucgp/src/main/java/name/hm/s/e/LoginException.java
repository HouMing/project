package name.hm.s.e;

import java.util.HashMap;

import name.hm.s.e.RightException.Type;

public class LoginException extends ServiceException
{
	public static final Type NoLogin = Type.valueOf("noLogin");
	public static final Type NoUser = Type.valueOf("noUser");
	public static final Type WrongPassword = Type.valueOf("wrongPassword");
	public static final Type InvalidUser = Type.valueOf("invalidUser");
	
  public LoginException(Type type){
  	super(type.toString());
  }
	enum Type {
		noLogin("未登录"),noUser("无此用户"),wrongPassword("密码错误"),invalidUser("无效用户");
		String value;
		Type(String value) {
			this.value = value;
		}
		@Override
		public String toString(){
			return value;
		}
	}
}
