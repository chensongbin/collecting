package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class SetCharacterEncodingFilter
 */
public class SetCharacterEncodingFilter implements Filter {
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;	
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//resp.setHeader("Context-type", "text/html;charset=utf-8");
		//response.setContentType("text/html;charset=utf-8");
		
		
		//System.out.println("过滤器" + req.getRequestURL());
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
