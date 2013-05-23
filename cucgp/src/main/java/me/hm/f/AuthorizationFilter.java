package me.hm.f;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.hm.m.User;

import org.apache.log4j.Logger;
//{{{
//}}}

public class AuthorizationFilter implements Filter
{
  static Logger logger = null;

  public void init(FilterConfig filterConfig) throws ServletException
  {
    logger = Logger.getLogger("cucgp");
  }

  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException
  {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    System.out.println(req.getRequestURI());
    // js/css
    if (req.getRequestURI().matches("^(.*)(\\.js)$") ||
        req.getRequestURI().matches("^(.*)(\\.css)$") ||
        req.getRequestURI().matches("^(.*)(\\.jpg)$") ||
        req.getRequestURI().matches("^(.*)(\\.png)$") ||
        req.getRequestURI().matches("^(.*)(\\.gif)$") ||
        req.getRequestURI().matches("^(.*)(\\.html)$") ||
        req.getRequestURI().matches("^(.*)$")
       ) {
      chain.doFilter(request, response);
      return;
       }

    // login
    if (req.getRequestURI().matches("^/$") ||
        req.getRequestURI().matches("^(/rest)(.*)(/login)$") ||
        req.getRequestURI().matches("^(/druid)(.*)$")) {
      chain.doFilter(request, response);
      return;
        }

    HttpSession se = req.getSession();
    User user = (User) se.getAttribute("user");
    if (user == null) {
        resp.setStatus(404);
        return;
    } else {
        chain.doFilter(request, response);
        return;
    }
  }

  public void destroy()
  {}
}
