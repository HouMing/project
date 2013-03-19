package name.hm.c.login;

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

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserRightController extends BaseController
{
  final String UserInValid = "该用户不存在";
  final String PasswordWrong = "用户名或密码错误";

	@RequestMapping(method = { RequestMethod.GET }, value = { "/" })
	public String index(HttpServletRequest req, HttpServletResponse resp)
	{
		return "login";
	}

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = {"/logout.ac"})
  public String logout (HttpServletRequest req, HttpServletResponse resp)
	{
		return "login";
	}
	
	@RequestMapping(method = { RequestMethod.GET }, value = { "/login.ac" })
	public String loginGet(HttpServletRequest req, HttpServletResponse resp)
	{
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			return "login";
		} else {
			return "logout";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, value = { "/login.ac" })
	public String loginPost(HttpServletRequest req, HttpServletResponse resp)
	{
		//每次都新建这么多的变量...他会不会很卡啊？
		String userName,password;
		User user;
		Group group;
		ArrayList<Integer> rolesId;
		ArrayList<Role> roles = new ArrayList<Role>();
		ArrayList<Integer> actionsId;
		ArrayList<Action> actions;
		
		openSession();
		userName = req.getParameter("userName");
		password = req.getParameter("password");
		user = userMapper.selectByUserName(userName);
		se.commit();
		if ((user == null )|| (user.getValid() == User.ConstInvalid)) {
			req.setAttribute("info", UserInValid);
			return "error/user";
		}
		if (user.getPassword().equals(password)) {
			req.getSession().setAttribute("user", user);
			group = groupMapper.selectByGroupId(user.getGroupId());
			se.commit();
			rolesId = group.getRoles();
			for (Integer roleId: rolesId) {
				Role tmpRole = roleMapper.selectByRoleId(roleId);
				roles.add(tmpRole);
			}
			for(Integer roleId : rolesId){
				Action action = actionMapper.selectByRoleId(roleId);
				actions.add(action);
			}
			try {
				resp.getWriter().append(user + "\n" + group + "\n" + roles);
				resp.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			req.setAttribute("user", user);
			req.setAttribute("roles", roles);
			req.setAttribute("actions", actions);
			return "com/main";
		} else {
			req.setAttribute("info", PasswordWrong);
			return "error/user";
		}
	}

	@RequestMapping(method = { RequestMethod.DELETE, RequestMethod.HEAD,
			RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.PUT,
			RequestMethod.TRACE }, value = { "/login.ac", "/logout.ac" })
	public String doOthers()
	{
		return "error/illegal";
	}
}
