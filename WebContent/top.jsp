<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body {
}
</style>
</head>
<body>
	<h1 align="center">在线购物</h1>
		<div align="center">
		<form  action="${ pageContext.request.contextPath}/ShowProductServlet" method=post>
			<input type="text" style="width: 500px; height: 25px" name="des"/> 			
			<input type="submit" value="搜索" />
		</form>
		</div>
</body>
</html>