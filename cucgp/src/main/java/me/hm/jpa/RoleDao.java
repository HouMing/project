package me.hm.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import me.hm.m.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleDao {
  @Autowired
  public JdbcTemplate jdbcTemplate;

  public Role readById(Integer roleId) {
    String sqlOne = "SELECT role_id, role_name FROM cucgp.`role` WHERE role_id = (?);";
    Role role = null;
    RowMapper<Role> rse = new RowMapper<Role>() {
      @Override
      public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer roleId;
        String roleName;
        roleId = rs.getInt("role_id");
        roleName = rs.getString("role_name");
        Role role = new Role(roleId, roleName, new TreeSet<Integer>(), new TreeSet<Integer>());
        return role;
      }
    };

    role = jdbcTemplate.queryForObject(sqlOne, new Object[] { roleId }, rse);

    if (role == null) {
      return null;
    }
    relation(role, false);
    return role;

  }

  public List<Role> readAll() {
    String SELECT_ALL = " SELECT role_id, role_name FROM cucgp.`role`;";
    List<Role> roles = new ArrayList<Role>();
    RowMapper<Role> rse = new RowMapper<Role>() {
      @Override
      public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer roleId;
        String roleName;
        roleId = rs.getInt("role_id");
        roleName = rs.getString("role_name");
        Role role = new Role(roleId, roleName);
        return role;
      }
    };
    roles = jdbcTemplate.query(SELECT_ALL, rse);
    if (roles.size() == 0) {
      return roles;
    }
    for (Role role : roles) {
    	relation(role, false);
    }
    return roles;
  }

  public Integer create(Role role) throws DataIntegrityViolationException {
    String CREATE =  "INSERT cucgp.`role` (role_name) VALUES (?);";
    Integer ret = jdbcTemplate.update(CREATE, role.getRoleName());
    String LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
    Integer id = jdbcTemplate.queryForObject(LAST_INSERT_ID, Integer.class);
    role.setRoleId(id);
    relation(role , true);
    return ret;
  }

  public Integer delete(Role role) {
	  String DELETE = "DELETE FROM cucgp.`role` WHERE role_id = (?);";
	  Integer ret = jdbcTemplate.update(DELETE, role.getRoleId());
	  return ret;
  }
  
  public Integer write(Role role) {
	  String UPDATE = "UPDATE cucgp.`role` SET role_name = (?) WHERE role_id = (?);";
	  Integer ret = jdbcTemplate.update(UPDATE, role.getRoleName(), role.getRoleId());
	  if (ret == 1) {
	    relation(role, true);
	    return ret;
	  } else {
        return 0;
	  }
  }
  
  public void read(Role role) {
	  String READ = "SELECT role_name FROM cucgp.`role` WHERE role_id = (?);";
	  String roleName = jdbcTemplate.queryForObject(READ, new Object[]{role.getRoleId()}, String.class);
	  role.setRoleName(roleName);
	  relation(role, false);
  }
  
  /**
   * 
   * @param role
   * @param newer 更改中间键，设置为真，否则为读取中间键。
   */
  protected void relation(Role role , boolean newer) {
    // role -> actions
    String ALL_ACTIONID = "SELECT action.action_id FROM action RIGHT JOIN role_and_action ON role_id WHERE role_id = (?);";
    String REMOVE_ACTIONID = "DELETE FROM cucgp.`role_and_action` WHERE action_id = (?) AND role_id = (?);";
    String ADD_ACTIONID = "INSERT INTO cucgp.`role_and_action` (action_id, role_id) VALUES (?, ?);";

    List<Integer> actions = jdbcTemplate.queryForList(ALL_ACTIONID, new Object[]{role.getRoleId()}, Integer.class);
    ArrayList<Integer> remove = new ArrayList<Integer>();
    ArrayList<Integer> add = new ArrayList<Integer>();

    if (newer) {
      remove.addAll(actions);
      remove.removeAll(role.getActions());
      for (Integer actionId : remove) {
        jdbcTemplate.update(REMOVE_ACTIONID, actionId, role.getRoleId());
      }
      add.addAll(role.getActions());
      add.removeAll(actions);
      for (Integer actionId : add) {
        jdbcTemplate.update(ADD_ACTIONID, actionId, role.getRoleId());
      }
    } else {
      role.setActions(null);
      role.setActions(new TreeSet<Integer>(actions));
    }
    // role -> groups
    String ALL_GROUPID = "SELECT group.group_id FROM cucgp.`group` RIGHT JOIN role_has_group ON role_id WHERE role_id = (?);";
    String REMOVE_GROUPID = "DELETE FROM cucgp.`role_has_group` WHERE group_id = (?) AND role_id = (?);";
    String ADD_GROUPID = "INSERT INTO cucgp.`role_has_group` (group_id, role_id) VALUES (?, ?);";

    List<Integer> groups = jdbcTemplate.queryForList(ALL_GROUPID, new Object[]{role.getRoleId()}, Integer.class);
    if (newer) {
      remove.clear();
      add.clear();
      remove.addAll(groups);
      remove.removeAll(role.getActions());
      for (Integer r : remove) {
        jdbcTemplate.update(REMOVE_GROUPID, r, role.getRoleId());
      }
      add.addAll(role.getActions());
      add.removeAll(groups);
      for (Integer a : add) {
        jdbcTemplate.update(ADD_GROUPID, a, role.getRoleId());
      }
    } else {
      role.setGroups(null);
      role.setGroups(groups);
    }
  }
}
