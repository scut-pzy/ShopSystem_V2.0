package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.*;
import Dao.*;
import utils.emailutils;
/**
 * Servlet implementation class buyProductServlet
 */
@WebServlet("/buyProductServlet")
public class buyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyProductServlet() {
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
		String product_id=request.getParameter("id");
		UserBean user=(UserBean) request.getSession().getAttribute("user");
		if(user==null) {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		String username=user.getUsername();
        ProductDao pdao=new ProductDao();
        ProductBean product=new ProductBean();
        try {
			product=(ProductBean)pdao.finDetailProduct(product_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String product_name=product.getName();
        String num=product.getNum();
        String price=product.getPrice();
		buyDao dao=new buyDao();
		cartDao cdao=new cartDao();
		int count=0;
		int bool=0;
		try {
			count = dao.getcount();
			//count = dao.getcount("root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bool=dao.insert(count,username,product_name,price);
			 //bool=dao.insert(count,product_id,user.getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bool!=0) {
			try {
				cdao.delete(product_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mail=user.getEmail();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	         String date=df.format(new Date());// new Date()为获取当前系统时间
			String title="订单:"+product_name+"**发货通知";
			String message="账户:"+username+"于--"+date+"--购买的"+product_name+"(价格："+product.getPrice()+" 数量："+num+")已经发货";
			emailutils.sendMail(mail,message,title);
			request.getRequestDispatcher("SuccessBuy.jsp").forward(request, response);
		}
		else {
		request.getRequestDispatcher("cart.jsp").forward(request, response);
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
