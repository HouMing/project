package me.hm.s;

import java.util.List;

import me.hm.jpa.TcaDao;
import me.hm.m.Tca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class TcaService {

	public final Integer WaitAdmin = 0;
	public final Integer PassAdmin = 1;
	public final Integer NoPassAdmin = 2;
	
	@Autowired
	TcaDao tcaDao;

	@Transactional
	public void createTca(Tca tca) {
		tcaDao.create(tca);
	}

	public List<Tca> readAll() {
		return tcaDao.readAll();
	}

	public void adminPassTca(Tca tca) {
		tcaDao.changeStatus(tca);
	}

	public List<Tca> applyTcas() {
		List<Tca> ret = tcaDao.readByStatus(PassAdmin);
		return ret;
	}

	public Tca readById(Integer tcaId) {
		Tca ret = tcaDao.readById(tcaId);
		return ret;
	}

}