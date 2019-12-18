<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>日志记录</title>
</head>
<body>
<c:if test="${not empty manager }">
<h1>浏览记录</h1>
<jsp:include page="UserHistory.jsp"></jsp:include>
<jsp:include page="statistics.jsp"></jsp:include>
<h1>购买记录</h1>
<jsp:include page="UserBuy.jsp"></jsp:include>
<jsp:include page="statistic_buy.jsp"></jsp:include>
<jsp:include page="statistic_user.jsp"></jsp:include>
</c:if>
</body>
</html>