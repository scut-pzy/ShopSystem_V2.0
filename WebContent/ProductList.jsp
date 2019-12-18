
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.ProductBean" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
<html>
<head>
<meta charset="GB18030">
<title>商品列表</title>
</head>
<body>
<%

List<ProductBean>  p=null;
int i=0;
if (request.getAttribute("list") !=null){
 p=(List<ProductBean>)request.getAttribute("list");
 i=p.size();
 
}
%>
<li><a href="${pageContext.request.contextPath }/index.jsp">退出</a></li>
<li>共有记录：<%=i %>条商品记录</li>
<form method=post action="${pageContext.request.contextPath }/UpdateProductServlet?id=<%=i+1 %>&type=insert">
<h2 style="text-align: justify;">添加商品</h2>
<label for="catelog">商品分类</label> 
                                <input list="companys" 
                                    class="form-control" editable="false" name="catelog" placeholder="请输入分类">      
                                  <datalist id="companys">
                                         <option value="手机数码">
                                         <option value="电子产品">
                                         <option value="衣服服饰">
                                         <option value="穿戴设备">
                                         <option value="生活用品">
                                         <option value="医疗药物">
                                         <option value="交通出行">
                                  </datalist>
<label for="name">商品名</label> 
                                <input type="text"
                                    class="form-control" name="name" placeholder="请输入商品名">
<label for="price">商品价格</label> 
                                <input type="text"
                                    class="form-control" name="price" placeholder="请输入价格">
 <label for="num">商品数量</label> 
                                <input type="text"
                                    class="form-control" name="num" placeholder="请输入数量">
<label for="des">商品描述</label> 
                                <input type="text"
                                    class="form-control" name="des" placeholder="请输入描述">       
<button type="submit" class="btn btn-primary" onclick="return checkForm()">添加商品</button>                                                            
</form>
<table border="1px solid #96c2f1" background="#eff7ff" align="center">
        <tr>
            <td>id</td>
            <td>商品名</td>
            <td>价格</td>
            <td>分类</td>
            <td>数量</td>
             <td>修改</td>
             <td>删除</td>
        </tr>
    <c:forEach items="${list }" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name }</td>
            <td>${product.price}</td>
            <td>${product.catelog}</td>
            <td>${product.num}</td>
            <td><a href="${pageContext.request.contextPath }/DetailServlet?id=${product.id } ">修改/查看</a></td>           
             <td>
                 <form action="${pageContext.request.contextPath }/UpdateProductServlet?id=${product.id }&type=delete" method=post>
                 <input type="submit" name="delete" value="删除商品">
                 </form>
             </td>
        </tr>
    </c:forEach>    
    </table>
	
</body>
</html>