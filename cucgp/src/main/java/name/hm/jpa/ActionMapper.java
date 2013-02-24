package name.hm.jpa;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import name.hm.pojo.Action;

public interface ActionMapper {

	final String SELECT_BY_ACTIONID = "SELECT * FROM cucgp.`action` WHERE action_id = #{param1}";
	final String SELECT_BY_ACTIONNAME = "SELECT * FROM cucgp.`action` WHERE action_name = #{param1}";
	final String SELECT_BY_ACTIONSTATUS = "SELECT * FROM cucgp.`action` WHERE action_status = #{param1}";
	final String INSERT = "INSERT INTO cucgp.`action`" +
			              "(action_id, action_name, action_url, workflow_id, role_id, action_status) VALUES " +
			              "(#{actionId}, #{actionName}, #{actionUrl}, #{workflowId}, #{roleId}, #{actionStatus})";
	final String UPDATE = "UPDATE cucgp.`action` " +
			              "SET action_name = #{actionName}, action_url = #{actionUrl}," +
			              "workflow_id = #{workflowId}, role_id = #{roleId}, action_status = #{actionStatus}" +
			              "WHERE action_id = #{actionId}";
	final String DELETE = "DELETE FROM cucgp.`action` WHERE action_id = #{actionId}";
	
	//Basic CRUD method
	@Select(SELECT_BY_ACTIONID)
	@Results(value = {
			@Result(property="actionId",column="action_id"),
			@Result(property="actionName",column="action_name"),
			@Result(property="actionUrl",column="action_url"),
			@Result(property="roleId",column="role_id"),
			@Result(property="workflowId",column="workflow_id"),
			@Result(property="actionStatus",column="action_status")
	})
	Action selectByActionId(Integer actionId);
	
	@Select(SELECT_BY_ACTIONNAME)
	@Results(value = {
			@Result(property="actionId",column="action_id"),
			@Result(property="actionName",column="action_name"),
			@Result(property="actionUrl",column="action_url"),
			@Result(property="roleId",column="role_id"),
			@Result(property="workflowId",column="workflow_id"),
			@Result(property="actionStatus",column="action_status")
	})
	Action selectByActionName(String actionName);

	@Select(SELECT_BY_ACTIONSTATUS)
	@Results(value = {
			@Result(property="actionId",column="action_id"),
			@Result(property="actionName",column="action_name"),
			@Result(property="actionUrl",column="action_url"),
			@Result(property="roleId",column="role_id"),
			@Result(property="workflowId",column="workflow_id"),
			@Result(property="actionStatus",column="action_status")
	})
	List<Action> selectByActionStatus(String string);

	@Insert(INSERT)
	Integer insert(Action action);
	
	@Update(UPDATE)
	Integer update(Action action);

	@Delete(DELETE)
	Integer delete(Action action);

}