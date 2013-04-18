package name.hm.spring.data.jpa;

import com.alibaba.fastjson.JSON;

public class Question
{
  Integer oid;
  Integer survey_oid;
  String description;
  String itemA;
  String itemB;
  String itemC;
  String itemD;
  
  public Question(int rst_survey_oid, String rst_decription,
			String rst_itemA, String rst_itemB, String rst_itemC, String rst_itemD)
	{
		oid = null;
		survey_oid = rst_survey_oid;
		description = rst_decription;
		itemA = rst_itemA;
		itemB = rst_itemB;
		itemC = rst_itemC;
		itemD = rst_itemD;
	}
  
	public Question(int rst_oid, int rst_survey_oid, String rst_decription,
			String rst_itemA, String rst_itemB, String rst_itemC, String rst_itemD)
	{
		oid = rst_oid;
		survey_oid = rst_survey_oid;
		description = rst_decription;
		itemA = rst_itemA;
		itemB = rst_itemB;
		itemC = rst_itemC;
		itemD = rst_itemD;
	}

	public Integer getOid()
	{
		return oid;
	}

	public void setOid(Integer oid)
	{
		this.oid = oid;
	}

	public Integer getSurvey_oid()
	{
		return survey_oid;
	}

	public void setSurvey_oid(Integer survey_oid)
	{
		this.survey_oid = survey_oid;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getItemA()
	{
		return itemA;
	}

	public void setItemA(String itemA)
	{
		this.itemA = itemA;
	}

	public String getItemB()
	{
		return itemB;
	}

	public void setItemB(String itemB)
	{
		this.itemB = itemB;
	}

	public String getItemC()
	{
		return itemC;
	}

	public void setItemC(String itemC)
	{
		this.itemC = itemC;
	}

	public String getItemD()
	{
		return itemD;
	}

	public void setItemD(String itemD)
	{
		this.itemD = itemD;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
