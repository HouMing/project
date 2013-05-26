package me.hm.c;

//{{{
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import me.hm.m.Action;
import me.hm.m.User;
import me.hm.s.AuthorizationService;
import me.hm.s.UsersService;
import me.hm.s.e.AuthorizationException;
import me.hm.s.e.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//}}}

@Controller
@RequestMapping(value = "/users")
public class UsersManagerController {
	@Autowired
	public AuthorizationService authorizationService;
	@Autowired
	public UsersService usersManagerService;

	// TODO 加入权限控制!
	@RequestMapping(value = { "/reads" }, method = { RequestMethod.GET })
	public @ResponseBody Object reads(HttpServletRequest req, Model model)
    throws ServerException {
		User user = (User) req.getSession().getAttribute("user");
		Action action = null;
		List<User> users = usersManagerService.readUsers();
		try {
			authorizationService.accessible(user, action);
			model.addAttribute("success", true);
			model.addAttribute("users", users);
			return model;
		} catch (AuthorizationException e) {
			model.addAttribute("success", true);
			model.addAttribute("users", users);
			return model;
		}
	}
	
	@RequestMapping(value = { "/creates" }, method = { RequestMethod.POST })
	public @ResponseBody Object creates(HttpServletRequest req, Model model, @RequestBody User users)
		    throws ServerException {
				User user = (User) req.getSession().getAttribute("user");
				Action action = null;
  			    usersManagerService.insertUser(users);
				try {
					authorizationService.accessible(user, action);
					model.addAttribute("success", true);
					model.addAttribute("result", true);
					return model;
				} catch (AuthorizationException e) {
					model.addAttribute("success", true);
					model.addAttribute("result", true);
					return model;
				}
			}
	
	@RequestMapping(value = { "/updates" }, method = { RequestMethod.POST })
	public @ResponseBody Object updates(HttpServletRequest req, Model model, @RequestBody User users)
		    throws ServerException {
				User user = (User) req.getSession().getAttribute("user");
				Action action = null;
				usersManagerService.updateUser(users);
				try {
					authorizationService.accessible(user, action);
					model.addAttribute("success", true);
					model.addAttribute("result", true);
					return model;
				} catch (AuthorizationException e) {
					model.addAttribute("success", true);
					model.addAttribute("result", true);
					return model;
				}
			}
	
	@RequestMapping(value = { "/deletes" }, method = { RequestMethod.DELETE })
	public @ResponseBody Object deletes(HttpServletRequest req, Model model, @RequestBody User users)
		    throws ServerException {
				User user = (User) req.getSession().getAttribute("user");
				Action action = null;
				usersManagerService.deleteUser(users);
				try {
					authorizationService.accessible(user, action);
					model.addAttribute("success", true);
					model.addAttribute("result", true);
					return model;
				} catch (AuthorizationException e) {
					model.addAttribute("success", true);
					model.addAttribute("result", true);
					return model;
				}
			}
}
