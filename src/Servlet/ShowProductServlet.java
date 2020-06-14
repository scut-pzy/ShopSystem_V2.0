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
import Bean.*;
import Dao.*;
import service.produceService;
import service.prudcutshowservicce;
/**
 * Servlet implementation class ShowProductServlet
 */
@WebServlet("/ShowProductServlet")
public class ShowProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String catelog=request.getParameter("catelog");
		String id=request.getParameter("id");
		String des=request.getParameter("des");
		List<ProductBean> list=null;		
		ProductBean product=null;
		request.getSession().setAttribute("buy",false);
        if(catelog!=null) {
			try {	
				if(catelog.equals("1")) {catelog="手机数码";}
				else if(catelog.equals("2")) {catelog="电子产品";}
				else if(catelog.equals("3")) {catelog="衣服服饰";}
				else if(catelog.equals("4")) {catelog="穿戴设备";}
				else if(catelog.equals("5")) {catelog="生活用品";}
				else if(catelog.equals("6")) {catelog="医疗药物";}
				else if(catelog.equals("7")) {catelog="交通出行";}
				
			    list=new prudcutshowservicce().findProduct(catelog);
			    request.getSession().setAttribute("productlistview", list);
				System.out.println("获取list数量:"+list.size());
				request.getRequestDispatcher("/viewproduct.jsp").forward(request, response);	
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		}
		else if (id !=null) {
			try {			
			    product=(ProductBean) new prudcutshowservicce().findDetailProduct(id);
			    request.setAttribute("productshow", product);
				//System.out.println("获取list数量:"+list.size());
			    request.getRequestDispatcher("/ShowProduct.jsp").forward(request, response);	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}			
		}
		else if (des!=null) {
			try {			
			    list=new prudcutshowservicce().searchProduct(des);
			    request.getSession().setAttribute("product", list);
				System.out.println("获取list数量:"+list.size());
				request.getRequestDispatcher("/viewproduct.jsp").forward(request, response);	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
			
		}
		else {
			response.sendRedirect("/Login.jsp");
			return;
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
