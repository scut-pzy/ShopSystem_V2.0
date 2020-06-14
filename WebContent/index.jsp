<%@page import="Bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href=/ShopSystem/css/detail.css> 
<!DOCTYPE html>
<html>
 <style>
  table
        {
            border-collapse: collapse;
            margin: 0 auto;
            margin-top:50px;
            text-align: center;
            margin-bottom: 100px;
            font-size: 20px;
        }
        form{
         text-align: center;
         width:30%;
            font-size: 20px;
            margin-left: 30%;
        }
        input{
            font-size: 20px;
            font-size-adjust:inherit;
            width:auto;
            overflow: hidden;
            padding-left: 10px;
        }
        table td, table th
        {
            border: 1px solid #cad9ea;
            color: #666;
            height: 30px;
            padding-left: 10px;
            padding-right: 10px;
            overflow: hidden;	
        }

        table thead th
        {
            background-color: #CCE8EB;
            width: 100px;
                        padding-left: 10px;
            padding-right: 10px;
            overflow: hidden;
        }
        table tr:nth-child(odd)
        {
            background: #fff;
            overflow: hidden;
        }
        table tr:nth-child(even)
        {
            background: #F5FAFA;
            overflow: hidden;
        }
        .weizhi1{
        position:absolute;
           left:100px;
          top:100px
        }
  </style>
 
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品交易网</title>
</head>
 
<body>
<h1 align="center">在线购物</h1>
<jsp:include page="top.jsp"></jsp:include>
<div  style="width:20%">
					<c:if test="${empty user }">
						<a href="Login.jsp"><button class="ok">登录/注册</button></a>
					</c:if>
					<c:if test="${not empty user }">
						<p>您好！--${user.username }--用户</p>
						
						<li><a href="cart.jsp"><button class="yes">购物车</button></a>
						</li><li><a href="history.jsp"><button >浏览记录</button></a>
						</li><li><a href="${pageContext.request.contextPath}/Logoutservlet"><button >注销</button></a>
					</li></c:if>
</div>			
<div style="width:80%" class="weizhi1">
			<table border="1px solid #96c2f1" background="#eff7ff" align="center">
				<tr>
         	   		<td><a href="ShowProductServlet?catelog=1">手机数码</a></td>
          	  		<td><a href="ShowProductServlet?catelog=2">电子产品</a></td>
          	  		<td><a href="ShowProductServlet?catelog=3">衣服服饰</a></td>
           	 			<td><a href="ShowProductServlet?catelog=4">穿戴设备</a></td>
       			 </tr>
       			 <tr>
       			 	
            		<td><a href="ShowProductServlet?catelog=5">生活用品</a></td>
           			 <td><a href="ShowProductServlet?catelog=6">医疗药物</a></td>
           			 <td><a href="ShowProductServlet?catelog=7">交通出行</a></td>
       			 </tr>
 			</table>
</div>		
<div>
<c:if test="${not empty user }">
<h2 style="text-align: center;">推荐产品</h2>
<jsp:include page="ProductRecommed.jsp"></jsp:include>
</c:if>
</div>	
			<div
				style="text-align: center; margin-top: 100px; margin-bottom: 20px;">
				@2019 华南理工大学计算机科学与工程学院2017级网络工程潘正源 </div>
		</div>
</body>
</html>
