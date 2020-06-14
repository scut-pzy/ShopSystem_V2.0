<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
</head>
<body>
<form action="${pageContext.request.contextPath }/LogServlet" method=post>
<input type="submit" name="buy" value="支付">
</form>
<c:forEach items="${logbuylist}" var="l">
<p>${l.sid }
</p>
</c:forEach>
</body>
</html>