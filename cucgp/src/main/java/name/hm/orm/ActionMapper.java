package name.hm.orm;

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

@Repository
public interface ActionMapper extends Mapper 
{
	final String INSERT = "INSERT INTO cucgp.`action`" +
			"(action_id, action_name, action_url, role_id, description) VALUES " +
			"(#{actionId}, #{actionName}, #{actionUrl}, #{roleId}, #{description})";
	
	final String SELECT_BY_ACTIONID = "SELECT * FROM cucgp.`action` WHERE action_id = #{param1}";
	final String SELECT_BY_ACTIONNAME = "SELECT * FROM cucgp.`action` WHERE action_name = #{param1}";
	final String SELECT_ALL = "SELECT * FROM cucgp.`action` ORDER BY action_id ASC";
	
	final String UPDATE = "UPDATE cucgp.`action` " +
			              "SET action_name = #{actionName}, action_url = #{actionUrl}," +
			              "role_id = #{roleId}, description = #{description}" +
			              "WHERE action_id = #{actionId}";
	final String DELETE = "DELETE FROM cucgp.`action` WHERE action_id = #{actionId}";
	

	@Insert(INSERT)
	@SelectKey(statement = "SELECT MAX(action_id) AS action_id FROM cucgp.`action`", before = false, keyProperty = "actionId", resultType = Integer.class)
	public Integer insert(Action action);
	
	@Select(SELECT_BY_ACTIONID)
	@Results(value = {
			@Result(property="actionId",column="action_id"),
			@Result(property="actionName",column="action_name"),
			@Result(property="actionUrl",column="action_url"),
			@Result(property="roleId",column="role_id"),
			@Result(property="description",column="description")
	})
	public Action selectByActionId(Integer actionId);
	
	@Select(SELECT_BY_ACTIONNAME)
	@Results(value = {
			@Result(property="actionId",column="action_id"),
			@Result(property="actionName",column="action_name"),
			@Result(property="actionUrl",column="action_url"),
			@Result(property="roleId",column="role_id"),
			@Result(property="description",column="description")
	})
	public Action selectByActionName(String actionName);

	@Select(SELECT_ALL)
	@Results(value = {
			@Result(property="actionId",column="action_id"),
			@Result(property="actionName",column="action_name"),
			@Result(property="actionUrl",column="action_url"),
			@Result(property="roleId",column="role_id"),
			@Result(property="description",column="description")
	})
	public ArrayList<Action> selectAll();
	
	@Update(UPDATE)
	public Integer update(Action action);

	@Delete(DELETE)
	public Integer delete(Action action);

}
