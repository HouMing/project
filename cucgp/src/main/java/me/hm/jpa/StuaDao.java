package me.hm.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.hm.m.Stua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class StuaDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	RowMapper<Stua> rse = new RowMapper<Stua>() {
		@Override
		public Stua mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer stuaId, tcaId, status;
			String teacherNumber, studentNumber;
			stuaId = rs.getInt("stua_id");
			tcaId = rs.getInt("tca_id");
			status = rs.getInt("status");
			teacherNumber = rs.getString("teacherNumber");
			studentNumber = rs.getString("studentNumber");
			Stua stua = new Stua(stuaId, tcaId, status, teacherNumber, studentNumber);
			return stua; 
		}
	};

	public void create(Stua stua) {
       String sql = "INSERT INTO cucgp.`stua` (tca_id, status, teacher_number, student_number) VALUES (?, ?, ?, ?);";		
       jdbcTemplate.update(sql, stua.getTcaId(), stua.getStatus(), stua.getTeacherNumber(), stua.getStudentNumber());
       int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Integer.class);
       stua.setStuaId(id);
	}

}
