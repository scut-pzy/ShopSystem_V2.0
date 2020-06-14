<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.ProductBean" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>产品详情</title>
</head>
<body>
<%
    ProductBean detail=null;
    detail=(ProductBean)request.getAttribute("detail");
    System.out.print(detail);
    request.setAttribute("list", detail);
    String sid=request.getSession().getAttribute("sid").toString();
%>   
<c:if   test="${not empty  detail }">

<p>商品ID:<%=detail.getId() %></p>

<form action="${pageContext.request.contextPath }/UpdateProductServlet?type=update&nowid=<%=detail.getId()  %>&&sid=<%=sid %> " method=post>
<p>分类:<%=detail.getCatelog()%>

    
<input list="companys" name="catelog" editable="false" placeholder="请输入新商品分类"/>
  <datalist id="companys">
  <option value="手机数码">
  <option value="电子产品">
  <option value="衣服服饰">
  <option value="穿戴设备">
  <option value="生活用品">
  <option value="医疗药物">
  <option value="交通出行">
  </datalist>
</p>

<p>商品名:<%=detail.getName() %>
    <input type="text" name="name"  placeholder="请输入新商品名">
</p>
<p>图片:<%=detail.getImgurl() %></p>

<p>价格:<%=detail.getPrice() %>
<input type="text"  name="price" placeholder="请输入新价格">
</p>
<p>库存:<%=detail.getNum() %>
<input type="text" name="num" placeholder="请输入新库存">
</p>
<p>描述:<%=detail.getDes() %></p>
<input type="text" name="des"  placeholder="请输入新描述">
<p>状态:<%=detail.getStatue() %></p>
<input list="c2"  name="statue"  editable="false" placeholder="请输入商品状态"/>
  <datalist id="c2">
  <option value="sell">
  <option value="off">

  </datalist>
<p><input type="submit"  value="提交修改"></p>
</form>
</c:if>
<!-- <p>/Shop/ProductList.jsp?begin=${sessionScope.page_current  }</p> -->
<form action="${pageContext.request.contextPath }/proServlet?sid=<%=sid%>" method="post">
<input type="submit" value="返回主页">
</form>
</body>
</html>