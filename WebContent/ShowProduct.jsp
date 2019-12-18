<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.*" %>
<%@ page language="java" import="Dao.*" %>
<link rel="stylesheet" type="text/css" href=/ShopSystem/css/detail.css> 
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>商品展示</title>
</head>
<%
ProductBean product=null;
UserBean user=null;
ProductDao dao=new ProductDao();
String id=request.getParameter("id");
user=(UserBean)request.getSession().getAttribute("user");
if(user!=null){
String username=user.getUsername();
product=(ProductBean)dao.finDetailProduct(id);
request.setAttribute("product", product);
internetHistory hdao=new internetHistory();
hdao.insert(username,product);
}
%>
<body>
<c:if test="${empty user }">
<a href="Login.jsp"><button class="ok">登录</button></a>
<a href="Register.jsp"><button class="yes">注册</button></a>
</c:if>
<div>
<p>
    <div class="box7">
<img alt="" src="">
</img>
    图片
    </div>
</p>
 <div class="container "> 
             <div class="mui-row mui-col-xs-12 mui-clearfix">
                 <span class="mui-col-xs-6">
                     <p class="red"> 商品名称:${product.name}</p>
                     <p class="red"> 商品分类:${product.catelog}</p>
                     <p class="red"> 商品价格:${product.price}</p>
                     <p class="red">  商品库存:${product.num}</p>
                     <p class="red">  商品描述:${product.des}</p>
                 </span>
             </div>
             <c:if test="${not empty user }">
    <form method=post action="${pageContext.request.contextPath}/cartServlet?id=${param.id}">
     <c:if test="${not buy}">
          <input type="submit" class="mui-col-xs-6" value="加入购物车">
      </c:if>
       <c:if test="${buy}">
          <input type="button" class="mui-col-xs-6" value="已经添加">
      </c:if>     
    </form>
</c:if>
</div>
</div>

</body>
</html>