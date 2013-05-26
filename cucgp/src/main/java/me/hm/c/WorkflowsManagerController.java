package me.hm.c;

//{{{
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import me.hm.m.Workflow;
import me.hm.s.AuthorizationService;
import me.hm.s.WorkflowsService;
import me.hm.s.e.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.IOUtils;
import com.alibaba.fastjson.JSON;

//}}}

@Controller
@RequestMapping(value = "/workflows")
public class WorkflowsManagerController {
	@Autowired
	public AuthorizationService authorizationService;
	@Autowired
	public WorkflowsService workflowsManagerService;

	// TODO 加入权限控制!
	@RequestMapping(value = { "/reads" }, method = { RequestMethod.GET })
	public @ResponseBody
	Object reads(HttpServletRequest req, Model model) throws ServerException {
		List<Workflow> workflows = workflowsManagerService.readWorkflows();
		model.addAttribute("success", true);
		model.addAttribute("workflows", workflows);
		return model;
	}

	@RequestMapping(value = { "/creates" }, method = { RequestMethod.POST })
	public @ResponseBody
	Object creates(HttpServletRequest req, Model model) throws ServerException {
		ServletInputStream inputStream;
		try {
			inputStream = req.getInputStream();
			String str = IOUtils.read(inputStream);
			Workflow t = new Workflow(JSON.parseObject(str));
			workflowsManagerService.insertWorkflow(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("success", true);
		model.addAttribute("result", true);
		return model;
	}

	@RequestMapping(value = { "/updates" }, method = { RequestMethod.POST })
	public @ResponseBody
	Object updates(HttpServletRequest req, Model model) throws ServerException {
		ServletInputStream inputStream;
		try {
			inputStream = req.getInputStream();
			String str = IOUtils.read(inputStream);
			Workflow t = new Workflow(JSON.parseObject(str));
			workflowsManagerService.updateWorkflow(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("success", true);
		model.addAttribute("result", true);
		return model;
	}

	@RequestMapping(value = { "/deletes" }, method = { RequestMethod.DELETE })
	public @ResponseBody
	Object deletes(HttpServletRequest req, Model model) throws ServerException {
		ServletInputStream inputStream;
		try {
			inputStream = req.getInputStream();
			String str = IOUtils.read(inputStream);
			Workflow t = new Workflow(JSON.parseObject(str));
			workflowsManagerService.deleteWorkflow(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("success", true);
		model.addAttribute("result", true);
		return model;
	}
}
