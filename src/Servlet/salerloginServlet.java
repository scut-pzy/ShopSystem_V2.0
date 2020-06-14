package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.SalerBean;
import Bean.UserBean;
import Dao.LogDao;
import Dao.UserDao;
import service.loginservice;
import utils.IpadrUtils;

/**
 * Servlet implementation class salerloginServlet
 */
@WebServlet("/salerloginServlet")
public class salerloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public salerloginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String ipadr=IpadrUtils.getRemoteIp(request);
		 String date=IpadrUtils.getTime();
		response.setContentType("text/html;charset=utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		loginservice lg=new loginservice();
		LogDao logdao=new LogDao();
		SalerBean user = null;
		try {
				user = lg.Salerlogin(username, password);		
				//System.out.print(user.getUsername());
				if(user.getUsername()!=null) {
				request.getSession().setAttribute("user", user);
				String autologin=request.getParameter("autologin");
				if(autologin!=null) {
					Cookie cookie=new Cookie("autologin", username+"#"+password);
					cookie.setMaxAge(60);
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					System.out.println(cookie);
				}
				String sid=user.getUid();
				try {
					if(user.getUsername()!=null) {
						logdao.insertLogRecord(user.getUsername(), "slaver", date, ipadr, "login");
						String autologoutdate=IpadrUtils.getAutoLogoutTime(date);
						logdao.insertLogRecord(user.getUsername(), "slaver", autologoutdate, ipadr, "logout");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				request.getRequestDispatcher("/CatelogServlet?catelog=product&&sid="+sid).forward(request, response);;
				System.out.println("登陆成功");
			}
			else {
				 response.sendRedirect(request.getContextPath()+"/Failed_login.jsp");
				 System.out.println("登陆失败");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 response.sendRedirect(request.getContextPath()+"/Failed_login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
