package name.hm.s.e;

import name.hm.m.User;

public class RightException extends ServiceException
{
	public static final Type NoGroup = Type.valueOf("noGroup");
	public static final Type NoRole = Type.valueOf("noRole");
	public static final Type NoAction = Type.valueOf("noAction");
	public static final Type IllegalAction = Type.valueOf("illegalAction");
	
	public RightException(Type type)
	{
		super(type.toString());
	}
	
	enum Type {
		noGroup("无分组"), noRole("无角色"), noAction("无操作"), illegalAction("非法操作");
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
