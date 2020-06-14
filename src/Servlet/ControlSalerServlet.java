package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.conf.ConnectionUrl.Type;

import Bean.SalerBean;
import Bean.UserBean;
import Dao.LogDao;
import Dao.UserDao;
import service.loginservice;
import utils.IpadrUtils;

/**
 * Servlet implementation class ControlSalerServlet
 */
@WebServlet("/ControlSalerServlet")
public class ControlSalerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlSalerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String type=request.getParameter("type");
		String sid=request.getParameter("sid");
		String password=request.getParameter("pass");
		String ipadr=IpadrUtils.getRemoteIp(request);
		String date=IpadrUtils.getTime(); 
		UserBean user=new UserBean();
		user=(UserBean)request.getSession().getAttribute("manager");
		LogDao logdao=new LogDao();
		try {
			logdao.insertLogRecord(user.getUsername(), "manager", date, ipadr, "UpdateProductLog");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		UserDao dao=new UserDao();
		int bool=0;
		try {
		if(type.equals("update")) {			
				bool=dao.UpdateSalerPassword(sid,password);
				// TODO Auto-generated catch block
		}
		if(type.equals("thaw")) {			
			bool=dao.ThaweSaler(sid);
			// TODO Auto-generated catch block
	}
		if(type.equals("freeze")) {
			bool=dao.FreezeSaler(sid);
		}}
	catch (SQLException e) {
		e.printStackTrace();
	}
		response.sendRedirect(request.getContextPath()+"/SalerManage.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
