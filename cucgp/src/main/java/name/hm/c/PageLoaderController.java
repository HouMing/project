package name.hm.c;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.hm.jpa.RoleMapper;
import name.hm.jpa.UserMapper;
import name.hm.m.Action;
import name.hm.m.Group;
import name.hm.m.User;
import name.hm.m.Role;
import name.hm.s.AuthorizationService;
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
@RequestMapping(value={"/loader"})
public class PageLoaderController
{
	@Autowired
	public AuthorizationService userRightService;

	@RequestMapping(value = { "/main" }, method = { RequestMethod.GET })
	public String loadPage(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServiceException
	{
		userRightService.startService();
		User user = (User) req.getSession().getAttribute("user");
		ArrayList<Role> roles = userRightService.loadRoles(user);
		ArrayList<Action> actions = userRightService.loadActions(user);
		model.addAttribute("roles", roles);
		model.addAttribute("actions", actions);
		userRightService.endService();
		return "loader/main";
	}
	
	@RequestMapping(value = {"/actions"}, method = { RequestMethod.GET} )
	public String jsonActions(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServiceException
	{
		userRightService.startService();
		User user = (User) req.getSession().getAttribute("user");
		ArrayList<Role> roles = userRightService.loadRoles(user);
		ArrayList<Action> actions = userRightService.loadActions(user);
		model.addAttribute("roles", roles);
		model.addAttribute("actions", actions);
		userRightService.endService();
		return "json";
	}

	@RequestMapping(method = { RequestMethod.DELETE, RequestMethod.HEAD,
			RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.PUT,
			RequestMethod.TRACE } )
	public void doOthers() throws ServiceException
	{
		throw new ServiceException("不支持该访问");
	}
	
}
