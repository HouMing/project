package me.hm.c;

//{{{
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import me.hm.m.Teacher;
import me.hm.s.AuthorizationService;
import me.hm.s.TeachersService;
import me.hm.s.e.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//}}}

@Controller
@RequestMapping(value = "/teachers")
public class TeachersManagerController {
	@Autowired
	public AuthorizationService authorizationService;
	@Autowired
	public TeachersService teachersManagerService;

	// TODO 加入权限控制!
	@RequestMapping(value = { "/reads" }, method = { RequestMethod.GET })
	public @ResponseBody Object reads(HttpServletRequest req, Model model)
    throws ServerException {
		List<Teacher> teachers = teachersManagerService.readTeachers();
		model.addAttribute("success", true);
		model.addAttribute("students", teachers);
		return model;
	}
}
