package me.hm.c;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.hm.m.Action;
import me.hm.m.Role;
import me.hm.m.User;
import me.hm.s.AuthorizationService;
import me.hm.s.e.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value={"/loader"})
public class PageLoaderController
{
	@Autowired
	public AuthorizationService userRightService;

	@RequestMapping(value = { "/main" }, method = { RequestMethod.GET })
	public String loadPage(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServiceException
	{
		User user = (User) req.getSession().getAttribute("user");
		ArrayList<Role> roles = userRightService.lsRoles(user);
		ArrayList<Action> actions = userRightService.lsActions(user);
		model.addAttribute("roles", roles);
		model.addAttribute("actions", actions);
		return "loader/main";
	}
	
	@RequestMapping(value = {"/actions"}, method = { RequestMethod.GET} )
	public String jsonActions(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServiceException
	{
		User user = (User) req.getSession().getAttribute("user");
		ArrayList<Role> roles = userRightService.lsRoles(user);
		ArrayList<Action> actions = userRightService.lsActions(user);
		model.addAttribute("roles", roles);
		model.addAttribute("actions", actions);
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
