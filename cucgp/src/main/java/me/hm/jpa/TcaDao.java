package me.hm.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.hm.m.Tca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TcaDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	RowMapper<Tca> rse = new RowMapper<Tca>() {
		@Override
		public Tca mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer tcaId, numerator, applycount, status;
			String userName, tcaName, introduction;
			tcaId = rs.getInt("tca_id");
			userName = rs.getString("user_name");
			tcaName = rs.getString("tca_name");
			introduction = rs.getString("introduction");
			numerator = rs.getInt("numerator");
			applycount = rs.getInt("applycount");
			status = rs.getInt("status");
			Tca tca = new Tca(tcaId, tcaName, userName, introduction, status, numerator, applycount);
			return tca;
		}
	};

	public void create(Tca tca) {
		String create = "INSERT cucgp.`tca` (user_name, tca_name, introduction, requirement, numerator, applycount, status)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(create, tca.getTeacherName(), tca.getTcaName(),
				tca.getIntroduction(), tca.getRequirement(),
				tca.getNumerator(), tca.getApplycount(), tca.getStatus());
		String LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
		Integer id = jdbcTemplate.queryForObject(LAST_INSERT_ID, Integer.class);
		tca.setTcaId(id);
	}

	public List<Tca> readAll() {
	  List<Tca> tcas = new ArrayList<Tca>();
	  String ALL = "SELECT tca_id, tca_name, user_name, introduction, numerator, applycount, status FROM cucgp.`tca`";
	  tcas = jdbcTemplate.query(ALL, rse);
	  return tcas;
	}

	public void changeStatus(Tca tca) {
		 String ALL = "UPDATE cucgp.`tca` SET tca_name = (?), introduction = (?), numerator = (?), status = (?) WHERE tca_id = (?);";
		 jdbcTemplate.update(ALL, tca.getTcaName(), tca.getIntroduction(), tca.getNumerator(), tca.getStatus(), tca.getTcaId());
	}

	public List<Tca> readByStatus(Integer passAdmin) {
		List<Tca> tcas = new ArrayList<Tca>();
		String ALL = "SELECT tca_id, tca_name, user_name, introduction, numerator, applycount, status FROM cucgp.`tca` WHERE status = (?);";
		tcas = jdbcTemplate.query(ALL, new Object[]{passAdmin}, rse);
		return tcas;
	}

	public Tca readById(Integer tcaId) {
		Tca tca = null;
		String ALL = "SELECT tca_id, tca_name, user_name, introduction, numerator, applycount, status FROM cucgp.`tca` WHERE tca_id = (?);";
		tca = jdbcTemplate.queryForObject(ALL, new Object[]{tcaId}, rse);
		return tca;
	}

}
