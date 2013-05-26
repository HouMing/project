package me.hm.m;

import com.alibaba.fastjson.JSON;


public class Stua
{
	Integer stuaId;
	Integer tcaId;
	Integer status;
	String teacherNumber;
	String studentNumber;
	
	public Stua(Integer stuaId, Integer tcaId, Integer status, String teacherNumber, String studentNumber ) {
		this.stuaId  = stuaId;
		this.tcaId = tcaId;
		this.status = status;
		this.teacherNumber = teacherNumber;
		this.studentNumber = studentNumber;
	}
	
	public Stua(Integer tcaId, Integer status, String teacherNumber, String studentNumber ) {
		this(null, tcaId, status, teacherNumber, studentNumber);
	}
	
	public String getTeacherNumber() {
		return teacherNumber;
	}

	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

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

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
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
    
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
