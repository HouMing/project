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

import org.apache.log4j.Logger;

public class FirstFilter implements Filter
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
		if (!(request instanceof HttpServletRequest)) {
			request.getRequestDispatcher("/error/protocolError.html").forward(request, response);
			logger.warn("不支持该协议！" + request.getRemoteAddr());
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy()
	{}

}
