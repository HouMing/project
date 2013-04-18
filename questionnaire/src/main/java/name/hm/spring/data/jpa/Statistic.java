package name.hm.spring.data.jpa;

import com.alibaba.fastjson.JSON;

public class Statistic
{
  Integer question_oid;
  Integer num_itemA;
  Integer num_itemB;
  Integer num_itemC;
  Integer num_itemD;
  Integer num_sum;
  
	public Statistic(Integer oid, Integer num_itemA2, Integer num_itemB2,
			Integer num_itemC2, Integer num_itemD2, Integer num_sum2)
	{
		question_oid = oid;
		num_itemA = num_itemA2;
		num_itemB = num_itemB2;
		num_itemC = num_itemC2;
		num_itemD = num_itemD2;
		num_sum = num_sum2;
	}
	
	public Integer getQuestion_oid()
	{
		return question_oid;
	}
	public void setQuestion_oid(Integer question_oid)
	{
		this.question_oid = question_oid;
	}
	public Integer getNum_itemA()
	{
		return num_itemA;
	}
	public void setNum_itemA(Integer num_itemA)
	{
		this.num_itemA = num_itemA;
	}
	public Integer getNum_itemB()
	{
		return num_itemB;
	}
	public void setNum_itemB(Integer num_itemB)
	{
		this.num_itemB = num_itemB;
	}
	public Integer getNum_itemC()
	{
		return num_itemC;
	}
	public void setNum_itemC(Integer num_itemC)
	{
		this.num_itemC = num_itemC;
	}
	public Integer getNum_itemD()
	{
		return num_itemD;
	}
	public void setNum_itemD(Integer num_itemD)
	{
		this.num_itemD = num_itemD;
	}
	public Integer getNum_sum()
	{
		return num_sum;
	}
	public void setNum_sum(Integer num_sum)
	{
		this.num_sum = num_sum;
	}
  
  public String toString() {
  	return JSON.toJSONString(this);
  }
}
