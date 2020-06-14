package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.UserBean;
import Dao.cartDao;

/**
 * Servlet implementation class cartServlet
 */
@WebServlet("/cartServlet")
public class cartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserBean user = null;
		user=(UserBean)request.getSession().getAttribute("user");
		String username=user.getUsername();
		String pnum=request.getParameter("pnum");
		String pid=request.getParameter("pid");
		String pname=request.getParameter("pname");
		String pcatelog=request.getParameter("pcatelog");
		cartDao dao=new cartDao();
		int num=0;
		int bool=0;
		try {
			num = dao.getid();
			num++;
			String id=num+"";
			bool=dao.insert(id, pid, username, pnum,pname,pcatelog);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(bool==1) {
			System.out.print("插入成功");
		}
		else
			System.out.print("插入成功");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
