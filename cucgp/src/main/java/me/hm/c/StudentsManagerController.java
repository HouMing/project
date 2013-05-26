package me.hm.c;

//{{{
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import me.hm.m.Action;
import me.hm.m.Student;
import me.hm.m.User;
import me.hm.s.AuthorizationService;
import me.hm.s.StudentService;
import me.hm.s.e.AuthorizationException;
import me.hm.s.e.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.IOUtils;
import com.alibaba.fastjson.JSON;

//}}}

@Controller
@RequestMapping(value = "/students")
public class StudentsManagerController {
	@Autowired
	public AuthorizationService authorizationService;
	@Autowired
	public StudentService studentsManagerService;

	// TODO 加入权限控制!
	@RequestMapping(value = { "/reads" }, method = { RequestMethod.GET })
	public @ResponseBody
	Object reads(HttpServletRequest req, Model model) throws ServerException {
		User user = (User) req.getSession().getAttribute("user");
		Action action = null;
		List<Student> students = studentsManagerService.readStudents();
		try {
			authorizationService.accessible(user, action);
			model.addAttribute("success", true);
			model.addAttribute("students", students);
			return model;
		} catch (AuthorizationException e) {
			model.addAttribute("success", true);
			model.addAttribute("students", students);
			return model;
		}
	}

	@RequestMapping(value = { "/creates" }, method = { RequestMethod.POST })
	public @ResponseBody Object creates(HttpServletRequest req, Model model) throws ServerException {
		try {
			ServletInputStream inputStream = req.getInputStream();
			String str = IOUtils.read(inputStream);
			Student t = new Student(JSON.parseObject(str));
			studentsManagerService.insertStudent(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("success", true);
		model.addAttribute("result", true);
		return model;
	}

	@RequestMapping(value = { "/updates" }, method = { RequestMethod.POST })
	public @ResponseBody
	Object updates(HttpServletRequest req, Model model) throws ServerException {
		try {
			ServletInputStream inputStream = req.getInputStream();
			String str = IOUtils.read(inputStream);
			Student t = new Student(JSON.parseObject(str));
			studentsManagerService.updateStudent(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("success", true);
		model.addAttribute("result", true);
		return model;
	}
	
	@RequestMapping(value = { "/deletes" }, method = { RequestMethod.POST })
	public @ResponseBody Object deletes(HttpServletRequest req, Model model) throws ServerException {
		try {
			ServletInputStream inputStream = req.getInputStream();
			String str = IOUtils.read(inputStream);
			Student t = new Student(JSON.parseObject(str));
			studentsManagerService.deleteStudent(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("success", true);
		model.addAttribute("result", true);
		return model;
	}
}
