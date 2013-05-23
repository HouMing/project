package me.hm.m;


@Deprecated
public class Tca
{
	Integer tcaId;
	String userName;
	String tcaName;
	String introduction;
	String requirement;
	Integer numerator;
	Integer applycount;
	Status status;

	public static final Status APPLY = Status.valueOf("apply");
	public static final Status PASS = Status.valueOf("pass");
  public static final Status NOTPASS = Status.valueOf("notpass");
	public static final Status PUBLISHED = Status.valueOf("published");
	public static final Status TRASH = Status.valueOf("trash");
	
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
	
	public Integer getTcaId()
	{
		return tcaId;
	}

	public void setTcaId(Integer tcaId)
	{
		this.tcaId = tcaId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public String getRequirement()
	{
		return requirement;
	}

	public void setRequirement(String requirement)
	{
		this.requirement = requirement;
	}

	public String getTcaName()
	{
		return tcaName;
	}

	public void setTcaName(String tcaName)
	{
		this.tcaName = tcaName;
	}

	public Integer getNumerator()
	{
		return numerator;
	}

	public void setNumerator(Integer numerator)
	{
		this.numerator = numerator;
	}

	public Integer getApplycount()
	{
		return applycount;
	}

	public void setApplycount(Integer applycount)
	{
		this.applycount = applycount;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public enum Status
	{
		apply("申请中"), pass("通过"), notpass("未通过"), published("已发布"), trash("已作废");
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
