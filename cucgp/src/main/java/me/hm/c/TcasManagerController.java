package me.hm.c;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import me.hm.m.Tca;
import me.hm.m.User;
import me.hm.s.TcaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.util.IOUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//{{{
//}}}

@Controller
@RequestMapping(value = { "/tcas" })
public class TcasManagerController {
	@Autowired
	TcaService tcaService;

	@RequestMapping(value = { "/creates" }, method = { RequestMethod.POST })
	public Object creates(HttpServletRequest req, Model model,
			@RequestParam("tcaName") String tcaName,
			@RequestParam("numerator") Integer numerator,
			@RequestParam("introduction") String introduction) {
		HttpSession se = req.getSession();
		User user = (User) se.getAttribute("user");
		Tca tca = new Tca(user.getUserName(), tcaName, introduction, 0,	numerator, 0);
		tcaService.createTca(tca);
		return model;
	}

	@RequestMapping(value = { "/reads" }, method = { RequestMethod.GET })
	public Object reads(HttpServletRequest req, Model model) {
		List<Tca> tcas = tcaService.readAll();
		model.addAttribute("success", true);
		model.addAttribute("tcas", tcas);
		return model;
	}

	@RequestMapping(value = { "/updates" }, method = { RequestMethod.POST })
	public Object updates(HttpServletRequest req, Model model) {
		try {
			ServletInputStream inputStream = req.getInputStream();
			String str = IOUtils.read(inputStream);
			JSONObject jsb = JSON.parseObject(str);
			Tca tca = new Tca(jsb);
			tcaService.adminPassTca(tca);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("success", true);
		return model;
	}
}
