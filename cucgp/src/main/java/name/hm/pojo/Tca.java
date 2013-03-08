package name.hm.pojo;

// TODO TEST POJO
public class Tca
{
	Integer tcaId;
	String userName;
	String introduction;
	String requirement;
	String tcaName;
	Integer numerator;
	Integer applycount;
	Status status;

	public static final Status S1 = Status.valueOf("valid");
	public static final Status S2 = Status.valueOf("invalid");
	
	public enum Status
	{
		s1("valid"), s2("invalid");
		String value;

		Status(String value)
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
