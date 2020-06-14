<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@page language="java" import="Bean.*"%>
<link rel="stylesheet" type="text/css" href=/ShopSystem/css/style.css> 
<html>
<head>
<meta charset="GB18030">
<title>管理员操作分类</title>
</head>
<body>
<div class="effect" >
<c:if test="${not empty manager }">
<form action="${pageContext.request.contextPath}/CatelogServlet?catelog=saler" method="post">
<input   id="1" class="div2" type="submit" value="销售人员管理"><label for="1" >销售人员管理</label>
</form>
<form action="${pageContext.request.contextPath}/CatelogServlet?catelog=user" method="post">
<input  id="2" class="div 2" type="submit" value="销售业绩管理"><label for="2">销售业绩管理</label>
</form>
<form action="${pageContext.request.contextPath}/CatelogServlet?catelog=userdraw" method="post">
<input  id="3" class="div 2" type="submit" value="用户画像管理"><label for="3">用户画像管理</label>
</form>
</c:if>
</div>
</body>
</html>