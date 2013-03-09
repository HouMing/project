package name.hm.pojo;

//TODO TEST POJO - task : #0308
//TODO TEST hashCode, equals - interrupt : #0309
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

	@Override
	public int hashCode()
	{
		return tcaId;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Tca && obj.hashCode() == this.hashCode()) {
			if (obj.toString().equals(this.toString())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	// ///////////////////
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
