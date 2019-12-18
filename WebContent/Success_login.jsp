<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>成功注册！2秒后进入登录界面</title>
</head>
<body>
<p>你好${sessionScope.user.username}用户</p>
 <% response.setHeader("refresh","1;URL=index.jsp"); %>
</body>
</html>