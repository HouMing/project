package name.hm.pojo;

//TODO TEST POJO - task : #0308
//TODO TEST hashCode, equals - interrupt : #0309
public class Stua
{
	Integer stuaId;
	Integer tcaId;
	Status status;
	Teacher teacher;
	Student student;

	public static Status S1 = Status.valueOf("valid");
	public static Status S2 = Status.valueOf("invalid");

	public Integer getStuaId()
	{
		return stuaId;
	}

	public void setStuaId(Integer stuaId)
	{
		this.stuaId = stuaId;
	}

	public Integer getTcaId()
	{
		return tcaId;
	}

	public void setTcaId(Integer tcaId)
	{
		this.tcaId = tcaId;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public Teacher getTeacher()
	{
		return teacher;
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	public Student getStudent()
	{
		return student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	@Override
	public int hashCode()
	{
		return stuaId;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Stua && obj.hashCode() == this.hashCode()) {
			if (obj.toString().equals(this.toString())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public enum Status
	{
		s1("valid"), s2("invalid");
		String value;

		Status(String str)
		{
			this.value = str;
		}

		public String toString()
		{
			return value;
		}
	}

}
