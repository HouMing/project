package me.hm.s;

import java.util.List;

import me.hm.jpa.ActionDao;
import me.hm.m.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class ActionsManagerService {

	@Autowired
	ActionDao actionDao;

	public void insertAction(Action action) {
		actionDao.create(action);
	}

	public void syncAction(Action action) {
        actionDao.read(action);		
	}

	public void updateAction(Action action) {
		actionDao.update(action);
	}

	public List<Action> readActions() {
		return actionDao.readAll();
	}

	public void deleteAction(Action action) {
actionDao.delete(action);		
	}
	
}