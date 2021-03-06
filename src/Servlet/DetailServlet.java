package Servlet;
import java.sql.SQLException;
import java.util.List;
import Bean.ProductBean;
import Bean.SalerBean;
import Bean.UserBean;
import Dao.LogDao;
import Dao.ProductDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.*;
import utils.IpadrUtils;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		LogDao logdao=new LogDao();
		SalerBean user=new SalerBean();
		user=(SalerBean)request.getSession().getAttribute("user");
		String usertype=request.getSession().getAttribute("usertype").toString();
		String ipadr=IpadrUtils.getRemoteIp(request);
		String date=IpadrUtils.getTime();
		try {
			logdao.insertLogRecord(user.getUsername(), usertype, date, ipadr, "SelecProduct");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		 
		ProductBean detail=null;
		String id=request.getParameter("nowid");
		List<ProductBean> p=(List<ProductBean>)request.getAttribute("list");
		//System.out.print(url1);
		try {
			//String id=(String) request.getAttribute("url");
				
			detail=(ProductBean)new produceService().findDetailProduct(id);
			System.out.println(detail.getId());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("detail", detail);		
		request.getRequestDispatcher("/DetailProduct.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
