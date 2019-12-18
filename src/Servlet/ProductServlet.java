package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import Bean.ProductBean;
import service.produceService;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Product_llistServlet
 */
@WebServlet("/Product_llistServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
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
		List<ProductBean> list=null;
		try {
			    list=new produceService().finALLProduct();			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getSession().setAttribute("seesionlist", list);
		System.out.println("获取list成功:"+list.size());
		request.getRequestDispatcher("/ProductList.jsp").forward(request, response);		
		//response.sendRedirect(request.getContextPath()+"/ProductList.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
