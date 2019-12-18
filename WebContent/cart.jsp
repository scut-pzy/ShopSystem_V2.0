<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.*" %>
<%@ page language="java" import="Dao.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
UserBean user  =null;
user=(UserBean)request.getSession().getAttribute("user");
int i=0;
if(user!=null){
String username=user.getUsername();
//String username="root";
cartDao dao=new cartDao();
List<String> list=new ArrayList<String>();
List<ProductBean> cartlist=null;
list=(List<String>)dao.findProductid(username);
System.out.print(list);
cartlist=(List<ProductBean>)dao.finAllCart(list);
request.getSession().setAttribute("cartlist",cartlist);

if(cartlist!=null){
	i=cartlist.size();
}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>购物车</title>
</head>
<body>
<li>共有记录：<%=i%>条商品记录</li>
<a href="index.jsp"><button>返回主页</button></a>
<table border="1px solid #96c2f1" background="#eff7ff" align="center">
        <tr>
            <td>id</td>
            <td>商品名</td>
            <td>价格</td>
            <td>分类</td>
            <td>数量</td>
             <td>选项</td>
        </tr>
    <c:forEach items="${cartlist }" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name }</td>
            <td>${product.price}</td>
            <td>${product.catelog}</td>
            <td>${product.num}</td>
             <td>
                 <form action="${pageContext.request.contextPath }/buyProductServlet?id=${product.id }" method=post>
                 <input type="submit" name="buy" value="支付">
                 </form>
             </td>
  	           
        </tr>
    </c:forEach>    
    </table>
</body>
</html>