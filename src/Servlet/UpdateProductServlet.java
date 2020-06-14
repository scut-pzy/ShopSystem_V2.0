package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ProductBean;
import Bean.SalerBean;
import Bean.UserBean;
import Dao.LogDao;
import Dao.ProductDao;
import utils.IpadrUtils;

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
		//String id=request.getParameter("id");
		
		String sid=request.getSession().getAttribute("sid").toString();
		//String id="2";
		SalerBean user=new SalerBean();
		user=(SalerBean)request.getSession().getAttribute("user");
		LogDao logdao=new LogDao();
		String type=request.getParameter("type");
		String usertype=request.getSession().getAttribute("usertype").toString();
		String ipadr=IpadrUtils.getRemoteIp(request);
		String date=IpadrUtils.getTime(); 
		
		//String type="delete";
		int bool=0;
		ProductDao dao=new ProductDao();
		String id=null;
		String nowid=request.getParameter("nowid");
		try {
			int num=dao.getid();
			num++;
			id = (num+"");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(type.equals("insert")) {
		String catelog=request.getParameter("catelog");
		String name=request.getParameter("name");
		String price=request.getParameter("price");
		String num=request.getParameter("num");
		String des=request.getParameter("des");
		ProductBean product=new ProductBean();
		product.setId(id);
		product.setCatelog(catelog);
		product.setName(name);
		product.setPrice(price);
		product.setNum(num);	
		product.setImgurl(id);
		product.seTDes(des);
		product.setSid(sid);
		product.setStatue("sell");
		try {
			logdao.insertLogRecord(user.getUsername(), usertype, date, ipadr, "InsertProduct");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			bool=dao.insert(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if(type.equals("delete")) {
			try {
				 bool= dao.delete(nowid,sid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				logdao.insertLogRecord(user.getUsername(), usertype, date, ipadr, "DeleteProduct");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		if(type.equals("update")) {
			try {
				String catelog=request.getParameter("catelog");
				String name=request.getParameter("name");
				String price=request.getParameter("price");
				String num=request.getParameter("num");
				String des=request.getParameter("des");
				String statue=request.getParameter("statue");
				 bool= dao.update(nowid,catelog,name,price,num,des,statue);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				logdao.insertLogRecord(user.getUsername(), usertype, date, ipadr, "UpdateProduct");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		if(bool==1) {
			System.out.println("成功");
			request.getRequestDispatcher("CatelogServlet?catelog=product&&sid="+sid).forward(request, response);
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
