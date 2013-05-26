package me.hm.c;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import me.hm.m.Stua;
import me.hm.m.Student;
import me.hm.m.Tca;
import me.hm.m.User;
import me.hm.s.StuaService;
import me.hm.s.StudentService;
import me.hm.s.TcaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.util.IOUtils;

@Controller
public class ServerApi {

	@Autowired
	TcaService tcaService;
	@Autowired
	StuaService stuaService;
	@Autowired
	StudentService studentService;

	@RequestMapping(value = { "/students/readTcas" }, method = { RequestMethod.GET })
	public Object applyTca(HttpServletRequest req, Model model) {
		List<Tca> list = tcaService.applyTcas();
		model.addAttribute("tcas", list);
		model.addAttribute("success", true);
		return model;
	}

	@RequestMapping(value = { "/students/submitStua" }, method = { RequestMethod.POST })
	public Object submitStua(HttpServletRequest req, Model model, @RequestParam("tcaId") Integer tcaId) {
		User user = (User) req.getSession().getAttribute("user");
		Student stu = studentService.readByUser(user);
		Tca tca = tcaService.readById(tcaId);
		Stua stua = stuaService.submitStua(stu, tca);
		model.addAttribute("stua", stua);
		return model;
	}

}
