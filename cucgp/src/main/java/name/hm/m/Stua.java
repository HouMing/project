package name.hm.m;

public class Stua
{
	Integer stuaId;
	Integer tcaId;
	Status status;
	String teacherUserName;
	String studentUserName;

	public static Status APPLY = Status.valueOf("apply");
	public static Status PASS = Status.valueOf("pass");
	public static Status NOTPASS = Status.valueOf("notpass");
  public static Status PROCESS = Status.valueOf("process");
  public static Status FINISH = Status.valueOf("finish");
	
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

	public String getTeacherUserName()
	{
		return teacherUserName;
	}

	public void setTeacherUserName(String teacherUserName)
	{
		this.teacherUserName = teacherUserName;
	}

	public String getStudentUserName()
	{
		return studentUserName;
	}

	public void setStudentUserName(String studentUserName)
	{
		this.studentUserName = studentUserName;
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
		apply("申请中"), pass("通过"), notpass("未通过"), process("进行中"), finish("完成");
		String value;

		Status(String value)
		{
			this.value = value;
		}

		public String toString()
		{
			return value;
		}
	}

}
