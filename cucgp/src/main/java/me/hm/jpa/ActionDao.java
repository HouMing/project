package me.hm.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

import me.hm.m.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ActionDao
{
	@Autowired
	public JdbcTemplate jdbcTemplate;

	public Action readById(Integer actionId)
	{
		String sqlOne = "SELECT action_id, action_name, action_url, description FROM cucgp.`action` WHERE action_id = (?);";
		 RowMapper<Action> rse = new RowMapper<Action>() {
			@Override
			public Action mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Integer actionId;
				String actionName, actionUrl, description;
				actionId = rs.getInt("action_id");
				actionName = rs.getString("action_name");
				actionUrl = rs.getString("action_url");
				description = rs.getString("description");
				
				Action group = new Action(actionId, new TreeSet<Integer>(), actionName, actionUrl, description);
				return group;
			}
		};
		Action action = jdbcTemplate.queryForObject(sqlOne, new Object[]{actionId}, rse);
		if (action == null) {
			return null;
		}
		relation(action, false);
		   return action;
	}

	public void read(Action action)
	{
		String sqlOne = "SELECT action_id, action_name, action_url, description FROM cucgp.`action` WHERE action_id = (?);";
		 RowMapper<Action> rse = new RowMapper<Action>() {
			@Override
			public Action mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Integer actionId;
				String actionName, actionUrl, description;
				actionId = rs.getInt("action_id");
				actionName = rs.getString("action_name");
				actionUrl = rs.getString("action_url");
				description = rs.getString("description");
				
				Action action = new Action(actionId, new TreeSet<Integer>(), actionName, actionUrl, description);
				return action;
			}
		};
		Action tmpAction = jdbcTemplate.queryForObject(sqlOne, new Object[]{action.getActionId()}, rse);
		if (tmpAction == null) {
			return;
		}
		action.setActionId(tmpAction.getActionId());
		action.setActionName(tmpAction.getActionName());
		action.setActionUrl(tmpAction.getActionUrl());
		action.setDescription(tmpAction.getDescription());
		relation(action, false);
	}
	
	public void create(Action action) {
		String CREATE = "INSERT INTO cucgp.`action` " +
				"( action_name, action_url, description) VALUES " +
				"(?, ?, ?);";
		// TODO Integer ret = 
	    jdbcTemplate.update(CREATE, action.getActionName(), action.getActionUrl(), action.getDescription());
		String LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
		Integer id = jdbcTemplate.queryForObject(LAST_INSERT_ID, Integer.class);
		action.setActionId(id);  
		relation(action, true);
	}

	protected void relation(Action action, boolean newer) {
		String ALL_ROLEID = "SELECT role_id FROM cucgp.`role_and_action` WHERE action_id = (?);"; 
		List<Integer> tmp = jdbcTemplate.queryForList(ALL_ROLEID, new Object[]{action.getActionId()}, Integer.class);
		if (newer) {
			TreeSet<Integer> remove = new TreeSet<Integer>(tmp);
			remove.removeAll(action.getRoles());
			for (Integer removeRoleId : remove) {
				jdbcTemplate.update("DELETE FROM cucgp.`role_and_action` WHERE role_id = (?) AND action_id = (?);", removeRoleId, action.getActionId());
			}
			
			TreeSet<Integer> add = new TreeSet<Integer>(action.getRoles());
			add.removeAll(tmp);
			for (Integer addRoleId : add) {
				jdbcTemplate.update("INSERT INTO cucgp.`role_and_action` (role_id, action_id) VALUES (?, ?);", addRoleId, action.getActionId());
			}
		} else {
			action.setRoles(null);
			action.setRoles(new TreeSet<Integer>(tmp));
		}
	}

	public void update(Action action) {
		String UPDATE_ROLEID = "UPDATE cucgp.`action` SET action_name = (?), action_url = (?), description = (?) WHERE action_id = (?);";
		jdbcTemplate.update(UPDATE_ROLEID, action.getActionName(), action.getActionUrl(), action.getDescription(), action.getActionId());
		relation(action, true);
	}

	public List<Action> readAll() {
		String READALL = "SELECT action_id, action_name, action_url, description FROM cucgp.`action`;";
		 RowMapper<Action> rse = new RowMapper<Action>() {
			@Override
			public Action mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Integer actionId;
				String actionName, actionUrl, description;
				actionId = rs.getInt("action_id");
				actionName = rs.getString("action_name");
				actionUrl = rs.getString("action_url");
				description = rs.getString("description");
				
				Action action = new Action(actionId, new TreeSet<Integer>(), actionName, actionUrl, description);
				return action;
			}
		};
		List<Action> actions = jdbcTemplate.query(READALL, rse);
		if (actions.size() == 0) {
			return actions;
		}
		for (Action action : actions) {
     		relation(action, false);
		}
		return actions;
	}

	public void delete(Action action) {
		String UPDATE_ROLEID = "DELETE FROM cucgp.`action` WHERE action_id = (?);";
		jdbcTemplate.update(UPDATE_ROLEID, action.getActionId());
	}
	
}
