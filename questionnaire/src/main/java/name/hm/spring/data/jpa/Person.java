package name.hm.spring.data.jpa;

import com.alibaba.fastjson.JSON;

public class Person
{
	Integer oid;
	String username;
	String password;
	Integer right;

	public Person (Integer oid, String name, String pwd, Integer right) {
		this.oid = oid;
		this.username = name;
		this.password = pwd;
		this.right = right;
	}

	public Integer getOid()
	{
		return oid;
	}

	public void setOid(Integer oid)
	{
		this.oid = oid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Integer getRight()
	{
		return right;
	}

	public void setRight(Integer right)
	{
		this.right = right;
	}
	
	public String toString() {
		return JSON.toJSONString(this);
	}
}
