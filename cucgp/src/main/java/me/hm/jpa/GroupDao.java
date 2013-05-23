package me.hm.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import me.hm.m.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class GroupDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	public Group readById(Integer groupId) {
		String sqlOne = "SELECT group_id, group_name FROM cucgp.`group` WHERE group_id = (?);";
		try {
			RowMapper<Group> rse = new RowMapper<Group>() {
				@Override
				public Group mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Integer groupId;
					String groupName;
					groupId = rs.getInt("group_id");
					groupName = rs.getString("group_name");
					Group group = new Group(groupId, groupName, new TreeSet<Integer>());
					return group;
				}
			};

			Group group = jdbcTemplate.queryForObject(sqlOne,
					new Object[] { groupId }, rse);

			if (group == null) {
				return null;
			}

			String sqlTwo = " SELECT role_id from role_has_group WHERE group_id = (?) ORDER BY role_id ASC;";

			List<Integer> rolesId = new ArrayList<Integer>();
			rolesId = jdbcTemplate.queryForList(sqlTwo,
					new Object[] { groupId }, Integer.class);
			group.setRoles(rolesId);
			return group;
		} catch (DataAccessException e) {
			return null;
		}
	}

	public List<Group> readAll() {
		String sqlOne = "SELECT group_id, group_name FROM cucgp.`group`;";
		List<Group> groups = new ArrayList<Group>();
		try {
			RowMapper<Group> rse = new RowMapper<Group>() {
				@Override
				public Group mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Integer groupId;
					String groupName;
					groupId = rs.getInt("group_id");
					groupName = rs.getString("group_name");
					Group group = new Group(groupId, groupName, new TreeSet<Integer>());
					return group;
				}
			};
			groups = jdbcTemplate.query(sqlOne, rse);
			if (groups.size() == 0) {
				return groups;
			}
			String sqlTwo = " SELECT role_id from role_has_group WHERE group_id = (?) ORDER BY role_id ASC;";
			for (Group group : groups) {
				List<Integer> rolesId = new ArrayList<Integer>();
				rolesId = jdbcTemplate.queryForList(sqlTwo,
						new Object[] { group.getGroupId() }, Integer.class);
				group.setRoles(rolesId);
			}
			return groups;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return groups;
		}
	}

	public Integer create(Group group) {
		String INSERT = " INSERT INTO cucgp.`group` (group_name) VALUES (?);";
		String LAST_INSERT_ID = "SELECT LAST_INSERT_ID();";
		Integer ret = jdbcTemplate.update(INSERT, group.getGroupName());
		if (ret == 0) {
			return 0;
		} else {
			Integer id = jdbcTemplate.queryForObject(LAST_INSERT_ID, Integer.class);
			group.setGroupId(id);
			relation(group, true);
			return ret;
		}
	}
	
	public void write(Group group) {
		String INSERT = "UPDATE cucgp.`group` SET group_name = (?) WHERE group_id = (?);";
		// TODO Integer ret = 
		jdbcTemplate.update(INSERT, group.getGroupName(), group.getGroupId());
		relation(group, true);
	}
	
	public void read(Group group) {
		String READ = "SELECT group_name FROM cucgp.`group` WHERE group_id = (?);";
		String groupName = jdbcTemplate.queryForObject(READ, new Object[]{group.getGroupId()}, String.class);
		group.setGroupName(groupName);
		relation(group, false);
	}
	
	public Integer delete(Group group) {
		String DELETE = "DELETE FROM cucgp.`group` WHERE group_id = (?);";
		Integer ret = jdbcTemplate.update(DELETE, group.getGroupId());
		return ret;
	}

	protected void relation(Group group, boolean newer) {
		String ALL_ROLEID = "SELECT role_id from role_has_group WHERE group_id = (?);";
		String REMOVE_ROLEID = "DELETE FROM cucgp.`role_has_group` WHERE group_id = (?) AND role_id = (?);";
		String ADD_ROLEID = "INSERT INTO cucgp.`role_has_group` (group_id, role_id) VALUES (?, ?);";
		
		List<Integer> roles = jdbcTemplate.queryForList(ALL_ROLEID, new Object[]{group.getGroupId()}, Integer.class);
		
		if (newer) {
		ArrayList<Integer> remove = new ArrayList<Integer>();
		ArrayList<Integer> add = new ArrayList<Integer>();
		remove.addAll(roles);
		remove.removeAll(group.getRoles());
		add.addAll(group.getRoles());
		add.removeAll(roles);
		for (Integer roleId : remove) {
			jdbcTemplate.update(REMOVE_ROLEID, group.getGroupId(), roleId);
		}
		for (Integer roleId : add) {
			jdbcTemplate.update(ADD_ROLEID, group.getGroupId(), roleId);
		}
		}else {
		  group.setRoles(null);
		  group.setRoles(roles);
		}
	}
}
