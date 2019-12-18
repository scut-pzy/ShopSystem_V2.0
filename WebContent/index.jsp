<%@page import="Bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品交易网</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</head>
 
<body>
<jsp:include page="top.jsp"></jsp:include>

	<div class="container-fluid">
<!--  <jsp:include page="left.jsp"></jsp:include> -->
		<!--
            	描述：菜单栏
            -->
		<div class="container-fluid">
			<div class="col-md-4">
				<img src="img/logo2.png" />
			</div>
			<div class="col-md-5">
				<img src="img/header.png" />
			</div>
			<div class="col-md-3" style="padding-top: 20px">
				<ol class="list-inline">
					<c:if test="${empty user }">
						<li><a href="Login.jsp">登录</a></li>
						<li><a href="Register.jsp">注册</a></li>
					</c:if>
					<c:if test="${not empty user }">
						<li>您好！--${user.username }--用户</li>
						<li><a href="cart.jsp">购物车</a></li>
						<li><a href="history.jsp">浏览记录</a></li>
						<li><a href="${pageContext.request.contextPath}/Logoutservlet">注销</a></li>
					</c:if>
				</ol>
			</div>
		</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a href="ShowProductServlet?catelog=1">手机数码</a></li>
							<li class="active"><a href="ShowProductServlet?catelog=2">电子产品</a></li>
							<li class="active"><a href="ShowProductServlet?catelog=3">衣服服饰</a></li>
							<li class="active"><a href="ShowProductServlet?catelog=4">穿戴设备</a></li>
							<li class="active"><a href="ShowProductServlet?catelog=5">生活用品</a></li>
							<li class="active"><a href="ShowProductServlet?catelog=6">医疗药物</a></li>
							<li class="active"><a href="ShowProductServlet?catelog=7">交通出行</a></li>
						</ul>
						
 
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
 

			<div
				style="text-align: center; margin-top: 5px; margin-bottom: 20px;">
				@2019 华南理工大学计算机科学与工程学院2017级网络工程潘正源 ——版权所有，盗版必究</div>
		</div>
	</div>
</body>
 
</html>