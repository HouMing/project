package me.hm.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.hm.m.Workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class WorkflowDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	protected RowMapper<Workflow> rse = new RowMapper<Workflow>() {
		@Override
		public Workflow mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer workflowId;
			String workflowName;
			workflowName = rs.getString("workflow_name");
			workflowId = rs.getInt("workflow_status");
			Workflow workflow = new Workflow(workflowName, workflowId);
			return workflow;
		}
	};

	public Workflow read(String workflowName) {
		String SELECTBYNAME = " SELECT workflow_name, workflow_id FROM cucgp.`workflow` WHERE workflow_name = (?);";
		Workflow workflow = jdbcTemplate.queryForObject(SELECTBYNAME, new Object[] { workflowName }, rse);
		return workflow;
	}

	public List<Workflow> getAll() {
		String SELECT_ALL = "SELECT workflow_name, workflow_status FROM cucgp.`workflow`;";
		List<Workflow> workflows = new ArrayList<Workflow>();
		workflows = jdbcTemplate.query(SELECT_ALL, rse);
		return workflows;
	}

	public void create(Workflow workflow) {
		String CREATE = "INSERT INTO cucgp.`workflow` (workflow_name, workflow_status) VALUES ( ?, ?);";
		jdbcTemplate.update(CREATE, workflow.getWorkflowName(), workflow.getWorkflowStatus());
	}

	public Integer write(Workflow workflow) {
		String UPDATE = "UPDATE cucgp.`workflow` SET workflow_status = (?) WHERE workflow_name = (?);";
		return jdbcTemplate.update(UPDATE, workflow.getWorkflowStatus(), workflow.getWorkflowName());
	}

	public Integer delete(Workflow user) {
		String DELETE = "DELETE FROM cucgp.`workflow` WHERE workflow_name = (?);";
		return jdbcTemplate.update(DELETE, user.getWorkflowName());
	}

	public void sync(Workflow workflow) {
		String UPDATE = "UPDATE cucgp.`workflow` SET workflow_status = (?) WHERE workflow_name = (?);";
		jdbcTemplate.update(UPDATE, workflow.getWorkflowName());
	}
}
