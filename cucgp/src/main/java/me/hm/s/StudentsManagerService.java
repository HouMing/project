package me.hm.s;

import java.util.ArrayList;
import java.util.List;

import me.hm.jpa.StudentDao;
import me.hm.jpa.UserDao;
import me.hm.m.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class StudentsManagerService {

	@Autowired
	StudentDao studentDao;
	@Autowired
	UserDao userDao;
	
	public List<Student> readStudents() {
		List<Student> students = new ArrayList<Student>();
		students.addAll(studentDao.readAll());
		return students;
	}

	public void insertStudent(Student student) {
		studentDao.create(student);
	}

	public void updateStudent(Student student) {
	   studentDao.write(student);
	}

	public void deleteStudent(Student t) {
	   studentDao.delete(t);
	}
	
}