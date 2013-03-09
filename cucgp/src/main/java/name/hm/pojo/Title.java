package name.hm.pojo;

import com.alibaba.fastjson.JSON;

//TODO TEST POJO - task : #0308
//TODO TEST hashCode, equals - interrupt : #0309
public class Title
{
	Integer titleId;
	String titleName;

	public Title()
	{}

	public Title(Integer title_Id, String title_Name)
	{
		titleId = title_Id;
		titleName = title_Name;
	}

	public Title(String title_Name)
	{
		this(new Integer(0), title_Name);
	}

	public Integer getTitleId()
	{
		return titleId;
	}

	public void setTitleId(Integer titleId)
	{
		this.titleId = titleId;
	}

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

	@Override
	public int hashCode()
	{
		return titleId;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Title && obj.hashCode() == this.hashCode()) {
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
