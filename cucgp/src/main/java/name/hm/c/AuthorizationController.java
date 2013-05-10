package name.hm.c;

//{{{
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.hm.jpa.RoleMapper;
import name.hm.jpa.UserMapper;
import name.hm.m.Action;
import name.hm.m.Group;
import name.hm.m.User;
import name.hm.m.Role;
import name.hm.s.AuthorizationService;
import name.hm.s.e.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
//}}}

@Controller
@RequestMapping(value="/auth")
public class AuthorizationController
{
  @Autowired
    public AuthorizationService authorizationService;

  @RequestMapping(value = { "/login" }, method = { RequestMethod.POST })
    public String login(@RequestParam("userName") String username, @RequestParam("password") String password, HttpServletRequest req, Model model)
    throws ServerException {
    User user = null;
    Group group = null;
    ArrayList<Role> roles = new ArrayList<Role>();
    ArrayList<Action> actions = new ArrayList<Action>();

    try {
      authorizationService.startService();

      user = authorizationService.loadUser(username, password);

      group = authorizationService.loadGroup(user);
      roles = authorizationService.loadRoles(user);
      actions = authorizationService.loadActions(user);

      authorizationService.endService();

      req.getSession().setAttribute("user", user);
      req.getSession().setAttribute("roles", roles);

      Map<String, String> map = new HashMap<String, String>();

      map.put("result", "yes");
      model.addAttribute("json",JSON.toJSON(map));

      return "json";
    } catch (RightException e) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("msg", "用户或密码错误!");
      model.addAttribute("json",JSON.toJSON(map));
      return "json";
    } catch (LoginException e) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("msg", "用户或密码错误!");
      model.addAttribute("json",JSON.toJSON(map));
      return "json";
    } catch (ServiceException e) {
      Map<String, String> map = new HashMap<String, String>();
      e.printStackTrace();
      throw new ServerException("Error");
    } 
    }

  @RequestMapping(value = { "/logout" }, method = { RequestMethod.POST, RequestMethod.GET })
    public String logout(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
      req.getSession().removeAttribute("user");
      req.getSession().invalidate();
      return "redirect:/";
    }
}
