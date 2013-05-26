package me.hm.m;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


//@Deprecated
public class Tca
{
	Integer tcaId;
	String teacherName;
	String tcaName;
	String introduction;
	String requirement;
	Integer numerator;
	Integer applycount;
	Integer status;
	
	/*
	Status status;
	public static final Status APPLY = Status.valueOf("apply");
	public static final Status PASS = Status.valueOf("pass");
    public static final Status NOTPASS = Status.valueOf("notpass");
	public static final Status PUBLISHED = Status.valueOf("published");
	public static final Status TRASH = Status.valueOf("trash");
	*/

//	@Temp
	public Tca(Integer tcaId, String tcaName, String teacherName, String introduction, Integer status, Integer numerator, Integer applycount) {
		this.tcaId = tcaId;
		this.teacherName = teacherName;
		this.tcaName = tcaName;
		this.introduction = introduction;
		this.numerator = numerator;
		this.status = status;
		this.applycount = applycount;
		this.requirement = "测试中.";
	}
	
	public Tca(String teacherName, String tcaName, String introduction, Integer status, Integer numerator, Integer applycount) {
		this(null, tcaName, teacherName, introduction, status, numerator, applycount);
	}

	public Tca(JSONObject jsb) {
		this.tcaId = jsb.getInteger("tcaId");
		this.teacherName = jsb.getString("teacherName");
		this.tcaName = jsb.getString("tcaName");
		this.introduction = jsb.getString("introduction");
		this.requirement = jsb.getString("requirement");
		this.numerator = jsb.getInteger("numerator");
		this.applycount = jsb.getInteger("applycount");
		this.status = jsb.getInteger("status");
	}

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

	public String getTeacherName()
	{
		return teacherName;
	}

	public void setTeacherName(String userName)
	{
		this.teacherName = userName;
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

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}
	
	@Override
    public String toString() {
    	return JSON.toJSONString(this);
    }
	
	/*
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
	*/
}
