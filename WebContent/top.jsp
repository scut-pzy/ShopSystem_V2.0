<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
        .weizhi{
        position:absolute;
           right:20%;
          top:11%;
        }
  </style>

</head>
<body>
		<form  action="${ pageContext.request.contextPath}/ShowProductServlet" method=post>
			<input type="text" style="width: 500px; height: 25px" name="des" /> 			
			<input class="weizhi"type="submit" value="搜索"  />
		</form>
</body>
</html>