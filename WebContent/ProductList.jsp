
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.BuyBean" %>
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
<body>
<%
int i=0;
int j=0;
if(request.getSession().getAttribute("sid")!=null){
	List<ProductBean>  p=null;
	List<BuyBean> buylist=null;
	request.getSession().setAttribute("usertype", "saler");
	String sid=null;
	 p=(List<ProductBean>)request.getSession().getAttribute("list");
	sid=request.getSession().getAttribute("sid").toString();
	List<BuyBean> list=new ArrayList<BuyBean>();
	buylist=(List<BuyBean>)request.getSession().getAttribute("buylist");
	i=buylist.size();
	j=p.size();
	request.setAttribute("list",  p);
	request.setAttribute("buylist", buylist);
}
else{
	response.sendRedirect(request.getContextPath()+"/Login.jsp");
}
%>
<li><a href="${pageContext.request.contextPath }/Login.jsp">退出</a></li>

<form method=post action="${pageContext.request.contextPath }/UpdateProductServlet?type=insert">
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
<li>共有记录：<%=j %>条商品记录</li>
        <tr>
            <td>id</td>
            <td>商品名</td>
            <td>价格</td>
            <td>分类</td>
            <td>数量</td>
            <td>状态</td>
             <td>更新</td>
             <td>状态操作</td>
        </tr>
    <c:forEach items="${list }" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name }</td>
            <td>${product.price}</td>
            <td>${product.catelog}</td>
            <td>${product.num}</td>
            <td>${product.statue}</td>
            <td><a href="${pageContext.request.contextPath }/DetailServlet?nowid=${product.id } ">修改/查看</a></td>           
             <td>
                 <form action="${pageContext.request.contextPath }/UpdateProductServlet?nowid=${product.id }&type=delete" method=post>
                 <c:if test="${product.statue  =='sell'}"> 
                 <input type="submit" name="delete" value="下架商品">
                 </c:if>
                 <c:if test="${product.statue  =='off'}"> 
                 <input type="text"  value="已下架"  readonly="true">
                 </c:if>
                 </form>
             </td>
        </tr>
    </c:forEach>    
    </table>
	
<table border="1px solid #96c2f1" background="#eff7ff" align="center">
<li>共有记录：<%=i%>条购买记录</li>
        <tr>
             <td>购买用户</td>
            <td>购买商品</td>
            <td>商品单价（元）</td>
            <td>购买数量</td>
            <td>付款总额（元）</td>
            <td>购买时间</td>
        </tr>
    <c:forEach items="${buylist }" var="h">
        <tr>
            <td>${h.username}</td>
            <td>${h.pname }</td>
            <td>${h.price}</td>
            <td>${h.pnum}</td>
            <td>${h.sumprice}</td>
            <td>${h.date}</td>
        </tr>
    </c:forEach>    
    </table>
</body>
</html>