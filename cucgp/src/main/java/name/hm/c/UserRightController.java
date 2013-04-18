package name.hm.c;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.alibaba.fastjson.JSON;

@Controller
public class UserRightController extends BaseController
{
	@Autowired
	public UserRightService userRightService;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "/logout.ac" })
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		req.getSession().removeAttribute("user");
		req.getSession().removeAttribute("roles");
		req.getSession().removeAttribute("actions");
		req.getSession().invalidate();
		resp.sendRedirect("login.ac");
	}
	
	@RequestMapping(method = { RequestMethod.GET }, value = { "/","/login.ac" })
	public String loginGet(HttpServletRequest req, HttpServletResponse resp)
	{
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			return "login";
		} else {
			return "com/main";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, value = { "/login.ac" })
	public String loginPost(HttpServletRequest req, HttpServletResponse resp, Model model) throws RightException, LoginException, ServiceException, IOException
	{
		String userName, password;
		User user;
		Group group;
		ArrayList<Role> roles = new ArrayList<Role>();
		ArrayList<Action> actions = new ArrayList<Action>();
		userName = req.getParameter("userName");
		password = req.getParameter("password");
		
		userRightService.startService();
		user = userRightService.loadUser(userName, password);
		group = userRightService.loadGroup(user);
		roles = userRightService.loadRoles(user);
		actions = userRightService.loadActions(user);
		
		userRightService.endService();
		
		req.getSession().setAttribute("user", user);
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("result", "yes");
		model.addAttribute("json",JSON.toJSON(map));
		
		return "json";
	}

	@RequestMapping(method = { RequestMethod.DELETE, RequestMethod.HEAD,
			RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.PUT,
			RequestMethod.TRACE }, value = { "/login.ac", "/logout.ac" })
	public String doOthers()
	{
		return "error/illegal";
	}
	
}
