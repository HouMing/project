package name.hm.spring.data.jpa;

import com.alibaba.fastjson.JSON;

public class Answer
{
  Integer oid;
  Integer person_oid;
  Integer question_oid;
  Integer survey_oid;
  String item;
  
	public Answer(int rst_oid, int rst_user_oid, int rst_question_oid,
			int rst_survey_oid, String rst_item)
	{
		oid = rst_oid;
		person_oid = rst_user_oid;
		question_oid = rst_question_oid;
		survey_oid = rst_survey_oid;
		item = rst_item;
	}

	public Answer(int rst_user_oid, int rst_question_oid,
			int rst_survey_oid, String rst_item)
	{
		oid = null;
		person_oid = rst_user_oid;
		question_oid = rst_question_oid;
		survey_oid = rst_survey_oid;
		item = rst_item;
	}
	
	public Integer getOid()
	{
		return oid;
	}
	public void setOid(Integer oid)
	{
		this.oid = oid;
	}
	public Integer getPerson_oid()
	{
		return person_oid;
	}
	public void setPerson_oid(Integer person_oid)
	{
		this.person_oid = person_oid;
	}
	public Integer getQuestion_oid()
	{
		return question_oid;
	}
	public void setQuestion_oid(Integer question_oid)
	{
		this.question_oid = question_oid;
	}
	public Integer getSurvey_oid()
	{
		return survey_oid;
	}
	public void setSurvey_oid(Integer survey_oid)
	{
		this.survey_oid = survey_oid;
	}
	public String getItem()
	{
		return item;
	}
	public void setItem(String item)
	{
		this.item = item;
	}
	public String toString() {
		return JSON.toJSONString(this);
	}
}
