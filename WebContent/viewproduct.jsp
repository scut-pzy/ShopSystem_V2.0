<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href=/ShopSystem/css/detail.css> 
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>商品列表</title>
<c:if  test="${empty user }">
<a href="Login.jsp"><button class="ok">登录</button></a>
<a href="Register.jsp"><button class="yes">注册</button></a>
</c:if>
<c:forEach items="${product }"  var="list">
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
                   <form method=post action="${pageContext.request.contextPath}/ShowProduct.jsp?id=${list.id}">
          <input type="submit" class="mui-col-xs-6" value="查看">
    </form>
                 </span>
             </div>


</div>
</div>
</c:forEach>
</head>
<body>

</body>
</html>