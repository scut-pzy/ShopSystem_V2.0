package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.SalerBean;
import Bean.UserBean;
import Dao.LogDao;
import utils.IpadrUtils;

/**
 * Servlet implementation class CatelogServlet
 */
@WebServlet("/CatelogServlet")
public class CatelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatelogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String catelog=request.getParameter("catelog");
		String sid=request.getParameter("sid");
		String ipadr=IpadrUtils.getRemoteIp(request);
		String date=IpadrUtils.getTime(); 
		UserBean user=new UserBean();
		SalerBean saler=new SalerBean();
		
		user=(UserBean)request.getSession().getAttribute("manager");
		LogDao logdao=new LogDao();
		try {
		if(catelog.equals("product")) {
			     saler=(SalerBean)request.getSession().getAttribute("user");
				logdao.insertLogRecord(saler.getUsername(), "saler", date, ipadr, "SelectProductLog");
			request.getRequestDispatcher("/proServlet?sid="+sid).forward(request, response);
		}
		if(catelog.equals("userdraw")) {
			
			logdao.insertLogRecord(user.getUsername(), "manager", date, ipadr, "SelectUserDraw");
			request.getRequestDispatcher("/UserdrawServlet").forward(request, response);
		}
		else if (catelog.equals("user")){
			request.getRequestDispatcher("/directory.jsp").forward(request, response);
		}
		else if (catelog.equals("saler")){
				logdao.insertLogRecord(user.getUsername(), "manager", date, ipadr, "SelectSalerLog");
			//request.getRequestDispatcher("/SalerManage.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/SalerManage.jsp");
		}
		else {
			request.getRequestDispatcher("/Catelog.jsp").forward(request, response);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
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
