package name.hm.c.login;

import name.hm.orm.ActionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Login
{
	
	@Autowired
	ActionMapper actionMapper = null;
	
	@RequestMapping(method = {RequestMethod.GET}, value = {"/first"})
	public String doGet(){
		return "first";
	}
	
	@RequestMapping(method = {RequestMethod.POST}, value = {"loggin"})
	public String doPost(){
		return "";
	}
}
