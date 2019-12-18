package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.UserBean;
import Dao.UserDao;
import service.loginservice;
/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		UserDao userDao=new UserDao();	
		loginservice lg=new loginservice();
		UserBean user = null;
		String type=request.getParameter("type");
		try {
			if(type==null){
				user = lg.login(username, password);		
				System.out.print(user.getUsername());
				}	
			else if(type.equals("manager")) 	{
				user =userDao.ManagerLogin(username, password);
				request.getSession().setAttribute("manager", "m");
				Cookie cookie=new Cookie("autologin", username+"#"+password);
				response.addCookie(cookie);
				System.out.println(cookie);
			response.sendRedirect(request.getContextPath()+"/Catelog.jsp");
			System.out.println("管理员登录成功");
			return;
				}		
			if(user.getUsername()!=null) {
				request.getSession().setAttribute("user", user);
				String autologin=request.getParameter("autologin");
				if(autologin!=null) {
					Cookie cookie=new Cookie("autologin", username+"#"+password);
					cookie.setMaxAge(60*3);
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					System.out.println(cookie);
				}
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				System.out.println("登陆成功");
			}
			else {
				 response.sendRedirect(request.getContextPath()+"/Failed_login.jsp");
				 System.out.println("登陆失败");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		/*user.setUsername(username);
		user.setPassword(password);
		user.setEmail(" ");
		user.setPhone_number(" ");
		request.getSession().setAttribute("user", user);
		Cookie cookie=new Cookie(username, password);
		cookie.setMaxAge(60*60);
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath()+"/Success_login.jsp");
		//System.out.print("hello");*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
