package me.hm.c;

//{{{
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.hm.m.User;
import me.hm.s.AuthorizationService;
import me.hm.s.e.AuthorizationException;
import me.hm.s.e.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//}}}

@Controller
@RequestMapping(value = "/auth")
public class AuthorizationController {
	@Autowired
	public AuthorizationService authorizationService;

	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST })
	public @ResponseBody
	Object login(@RequestParam("userName") String username,
			@RequestParam("password") String password, HttpServletRequest req,
			Model model) throws ServerException {
		try {
			User user = null;
			user = authorizationService.login(username, password);
			req.getSession().setAttribute("user", user);
			model.addAttribute("status", "0");
			model.addAttribute("msg", "登录成功");
			return model;
		} catch (AuthorizationException e) {
			model.addAttribute("status", "-1");
			model.addAttribute("msg", e.getMessage());
			return model;
		}
	}
	

	@RequestMapping(value = { "/logout" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public String logout(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		req.getSession().removeAttribute("user");
		req.getSession().invalidate();
		return "redirect:/";
	}
}
