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

import Bean.BuyBean;
import Bean.BuyLoginBean;
import Bean.ProductBean;
import Dao.buyDao;
import service.produceService;

/**
 * Servlet implementation class proServlet
 */
@WebServlet("/proServlet")
public class proServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public proServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String sid=request.getParameter("sid");
		List<ProductBean> list=null;		
		List<BuyBean>  buylist =null;
		try {			
			    list=new produceService().finALLProduct(sid);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			buylist =new ArrayList<BuyBean>();
			buyDao dao=new buyDao();
			buylist=dao.findBuy(sid);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("buylist", buylist);
		request.getSession().setAttribute("list", list);
		request.getSession().setAttribute("sid",sid);
		System.out.println("获取list成功:"+list.size());
		//request.getRequestDispatcher("/ProductList.jsp").forward(request, response);
		
		response.sendRedirect(request.getContextPath()+"/ProductList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
