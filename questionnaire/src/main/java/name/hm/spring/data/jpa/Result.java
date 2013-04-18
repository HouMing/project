package name.hm.spring.data.jpa;

import java.util.ArrayList;
import java.util.List;

public class Result
{
	List<Integer> lq;
	List<Integer> la;
	Integer user_oid;
	Integer survey_oid;
	
	public Result(Integer survey_oid, Integer user_oid, ArrayList<Integer> la, ArrayList<Integer> lq)
	{
		this.survey_oid = survey_oid;
		this.user_oid = user_oid;
		this.lq = lq;
		this.la = la;
	}
	
	public Integer getUser_oid()
	{
		return user_oid;
	}

	public void setUser_oid(Integer user_oid)
	{
		this.user_oid = user_oid;
	}

	public Integer getSurvey_oid()
	{
		return survey_oid;
	}

	public void setSurvey_oid(Integer survey_oid)
	{
		this.survey_oid = survey_oid;
	}

	public List<Integer> getLq()
	{
		return lq;
	}

	public void setLq(List<Integer> lq)
	{
		this.lq = lq;
	}

	public List<Integer> getLa()
	{
		return la;
	}

	public void setLa(List<Integer> la)
	{
		this.la = la;
	}
	
}
