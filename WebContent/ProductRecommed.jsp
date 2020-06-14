<%@page import="Dao.ProductDao"%>
<%@page import="Bean.ProductBean"%>
<%@page import="Bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href=/ShopSystem/css/detail.css> 

<%
String timelast=request.getParameter("timelast");

try{
if(request.getSession().getAttribute("lastname")!=null){
String lastname=request.getSession().getAttribute("lastname").toString();
String lastcatelog=request.getSession().getAttribute("lastcatelog").toString();
String lastprice=request.getSession().getAttribute("lastprice").toString();
}}
catch(Exception e){
}
try{
UserBean user=(UserBean)request.getSession().getAttribute("user");
ProductDao dao=new ProductDao();
List<ProductBean> recomlist=new ArrayList<ProductBean>();
recomlist=dao.finAllProductInUserProductRecommend(user.getUsername());
request.getSession().setAttribute("recomlist", recomlist);
}
catch(Exception e){
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>商品列表</title>
</head>
<body>
<c:forEach items="${recomlist }"  var="list">
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
                     <p class="red"> 商品名称:${list.name}</p>
                     <p class="red"> 商品分类:${list.catelog}</p>
                     <p class="red"> 商品价格:${list.price}</p>
                     <p class="red">  商品库存:${list.num}</p>
       <c:if  test="${not empty user }">
           <form method=post action="${pageContext.request.contextPath}/ShowProduct.jsp?id=${list.id}">
                  <input type="submit" class="mui-col-xs-6" value="查看">
                  <input type="text" class="mui-col-xs-6" id="timelast" name="tila" style="display:none"  >
          </form>
       </c:if>
                 </span>
             </div>


</div>
</div>
</c:forEach>

<input class="fk"  type="text" id="timelast" name="timelast" style="display:none"/>
</body>
</html>
<script type="text/javascript">
<!--

//-->

function statisticsStay(){
    console.log(localStorage.getItem('testSecond'));
    var second = 0;
    var p=document.getElementById("timelast");
    p.value=localStorage.getItem('testSecond');
    //开启定时器记录页面停留时间
}
statisticsStay();
</script>