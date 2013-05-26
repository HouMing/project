package me.hm.c;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import me.hm.m.Action;
import me.hm.m.Role;
import me.hm.m.User;
import me.hm.s.AuthorizationService;
import me.hm.s.e.AuthorizationException;
import me.hm.s.e.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//{{{
//}}}

@Controller
@RequestMapping(value = {"/actions"})
public class ActionsApi
{
	@Autowired
	public AuthorizationService authorizationService;

	@RequestMapping(value = {"/reads"}, method = {RequestMethod.GET})
	public @ResponseBody Object lsActions (HttpServletRequest req, Model model) throws AuthorizationException {
		User user = (User) req.getSession().getAttribute("user");
		ArrayList<Action> lsActions;
		lsActions = authorizationService.lsActions(user);
		model.addAttribute("actions", lsActions);
        model.addAttribute("success", "true");
		return model;
	}
	
	@RequestMapping(value = {"/{roleId}/{actionId}"}, method = {RequestMethod.GET} )
	public String dispatcher(@PathVariable Integer roleId, @PathVariable Integer actionId, HttpServletRequest req) throws ServiceException {
		User user = (User) req.getSession().getAttribute("user");
		ArrayList<Role> roles = authorizationService.lsRoles(user);
		boolean isRole = false;
		for (Role role : roles) {
			if (role.getRoleId() == roleId) {
				isRole = true;
			}
		}
		if (isRole == false) {
			throw new AuthorizationException(AuthorizationException.NoPermission);
		}
		ArrayList<Action> actions = authorizationService.lsActions(user);
		boolean isAccessAction = false;
		for(Action ac : actions) {
			if (ac.getActionId() == actionId ) {
				isAccessAction = true;
			}
		}
		if (isAccessAction == false) {
			throw new AuthorizationException(AuthorizationException.NoPermission);
		}
		return "debug";
	}
	
}
