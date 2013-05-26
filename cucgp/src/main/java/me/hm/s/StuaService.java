package me.hm.s;

import me.hm.jpa.StuaDao;
import me.hm.m.Stua;
import me.hm.m.Student;
import me.hm.m.Tca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuaService {

	@Autowired
	StuaDao stuaDao;

	@Transactional
	public Stua submitStua(Student stu, Tca tca) {
		Stua stua = new Stua(tca.getTcaId(), 0, tca.getTeacherName(), stu.getUserName());
		stuaDao.create(stua);
	    return stua;
	}
	
	
}
