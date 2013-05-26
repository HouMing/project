package me.hm.s;

import java.util.ArrayList;
import java.util.List;

import me.hm.jpa.TeacherDao;
import me.hm.m.Teacher;
import me.hm.m.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//{{{

//}}}

@Service
@Transactional
public class TeachersService {

	@Autowired
	TeacherDao teacherDao;
	
	public List<Teacher> readTeachers() {
		List<Teacher> students = new ArrayList<Teacher>();
		students.addAll(teacherDao.readAll());
		return students;
	}

	public void insertTeacher(Teacher teacher) {
		teacherDao.create(teacher);
	}

	@Deprecated
	public void updateStudent(User user) {
		
	}
	
}