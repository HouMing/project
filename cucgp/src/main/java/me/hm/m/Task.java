package me.hm.m;

import com.alibaba.fastjson.JSON;


@Deprecated
public class Task
{
	Integer taskId;
	Integer stuaId;
	Student student;
	Teacher teacher;
	String target;
	String demands;
	Float firstScore;
	Float secondScore;
	Float thirdScore;

	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	public Integer getStuaId()
	{
		return stuaId;
	}

	public void setStuaId(Integer stuaId)
	{
		this.stuaId = stuaId;
	}

	public Student getStudent()
	{
		return student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	public Teacher getTeacher()
	{
		return teacher;
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	public String getTarget()
	{
		return target;
	}

	public void setTarget(String target)
	{
		this.target = target;
	}

	public String getDemands()
	{
		return demands;
	}

	public void setDemands(String demands)
	{
		this.demands = demands;
	}

	public Float getFirstScore()
	{
		return firstScore;
	}

	public void setFirstScore(Float firstScore)
	{
		this.firstScore = firstScore;
	}

	public Float getSecondScore()
	{
		return secondScore;
	}

	public void setSecondScore(Float secondScore)
	{
		this.secondScore = secondScore;
	}

	public Float getThirdScore()
	{
		return thirdScore;
	}

	public void setThirdScore(Float thirdScore)
	{
		this.thirdScore = thirdScore;
	}

	@Override
	public String toString()
	{
		return JSON.toJSONString(this);
	}

	@Override
	public int hashCode()
	{
		return taskId;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Task && obj.hashCode() == this.hashCode()) {
			if (obj.toString().equals(this.toString())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
