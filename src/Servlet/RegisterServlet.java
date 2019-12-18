package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.UserBean;
import Dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserBean newuser =new UserBean();
		String name=request.getParameter("username");
		String psw=request.getParameter("password1");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		newuser.setUsername(name);
		newuser.setPassword(psw);
		newuser.setPhone_number(phone);
		newuser.setEmail(email);
		UserDao dao=new UserDao();
		int bool=0;
		try {
			bool=dao.UserRegister(newuser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bool!=0) {
			System.out.println("注册成功");
			request.getRequestDispatcher("/Success_login.jsp").forward(request, response);
		}
		else {
			System.out.println("注册失败");
			request.getRequestDispatcher("/Failed_login.jsp").forward(request, response);
		}
	}

}
