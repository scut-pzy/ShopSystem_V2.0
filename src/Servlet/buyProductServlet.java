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
import utils.IpadrUtils;
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
		String saler_id=request.getParameter("sid");
		String cart_id=request.getParameter("cid");
		String product_num=request.getParameter("num");
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
        String pcatelog=product.getCatelog();
        String price=product.getPrice();
        String StockNum=product.getNum();//存货
        String ShopStatue=product.getStatue();//存货
        String SumPrice=Integer.parseInt(price)*Integer.parseInt(product_num)+"";
		buyDao bdao=new buyDao();
		cartDao cdao=new cartDao();
		int count=0;
		int bool=0;
		try {
			count = bdao.getcount();
			count++;
			//count = dao.getcount("root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bool=pdao.update(product_id,StockNum,product_num,ShopStatue);
			if(bool!=0)
				{cdao.delete(product_id,username,cart_id);	}
			else {
				cdao.deleteShopCart(product_id,username);//可能购买数大于商品数，或者商品已经下架处理了
				request.getRequestDispatcher("cart.jsp").forward(request, response);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      if(bool!=0) {
		try {
			bool=bdao.insert(count,username,product_name,saler_id,price,product_num,SumPrice,pcatelog);
			 //bool=dao.insert(count,product_id,user.getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
      else {
  		request.getRequestDispatcher("cart.jsp").forward(request, response);
  	}	
      		String mail=user.getEmail();
      		String date=IpadrUtils.getTime();
			String title="订单:"+product_name+"**发货通知";
			String message="账户:"+username+"于--"+date+"--购买的"+product_name+"(价格："+product.getPrice()+" 数量："+product_num+")已经发货";
			emailutils.sendMail(mail,message,title);
			request.getRequestDispatcher("SuccessBuy.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
