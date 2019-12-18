<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.*" %>
<%@ page language="java" import="Dao.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
</head>
<%
List<HistoryBean> list=new ArrayList<HistoryBean>();
internetHistory dao=new internetHistory();
list=dao.findHistory();
request.setAttribute("history", list);
int i=0;
if(list!=null){
     i=list.size();
}
%>
<body>
<li>共有记录：<%=i%>条商品浏览记录</li>
<table border="1px solid #96c2f1" background="#eff7ff" align="center">
        <tr>
             <td>用户名</td>
            <td>商品名</td>
            <td>价格</td>
            <td>分类</td>
            <td>浏览时间</td>
        </tr>
    <c:forEach items="${history }" var="h">
        <tr>
            <td>${h.username}</td>
            <td>${h.name }</td>
            <td>${h.price}</td>
            <td>${h.catelog}</td>
            <td>${h.date}</td>
        </tr>
    </c:forEach>    
    </table>
</body>
</html>