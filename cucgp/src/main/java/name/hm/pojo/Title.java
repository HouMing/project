package name.hm.pojo;

import com.alibaba.fastjson.JSON;

public class Title
{
	String titleName;

	public String getTitleName()
	{
		return titleName;
	}

	public void setTitleName(String titleName)
	{
		this.titleName = titleName;
	}

	@Override
	public String toString()
	{
		return JSON.toJSONString(this);
	}

}
