<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
 <% response.setHeader("refresh","1;URL=/ShopSystem/Login.jsp"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>登陆失败</title>
</head>
<body>
<p>用户名或者密码错误！将于一秒后返回登录页面</p>
<form action="${pageContext.request.contextPath}/Login.jsp">
    <input type="submit" value="返回"></input>
</form>
</body>
</html>