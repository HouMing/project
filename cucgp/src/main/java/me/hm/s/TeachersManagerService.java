package me.hm.s;

import java.util.ArrayList;
import java.util.List;

import me.hm.jpa.StudentDao;
import me.hm.m.Student;
import me.hm.m.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class TeachersManagerService {

	@Autowired
	StudentDao studentDao;
	
	public List<Student> readStudents() {
		List<Student> students = new ArrayList<Student>();
		students.addAll(studentDao.readAll());
		return students;
	}

	public void insertStudent(Student student) {
		studentDao.create(student);
	}

	@Deprecated
	public void updateStudent(User user) {
		
	}
	
}