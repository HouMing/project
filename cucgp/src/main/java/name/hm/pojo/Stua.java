package name.hm.pojo;

//TODO TEST POJO - task : #0308
//TODO hashCode, equals - interrupt : #0309
public class Stua
{
	Integer stuaId;
	Integer tcaId;
	Status status;
	Teacher teacher;
	Student student;

	public static Status S1 = Status.valueOf("valid");
	public static Status S2 = Status.valueOf("invalid");
	
	public enum Status
	{
		s1("valid"), s2("invalid");
		String value;

		Status(String str)
		{
			this.value = str;
		}
		
		public String toString(){
			return value;
		}
	}

}
