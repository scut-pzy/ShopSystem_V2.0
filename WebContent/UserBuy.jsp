<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.*" %>
<%@ page language="java" import="Dao.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
List<BuyBean> list=new ArrayList<BuyBean>();
buyDao dao=new buyDao();
list=dao.findBuy();
request.setAttribute("userbuy", list);
int i=0;
if(list!=null){
     i=list.size();
}
%>
<html>
<head>
<meta charset="GB18030">
</head>
<body>
<li>共有记录：<%=i%>条购买记录</li>
<table border="1px solid #96c2f1" background="#eff7ff" align="center">
        <tr>
             <td>用户名</td>
            <td>商品名</td>
             <td>数量</td>
            <td>价格</td>
            <td>购买时间</td>
            <td>销售人员id</td>
        </tr>
    <c:forEach items="${userbuy }" var="h">
        <tr>
            <td>${h.username}</td>
            <td>${h.pname }</td>
              <td>${h.pnum}</td>
            <td>${h.sumprice}</td>
            <td>${h.date}</td>
            <td>${h.sid}</td>
        </tr>
    </c:forEach>    
    </table>
</body>
</html>