package me.hm.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.hm.m.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	protected RowMapper<User> rse = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer userId, groupId;
			String userName, password, userHome;
			userId = rs.getInt("user_id");
			groupId = rs.getInt("group_id");
			userName = rs.getString("user_name");
			password = rs.getString("password");
			userHome = rs.getString("user_home");
			User user = new User(userId, groupId, userName, password, userHome);
			return user;
		}
	};

	public User getUser(String userName) {
		String SELECTBYNAME = " SELECT user_id, group_id, user_name, password, user_home FROM cucgp.`user` WHERE user_name = (?);";
		User user = jdbcTemplate.queryForObject(SELECTBYNAME, new Object[] { userName }, rse);
		return user;
	}

	public User readById(Integer id) {
		String SELECTBYNAME = " SELECT user_id, group_id, user_name, password, user_home "
				+ "FROM cucgp.`user` WHERE user_id = (?);";
		User user = jdbcTemplate.queryForObject(SELECTBYNAME, new Object[] { id }, rse);
		return user;
	}

	public List<User> getAll() {
		String SELECT_ALL = " SELECT user_id, group_id, user_name, password, user_home FROM cucgp.`user`;";
		List<User> users = new ArrayList<User>();
		try {
			users = jdbcTemplate.query(SELECT_ALL, rse);
			return users;
		} catch (DataAccessException e) {
			return users;
		}
	}

	public Integer create(User user) {
		String CREATE = "INSERT INTO cucgp.`user` "
				+ "(group_id, user_name, password, user_home) "
				+ "VALUES ( ?, ?, ?, ?);";
		Integer ret = jdbcTemplate.update(CREATE, user.getGroupId(),
				user.getUserName(), user.getPassword(), user.getUserHome());
		String LAST_INSERT = "SELECT LAST_INSERT_ID();";
		Integer id = jdbcTemplate.queryForObject(LAST_INSERT, Integer.class);
		user.setUserId(id);
		return ret;
	}

	public Integer write(User user) {
		String UPDATE = "UPDATE cucgp.`user` SET "
				+ "group_id = (?), user_name = (?), password = (?), user_home = (?) WHERE user_id = (?);";
		return jdbcTemplate.update(UPDATE, user.getGroupId(),
				user.getUserName(), user.getPassword(), user.getUserHome(),
				user.getUserId());
	}

	public Integer delete(User user) {
		String UPDATE = "DELETE FROM cucgp.`user` WHERE user_id = (?);";
		return jdbcTemplate.update(UPDATE, user.getUserId());
	}

	public void sync(User user) {
		User tmp = readById(user.getUserId());
		user.setGroupId(tmp.getGroupId());
		user.setPassword(tmp.getPassword());
		user.setUserName(tmp.getUserName());
		user.setUserHome(tmp.getUserHome());
	}
}
