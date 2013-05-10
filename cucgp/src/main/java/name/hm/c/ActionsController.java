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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/actions"})
public class ActionsController
{
	@Autowired
	public AuthorizationService authorizationService;

	@RequestMapping(value = {"/{roleId}/{actionUrl}"}, method = {RequestMethod.GET} )
	public String dispatcher(@PathVariable Integer roleId, @PathVariable String actionUrl, HttpServletRequest req) throws ServiceException {
		authorizationService.startService();
		User user = (User) req.getSession().getAttribute("user");
		boolean ret = authorizationService.userIsRole(user.getUserId(), roleId);
		if (ret == false) {
			authorizationService.endService();
			return "error";
		}
		authorizationService.endService();
		return actionUrl;
	}
	
}
