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
import utils.IpadrUtils;
import Bean.UserBean;
import Dao.LogDao;
import Dao.UserDao;
/**
 * Servlet implementation class Logoutservlet
 */
@WebServlet("/Logoutservlet")
public class Logoutservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logoutservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String ipadr=IpadrUtils.getRemoteIp(request);
		 String date=IpadrUtils.getTime();
		 LogDao logdao=new LogDao();
		UserBean user=(UserBean)request.getSession().getAttribute("user");
		try {
			if(user.getUsername()!=null) {
				logdao.insertLogRecord(user.getUsername(), "user", date, ipadr, "logout");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
		request.getSession().removeAttribute("user");
		Cookie cookie=new Cookie("autologin","msg");
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
