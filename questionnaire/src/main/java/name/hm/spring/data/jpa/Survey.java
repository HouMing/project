package name.hm.spring.data.jpa;

import java.util.ArrayList;
import java.util.List;

public class Survey
{
	Integer oid;
	String name;
	List<Integer> lq;
	List<Result> lr;

	public Survey(Integer rst_oid, String rst_name, ArrayList<Integer> rst_lq, ArrayList<Result> rst_result)
	{
		oid = rst_oid;
		name = rst_name;
		lq = rst_lq;
		lr = rst_result;
	}

	public Survey(Integer rst_oid, String rst_name)
	{
		oid = rst_oid;
		name = rst_name;
		lq = null;
		lr = null;
	}

	public Survey(String rst_name)
	{
		this.name = rst_name;
	}

	public Integer getOid()
	{
		return oid;
	}

	public void setOid(Integer oid)
	{
		this.oid = oid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Integer> getLq()
	{
		return lq;
	}

	public void setLq(List<Integer> lq)
	{
		this.lq = lq;
	}

	public List<Result> getLr()
	{
		return lr;
	}

	public void setLr(List<Result> lr)
	{
		this.lr = lr;
	}
	
}
