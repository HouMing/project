package me.hm.s;

import java.util.ArrayList;
import java.util.List;

import me.hm.jpa.WorkflowDao;
import me.hm.m.Workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class WorkflowsService {

	@Autowired
	WorkflowDao roleDao;

	public List<Workflow> readWorkflows() {
		List<Workflow> roles = new ArrayList<Workflow>();
		roles.addAll(roleDao.getAll());
		return roles;
	}

	public void insertWorkflow(Workflow workflow) {
	  roleDao.create(workflow);
	}

	public void updateWorkflow(Workflow workflow) {
       roleDao.write(workflow);
	}
	
	public void syncWorkflow(Workflow workflow) {
		roleDao.sync(workflow);
	}

	public void deleteWorkflow(Workflow workflow) {
       roleDao.delete(workflow);		
	}

}