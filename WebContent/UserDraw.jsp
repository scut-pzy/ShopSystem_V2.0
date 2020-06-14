<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.*" %>
<%@ page language="java" import="Dao.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        li{
         border-collapse: collapse;
            margin: 0 auto;
            margin-top:100px;
            text-align: center;
            font-size: 20px;
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
  </style>
<head>
<meta charset="GB18030">
</head>
<body>
<h2 style="margin-left: 40%">用户推荐</h2>
<table border="1px solid #96c2f1" background="#eff7ff" align="center">
        <tr>
            <td>用户名</td>
            <td>标签1st</td>
            <td>标签2nd</td>
        </tr>
    <c:forEach items="${listUserdraw }" var="h">
        <tr>
            <td>${h.username}</td>
            <td>${h.lable}</td>
            <td>${h.otherlable }</td>
        </tr>
    </c:forEach>    
    </table>
    <h2 style="margin-left: 40%">商品销售趋势</h2>
<table border="1px solid #96c2f1" background="#eff7ff" align="center">
        <tr>
            <td>商品类别</td>
            <td>商品1st</td>
            <td>商品2nd</td>
            <td>商品srd</td>
        </tr>
    <c:forEach items="${listProducttrend }" var="h">
        <tr>
            <td>${h.lable}</td>
            <td>${h.otherlable }</td>
            <td>${h.product}</td>
             <td>${h.username}</td>
        </tr>
    </c:forEach>    
    </table>
    <h2 style="margin-left: 40%">用户画像</h2>
<table border="1px solid #96c2f1" background="#eff7ff" align="center">
        <tr>
            <td>用户</td>
            <td>商品</td>
            <td>行为</td>
            <td>行为次数</td>
            <td>行为权重</td>
            <td>日期</td>          
            <td>时间衰减比重</td>
            <td>分类</td>
        </tr>
    <c:forEach items="${listDrawTable }" var="h">
        <tr>
           	<td>${h.username}</td>
           	<td>${h.product}</td>
           	<td>${h.action}</td>
            <td>${h.actiontime}</td>
            <td>${h.weight }</td>
            <td>${h.date }</td>
            <td>${h.timereduce }</td>
            <td>${h.lable }</td>
        </tr>
    </c:forEach>    
    </table>
</body>
</html>