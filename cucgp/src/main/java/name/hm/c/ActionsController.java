package name.hm.c;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.hm.jpa.RoleMapper;
import name.hm.jpa.UserMapper;
import name.hm.m.Action;
import name.hm.m.Group;
import name.hm.m.User;
import name.hm.m.Role;
import name.hm.s.UserRightService;
import name.hm.s.e.LoginException;
import name.hm.s.e.RightException;
import name.hm.s.e.ServiceException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActionsController extends BaseController
{
	@Autowired
	public UserRightService userRightService;

	@RequestMapping(method = { RequestMethod.GET }, value = { "/editStudent" })
	public String editStudent(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServiceException
	{
		HttpSession se = req.getSession();
		User user = (User) se.getAttribute("user");
		userRightService.actionCheck(user,"editStudent");
		return "student/ManageStudent";
	}

	static int i = 0;
	@RequestMapping(method = { RequestMethod.POST }, value = { "/getStudents" })
	public String getStudents(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServiceException
	{
		++i;
		String userName = req.getParameter("userName");
		System.out.println("req times:" + i);
		System.out.println(userName);
		return "debug";
	}
	
	@RequestMapping(method = { RequestMethod.GET }, value = { "/getStudent" })
	public String getStudent(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServiceException
	{
		++i;
		String userName = req.getParameter("userName");
		System.out.println("req times:" + i);
		System.out.println(userName);
		return "student/StudentInfoForm";
	}
}
