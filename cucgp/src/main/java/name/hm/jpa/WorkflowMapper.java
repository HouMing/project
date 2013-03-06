package name.hm.jpa;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import name.hm.pojo.Workflow;

public interface WorkflowMapper extends Mapper
{

	final String SELECT_BY_WORKFLOWID = "SELECT * FROM `workflow` WHERE workflow_id = #{param1}";
	final String SELECT_BY_WORKFLOWNAME = "SELECT * FROM `workflow` WHERE workflow_name = #{param1}";
	final String SELECT_BY_WORKFLOWSTATUS = "SELECT * FROM `workflow` WHERE workflow_status = #{param1}";
	final String INSERT_WORKFLOW = "INSERT INTO `workflow` (workflow_id, workflow_name, workflow_status) VALUES " +
			                    "(#{workflowId}, #{workflowName}, #{workflowStatus})";
	final String UPDATE = "UPDATE `workflow` " +
			              "SET workflow_name = #{workflowName}, workflow_status = #{workflowStatus} " +
			              "WHERE workflow_id = #{workflowId}";
	final String DELETE = "DELETE FROM `workflow` WHERE workflow_id = #{workflowId}";
	
	@Select(SELECT_BY_WORKFLOWID)
	@Results(value = {
			@Result(property="workflowId",column="workflow_id"),
			@Result(property="workflowName",column="workflow_name"),
			@Result(property="workflowStatus",column="workflow_status")
	})
	Workflow selectByWorkflowId(Integer workflowId);

	@Select(SELECT_BY_WORKFLOWNAME)
	@Results(value = {
			@Result(property="workflowId",column="workflow_id"),
			@Result(property="workflowName",column="workflow_name"),
			@Result(property="workflowStatus",column="workflow_status")
	})
	Workflow selectByWorkflowName(String workflowName);

	@Select(SELECT_BY_WORKFLOWSTATUS)
	@Results(value = {
			@Result(property="workflowId",column="workflow_id"),
			@Result(property="workflowName",column="workflow_name"),
			@Result(property="workflowStatus",column="workflow_status")
	})
	List<Workflow> selectByWorkflowStatus(Workflow.STATUS str);

	@Insert(INSERT_WORKFLOW)
	@SelectKey(statement = "SELECT MAX(workflow_id) AS workflow_id FROM cucgp.`workflow`", keyProperty = "workflowId", before = false, resultType = Integer.class)
	@Options(useGeneratedKeys = true, keyProperty = "workflowId", keyColumn = "workflow_id")
	Integer insert(Workflow workflow);
	
	@Update(UPDATE)
	Integer update(Workflow workflow);

	@Delete(DELETE)
	Integer delete(Workflow workflow);

}
