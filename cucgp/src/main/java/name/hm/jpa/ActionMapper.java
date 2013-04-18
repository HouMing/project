package name.hm.jpa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import name.hm.m.Action;

public interface ActionMapper extends Mapper 
{
	public Integer insert(Action action);
	
	public ArrayList<Action> selectAll();
	public Action selectByActionId(Integer actionId);
	public ArrayList<Action> selectByRoleId(Integer roleId);
	
	@Deprecated
	public Integer update(Action action);
	
	@Deprecated
	public Integer delete(Action action);
}
