package name.hm.f;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.hm.m.User;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.session.AbstractSessionManager.Session;

public class UserRightFilter implements Filter
{
	static Logger logger = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		logger = Logger.getLogger("cucgp");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		if (req.getRequestURI().contains("login.ac")) {
			chain.doFilter(request, response);
		}
		HttpSession se = req.getSession();
		User user = (User) se.getAttribute("user");
		if (user == null) {
			resp.sendRedirect("login.ac");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy()
	{}
}
