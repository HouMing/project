package name.hm.pojo;

import com.alibaba.fastjson.JSON;

// TODO interrupt : #0309
public class Classroom
{
	String classroomName;

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

}
