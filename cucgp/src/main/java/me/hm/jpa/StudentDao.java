package me.hm.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import me.hm.m.Student;
import me.hm.m.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	RowMapper<Student> rse = new RowMapper<Student>() {
		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer userId, groupId;
			String userName, password, userHome, studentName, telephone, email, weibo, introduction, classroomName, departmentName;
			userId = rs.getInt("user_id");
			groupId = rs.getInt("group_id");
			userName = rs.getString("user_name");
			password = rs.getString("password");
			userHome = rs.getString("user_home");
			studentName = rs.getString("student_name");
			telephone = rs.getString("telephone");
			email = rs.getString("email");
			weibo = rs.getString("weibo");
			introduction = rs.getString("introduction");
			classroomName = rs.getString("classroom_name");
			departmentName = rs.getString("department_name");
			Student student = new Student(userId, groupId, userName, password,
					userHome, studentName, telephone, email, weibo,
					introduction, classroomName, departmentName);
			return student;
		}
	};

	public Student readByName(String userName) {
		String SELECTBYNAME = "SELECT u.user_id, u.group_id, u.user_name, u.password, u.user_home, u.user_name, s.student_name, s.telephone, s.email, s.weibo, s.introduction, s.classroom_name, s.department_name FROM user AS u JOIN student AS s ON u.user_name = s.user_name WHERE u.user_name = (?);";
		Student user = jdbcTemplate.queryForObject(SELECTBYNAME,
				new Object[] { userName }, rse);
		return user;
	}

	public List<Student> readAll() {
		String SELECT_ALL = "SELECT u.user_id, u.group_id, u.user_name, u.password, u.user_home, u.user_name, s.student_name, s.telephone, s.email, s.weibo, s.introduction, s.classroom_name, s.department_name FROM user AS u JOIN student AS s ON u.user_name = s.user_name;";
		List<Student> students = jdbcTemplate.query(SELECT_ALL, rse);
		return students;
	}

	public void create(Student student) {
		// STEP ONE INSERT A NEW USER !
		String CREATE2 = "INSERT INTO cucgp.`user` (group_id, user_name, password, user_home) VALUES ( ?, ?, ?, ?);";
		jdbcTemplate.update(CREATE2, student.getGroupId(),
				student.getUserName(), student.getPassword(),
				student.getUserHome());
		String LAST_INSERT = "SELECT LAST_INSERT_ID();";
		Integer id = jdbcTemplate.queryForObject(LAST_INSERT, Integer.class);
		student.setUserId(id);
		// STEP TWO INSERT A NEW STUDENT !
		String CREATE = "INSERT cucgp.`student` (user_name, student_name, telephone, email, weibo, introduction, classroom_name, department_name)"
				+ "VALUE (?, ?, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(CREATE, student.getUserName(), student.getStudentName(), student.getTelephone(), student.getEmail(), student.getWeibo(),
				student.getIntroduction(), student.getClassroomName(),
				student.getDepartmentName());
	}

	public void sync(Student student) {
		Student tmp = readById(student.getUserId());
		student.sync(tmp);
	}

	public Student readById(Integer id) {
		String SELECTBYNAME = " SELECT user_id, group_id, user_name, password, user_home "
				+ "FROM cucgp.`user` WHERE user_id = (?);";
		RowMapper<User> tmp = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer userId, groupId;
				String userName, password, userHome;
				userId = rs.getInt("user_id");
				groupId = rs.getInt("group_id");
				userName = rs.getString("user_name");
				password = rs.getString("password");
				userHome = rs.getString("user_home");
				User user = new User(userId, groupId, userName, password,
						userHome);
				return user;
			}
		};
		User user = jdbcTemplate.queryForObject(SELECTBYNAME,
				new Object[] { id }, tmp);
		return readByName(user.getUserName());
	}

	public void write(Student student) {
		// STEP ONE UPDATE A NEW USER !
		String CREATE2 = "UPDATE cucgp.`user` SET group_id = ?, user_name = ?, password = ?, user_home =? WHERE user_id = ?;";
		jdbcTemplate.update(CREATE2, student.getGroupId(),
				student.getUserName(), student.getPassword(),
				student.getUserHome(), student.getUserId());
		// STEP TWO INSERT A NEW STUDENT !
		String CREATE = "UPDATE cucgp.`student` SET student_name = ?, telephone = ?, email = ?, weibo = ?, introduction = ?, classroom_name = ?, department_name = ? WHERE user_name = ?;";
		jdbcTemplate.update(CREATE, student.getStudentName(),
				student.getTelephone(), student.getEmail(), student.getWeibo(),
				student.getIntroduction(), student.getClassroomName(),
				student.getDepartmentName(), student.getUserName());
	}

	public void delete(Student student) {
		String DELETE2 = "DELETE FROM cucgp.`student` WHERE user_name = (?);"; 
		jdbcTemplate.update(DELETE2, student.getUserName());
		String DELETE1 = "DELETE FROM cucgp.`user` WHERE user_id = (?);";
		jdbcTemplate.update(DELETE1, student.getUserId());
	}
}
