package name.hm.m;

import com.alibaba.fastjson.JSON;

// TODO TEST hashCode, equals interrupt : #0309
public class Classroom
{
	Group classroomId;
	String classroomName;

	public Classroom()
	{}

	public Classroom(String classroomName)
	{
		this(null, classroomName);
	}

	public Classroom(Group classroomId, String classroomName)
	{
		this.classroomId = classroomId;
		this.classroomName = classroomName;
	}

	public Group getClassroomId()
	{
		return classroomId;
	}

	public void setClassroomId(Group classroomId)
	{
		this.classroomId = classroomId;
	}

	public String getClassroomName()
	{
		return classroomName;
	}

	public void setClassroomName(String classroomName)
	{
		this.classroomName = classroomName;
	}

	@Override
	public String toString()
	{
		return JSON.toJSONString(this);
	}

	@Override
	public int hashCode()
	{
		return classroomName.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Classroom && obj.hashCode() == this.hashCode()) {
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
