package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import Bean.UserBean;
import service.loginservice;
import utils.*;
/**
 * Servlet Filter implementation class autolloginFilter
 */
@WebFilter("/autolloginFilter")
public class autolloginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public autolloginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		/*
		 *判断session中是否有用户的信息:
		 * * session中如果有:放行.
		 * * session中没有:
		 *    * 从Cookie中获取:
		 *        * Cookie中没有:放行.
		 *        * Cookie中有:
		 *            * 获取Cookie中存的用户名和密码到数据库查询.
		 *                * 没有查询到:放行.
		 *                * 查询到:将用户信息存入到session . 放行.
		 */
         HttpServletRequest req=(HttpServletRequest) request;
         UserBean user=(UserBean)req.getSession().getAttribute("user");
         if(user!=null) {
 			chain.doFilter(req, response);
         }
         else {
        	 Cookie[] cookies=req.getCookies();
        	 Cookie cookie=CookieUtils.findCookie(cookies, "autologin");
        	 if(cookie!=null) {
        		 String username = cookie.getValue().split("#")[0];
 				System.out.println(username);
 				String password = cookie.getValue().split("#")[1];
 				System.out.println(password);
				loginservice loginService = new loginservice();
 				try {
 					UserBean u=loginService.login(username, password);
 					if (u!=null) {
 						req.getSession().setAttribute("user", u);
 						 chain.doFilter(req, response);
 					}
 					else {
 						chain.doFilter(req, response);
 					}
 				}   	 
 				catch (Exception e) {
					e.printStackTrace();
				}
        	 }
 				else {
					chain.doFilter(req, response);
				}
         }
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
