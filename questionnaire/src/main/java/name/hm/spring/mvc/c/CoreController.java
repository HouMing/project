package name.hm.spring.mvc.c;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.hm.spring.data.jpa.Answer;
import name.hm.spring.data.jpa.Person;
import name.hm.spring.data.jpa.Question;
import name.hm.spring.data.jpa.Statistic;
import name.hm.spring.data.jpa.Survey;
import name.hm.spring.mvc.m.QuestionDAO;
import name.hm.spring.mvc.s.CoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = { "" })
public class CoreController
{
	@Autowired
	CoreService service;

	@RequestMapping(value = { "index" }, method = { RequestMethod.GET })
	public String index(HttpServletRequest req)
	{
		Person user = (Person) req.getSession().getAttribute("user");
		int right = 0;
		if (user != null) {
   		right = user.getRight();
		}
		HashMap<String, String> actions = new HashMap<String, String>();
		switch (right) {
		case 3:
			actions.put("管理问卷", "manage");
			actions.put("退出", "logout");
			break;
		case 2:
			actions.put("填写问卷", "join");
			actions.put("退出", "logout");
			break;
		case 1:
			actions.put("出问题了", "");
			break;
		case 0:
			actions.put("注册", "register");
			break;
		}
		req.setAttribute("actions", actions);
		return "index";
	}

	@RequestMapping(value = { "login" }, method = { RequestMethod.GET })
	public String loginGET()
	{
		return "login";
	}

	@RequestMapping(value = { "register" }, method = { RequestMethod.GET })
	public String register()
	{
		return "register";
	}
	
	@RequestMapping(value = { "register" }, method = { RequestMethod.POST })
	public String register(HttpServletRequest req)
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		service.registerUser(username, password);
		return "index";
	}
	
	@RequestMapping(value = { "login" }, method = { RequestMethod.POST })
	public String loginPOST(HttpServletRequest req, HttpServletResponse resp,
			Model model)
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Person user = service.getUser(username, password);
		if (user == null) {
			return "redirect:index";
		} else {
			req.getSession().setAttribute("user", user);
			return "redirect:"+index(req);
		}
	}

	@RequestMapping(value = { "logout" }, method = { RequestMethod.GET })
	public String logout(HttpServletRequest req)
	{
		req.getSession().setAttribute("user", null);
		req.getSession().invalidate();
		return "index";
	}
	
	@RequestMapping(value = { "manage" }, method = { RequestMethod.GET })
	public String manage(HttpServletRequest req, HttpServletResponse resp, Model model)
	{
		ArrayList<Survey> list = service.allHeavySurvey();
		req.setAttribute("surveys", list);
		return "manage";
	}
	
	@RequestMapping(value = { "edit" }, method = { RequestMethod.POST })
	public String edit(HttpServletRequest req, HttpServletResponse resp, Model model)
	{
		String oid = req.getParameter("oid");
		String name = req.getParameter("name");
		ArrayList<Question> questions = service.someQuestionsOfSurvey(oid);
		req.setAttribute("survey_oid", oid);
		req.setAttribute("questions", questions);
		return "edit";
	}
	
	@RequestMapping(value = { "dele" }, method = { RequestMethod.POST })
	public String dele(HttpServletRequest req, HttpServletResponse resp, Model model)
	{
		String oid = req.getParameter("oid");
		String name = req.getParameter("name");
		String result = service.deleteSurvey(oid);
		req.setAttribute("msg", result);
		return "dele";
	}
	
	@RequestMapping(value = { "sttc" }, method = { RequestMethod.POST })
	public String sttc(HttpServletRequest req, HttpServletResponse resp, Model model)
	{
		String oid = req.getParameter("oid");
		String name = req.getParameter("name");
		ArrayList<Question> questions = service.someQuestionsOfSurvey(oid);
		HashMap<Integer, Statistic> maps = new HashMap<Integer, Statistic>();
		for (Question q : questions) {
			Statistic sttc = service.getStatistic(q);
			maps.put(q.getOid(), sttc);
		}
		req.setAttribute("questions", questions);
		req.setAttribute("sttc", maps);
		return "sttc";
	}
	
	@RequestMapping(value = { "deleteQuestion" }, method = { RequestMethod.POST })
	public String deleteQuestion(HttpServletRequest req, HttpServletResponse resp, Model model)
	{
		String oid = req.getParameter("oid");
		String msg = service.deleteQuestion(oid);
		req.setAttribute("msg", msg);
		return "msg";
	}
	
	@RequestMapping(value = { "updateQuestion" }, method = { RequestMethod.POST })
	public String updateQuestion(HttpServletRequest req, HttpServletResponse resp, Model model)
	{
		String oid = req.getParameter("oid");
		String description = req.getParameter("description");
		String itemA = req.getParameter("itemA");
		String itemB = req.getParameter("itemB");
		String itemC = req.getParameter("itemC");
		String itemD = req.getParameter("itemD");
		String survey_oid = req.getParameter("survey_oid");
		String msg;
		if (oid.equals("-1")) {
			msg = service.createQuestion(survey_oid, description, itemA, itemB, itemC, itemD);
		}else {
		  msg = service.updateQuestion(oid, description, itemA, itemB, itemC, itemD);
		}
		req.setAttribute("msg", msg);
		return "msg";
	}
	
	@RequestMapping(value = { "createSurvey" }, method = { RequestMethod.POST })
	public String createSurvey(HttpServletRequest req, HttpServletResponse resp, Model model)
	{
		String name = req.getParameter("name");
		String msg;
		msg = service.createSurvey(name);
		HashMap<String, String> ret = new HashMap<String, String>();
		ret.put("result", msg);
		req.setAttribute("msg", JSON.toJSONString(ret));
		return "msg";
	}
	
	@RequestMapping(value = { "join" }, method = { RequestMethod.GET })
	public String joinGET(HttpServletRequest req, HttpServletResponse resp)
	{
		ArrayList<Survey> list = service.allHeavySurvey();
		req.setAttribute("surveys", list);
		return "joinSurvey";
	}
	
	@RequestMapping(value = { "join" }, method = { RequestMethod.POST })
	public String joinPOST(HttpServletRequest req, HttpServletResponse resp)
	{
		String oid = req.getParameter("oid");
		ArrayList<Question> questions = service.someQuestionsOfSurvey(oid);
		Person user = (Person) req.getSession().getAttribute("user");
		req.setAttribute("user_oid", user.getOid());
		req.setAttribute("survey_oid", oid);
		req.setAttribute("questions", questions);
		return "joinASurvey";
	}
	
	@RequestMapping(value = { "addAnswer" }, method = { RequestMethod.POST })
	public String addAnswer(HttpServletRequest req, HttpServletResponse resp)
	{
		String user_oid = req.getParameter("user_oid");
		String survey_oid = req.getParameter("survey_oid");
		ArrayList<Question> questions = service.someQuestionsOfSurvey(survey_oid);
		for(Question q : questions) {
			Integer q_oid = q.getOid();
			String item = req.getParameter(q_oid.toString());
			Answer a = new Answer(Integer.parseInt(user_oid), q_oid, Integer.parseInt(survey_oid), item);
			service.updateAnswer(a);
		}
		req.setAttribute("questions", questions);
		return index(req);
	}
	
	
}
