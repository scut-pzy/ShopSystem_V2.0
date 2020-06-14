package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.group.interceptors.StaticMembershipInterceptorMBean;

import Bean.UserdrawBean;
import Dao.ProductTrendDao;
import Dao.UserdrawDao;

/**
 * Servlet implementation class UserdrawServlet
 */
@WebServlet("/UserdrawServlet")
public class UserdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserdrawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		UserdrawDao dao=new UserdrawDao();
		ProductTrendDao ptdao=new ProductTrendDao();
		try {
			int bool =dao.InsertintoMiddleTable();//整合三个表数据至中间表
			dao.getUserLable();//将中间表的数据整理并且加工到用户画像表中
			ptdao.getProductTrend();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 List<UserdrawBean> listUserdraw=new ArrayList<UserdrawBean>();
			 List<UserdrawBean> listProducttrend=new ArrayList<UserdrawBean>();
			 List<UserdrawBean> listDrawTable=new ArrayList<UserdrawBean>();
			listUserdraw=dao.getUserDraw();
			listProducttrend=dao.getProducttrend();
			listDrawTable=dao.getDrawTable();
			request.setAttribute("listUserdraw", listUserdraw);
			request.setAttribute("listProducttrend", listProducttrend);
			request.setAttribute("listDrawTable", listDrawTable);
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		request.getRequestDispatcher("UserDraw.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
