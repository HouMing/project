package me.hm.s;

import java.util.ArrayList;
import java.util.List;

import me.hm.jpa.StudentDao;
import me.hm.jpa.UserDao;
import me.hm.m.Student;
import me.hm.m.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
public class StudentService {
	@Autowired
	StudentDao studentDao;
	@Autowired
	UserDao userDao;

	public List<Student> readStudents() {
		List<Student> students = new ArrayList<Student>();
		students.addAll(studentDao.readAll());
		return students;
	}

	@Transactional
	public void insertStudent(Student student) {
		studentDao.create(student);
	}

	@Transactional
	public void updateStudent(Student student) {
	   studentDao.write(student);
	}

	@Transactional
	public void deleteStudent(Student t) {
	   studentDao.delete(t);
	}

	public Student readByUser(User user) {
		return studentDao.readByName(user.getUserName());
	}
	
}