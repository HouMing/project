package me.hm.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import me.hm.m.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TeacherDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	RowMapper<Teacher> rse = new RowMapper<Teacher>() {
		@Override
		public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer userId, groupId;
			String userName, password, userHome, studentName, telephone, email, weibo, introduction, classroomName, departmentName;
			userId = rs.getInt("user_id");
			groupId = rs.getInt("group_id");
			userName = rs.getString("user_name");
			password = rs.getString("password");
			userHome = rs.getString("user_home");
			studentName = rs.getString("teacher_name");
			telephone = rs.getString("telephone");
			email = rs.getString("email");
			weibo = rs.getString("weibo");
			introduction = rs.getString("introduction");
			classroomName = rs.getString("title_name");
			departmentName = rs.getString("department_name");
			Teacher student = new Teacher(userId, groupId, userName, password, userHome, studentName, telephone, email, weibo,
					introduction, classroomName, departmentName);
			return student;
		}
	};

	public Teacher readByName(String userName) {
		String SELECTBYNAME = "SELECT u.user_id, u.group_id, u.user_name, u.password, u.user_home, u.user_name, s.teacher_name, s.telephone, s.email, s.weibo, s.introduction, s.title_name, s.department_name FROM user AS u JOIN teacher AS s ON u.user_name = s.user_name WHERE u.user_name = (?);";
		Teacher user = jdbcTemplate.queryForObject(SELECTBYNAME, new Object[] { userName }, rse);
		return user;
	}

	public List<Teacher> readAll() {
		String SELECT_ALL = "SELECT u.user_id, u.group_id, u.user_name, u.password, u.user_home, u.user_name, s.teacher_name, s.telephone, s.email, s.weibo, s.introduction, s.title_name, s.department_name FROM user AS u JOIN teacher AS s ON u.user_name = s.user_name;";
		List<Teacher> students = jdbcTemplate.query(SELECT_ALL, rse);
		return students;
	}

	public void create(Teacher student) {
		// STEP ONE INSERT A NEW USER !
		String CREATE2 = "INSERT INTO cucgp.`user` (group_id, user_name, password, user_home) VALUES ( ?, ?, ?, ?);";
		jdbcTemplate.update(CREATE2, student.getGroupId(),
				student.getUserName(), student.getPassword(),
				student.getUserHome());
		String LAST_INSERT = "SELECT LAST_INSERT_ID();";
		Integer id = jdbcTemplate.queryForObject(LAST_INSERT, Integer.class);
		student.setUserId(id);
		// STEP TWO INSERT A NEW STUDENT !
		String CREATE = "INSERT cucgp.`teacher` (user_name, teacher_name, telephone, email, weibo, introduction, title_name, department_name)"
				+ "VALUE (?, ?, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(CREATE, student.getUserName(), student.getTeacherName(), student.getTelephone(), student.getEmail(), student.getWeibo(),
				student.getIntroduction(), student.getTitleName(), student.getDepartmentName());
	}

}
