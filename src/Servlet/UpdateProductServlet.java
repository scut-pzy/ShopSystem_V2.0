package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ProductBean;
import Dao.ProductDao;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
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
		String id=request.getParameter("id");
		//String id="2";
		String type=request.getParameter("type");
		//String type="delete";
		int bool=0;
		if(type.equals("insert")) {
		String catelog=request.getParameter("catelog");
		String name=request.getParameter("name");
		String price=request.getParameter("price");
		String num=request.getParameter("num");
		String des=request.getParameter("des");
		ProductBean product=new ProductBean();
		product.setCatelog(catelog);
		product.setName(name);
		product.setPrice(price);
		product.setNum(num);	
		product.setImgurl(id);
		product.seTDes(des);
		product.setId(id);
		ProductDao dao=new ProductDao();
		
		try {
			bool=dao.insert(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if(type.equals("delete")) {
			ProductDao dao=new ProductDao();
			try {
				 bool= dao.delete(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(type.equals("update")) {
			ProductDao dao=new ProductDao();
			try {
				String catelog=request.getParameter("catelog");
				String name=request.getParameter("name");
				String price=request.getParameter("price");
				String num=request.getParameter("num");
				String des=request.getParameter("des");
				 bool= dao.update(id,catelog,name,price,num,des);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(bool==1) {
			System.out.println("成功");
			request.getRequestDispatcher("CatelogServlet?catelog=product").forward(request, response);
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
