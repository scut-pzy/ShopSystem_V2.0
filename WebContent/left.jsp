<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>导航栏</title>
<style type="text/css">
body {
	background-color: #FFCCCC;
}
.footer{
position:fixed;
bottom:100px;
}
</style>
<base target="_parent"/>
</head>
<body>
<div>
		<h2>主题市场</h2>
		<ul class="nav-hd ">
			<li><a href="#">服装</a></li>
			<br>
			<li><a href="#">香水</a></li>
			<br>
			<li><a href="#">口红</a></li>
			<br>
		</ul>
	</div>
<div class="footer">
账户余额：
<%--
<%
int mon;
if (session.getAttribute("a") == null) {
	mon=0;
} else {
	 mon = (Integer) session.getAttribute("a");
} %>
<%=mon%>元
 --%>
<br>
<br>
<a href = "money.jsp" target="_parent">账户充值</a><br><br>
<a href="shopcart.jsp">查看购物车</a><br><br>
<a href="buy.jsp?op=clear">清空购物测</a>
</div>	
</body>
</html>