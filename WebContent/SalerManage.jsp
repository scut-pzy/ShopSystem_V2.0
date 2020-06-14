<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.*" %>
<%@ page language="java" import="Dao.*" %>

<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
  <style>
  table
        {
            border-collapse: collapse;
            margin: 0 auto;
            margin-top:100px;
            text-align: center;
        }
        table td, table th
        {
            border: 1px solid #cad9ea;
            color: #666;
            height: 30px;
        }

        table thead th
        {
            background-color: #CCE8EB;
            width: 100px;
        }
        table tr:nth-child(odd)
        {
            background: #fff;
        }
        table tr:nth-child(even)
        {
            background: #F5FAFA;
        }
  </style>
 <script type="text/javascript">
function beforeSubmit(form){
	if(form.pass.value==''){
	alert('�û�������Ϊ�գ�');
	form.username.focus();
	return false;
  }
}
</script>
<%
String  manaer=request.getSession().getAttribute("manager").toString();
if(manaer !=null){
	SalerBean saler  =null;
	int i=0;	
	UserDao dao=new UserDao();
	List<SalerBean> list=new ArrayList<SalerBean>();
	list=(List<SalerBean>)dao.findAllSaler();
	//System.out.print(   list.size());
	request.getSession().setAttribute("salerlist",list);
}
else{
		response.sendRedirect(request.getContextPath()+"/ManagerLogin.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>������Ա����</title>
</head>
<body>
<c:if test="${not empty manager }">

<table border="1px solid #96c2f1" background="#eff7ff" align="center" >
	<tr>
		     <td>������Ա���</td>
            <td>������Ա�˺�</td>
            <td>�˺ŵ�¼����</td>
            <td>����</td>
            <td>�绰</td>
             <td>��ǰ״̬</td>
             <td> �����</td>
             <td>���� 1</td>
             <td>����2</td>
             <td><a  href="SalerRegister.jsp"><button type="button" class="btn btn-primary" >������Աע��</button> </a>	</td>
	</tr>
		<c:forEach items="${salerlist }" var="saler">
		<tr>
            <td>${saler.uid}</td>
            <td>${saler.username}</td>
            <td>${saler.pass}</td>
            <td>${saler.email}</td>     
            <td>${saler.phone_number}</td>
            <td>
            	<c:if test="${saler.statue=='alive'}">��Ծ</c:if>
            	<c:if test="${saler.statue=='freeze'}">����</c:if>
            </td>
           	 <form action="${pageContext.request.contextPath }/ControlSalerServlet?sid=${saler.uid}&&type=update" method=post>
            		<td> 
            			<input type="text"   id="pass" name="pass" placeholder="������������"  onSubmit="return beforeSubmit(this);"></input>
            		</td>
            
             		<td>
             			<button type="submit" class="btn btn-primary">�޸�����</button> 
            		 </td>
             </form>
             <c:if test="${saler.statue=='alive'}">
             	 <form action="${pageContext.request.contextPath }/ControlSalerServlet?type=freeze&&sid=${saler.uid}" method=post>
             		     <td>
             		            <button type="submit" class="btn btn-primary">�����˻�</button> 
             		      </td>
            	 </form>
             </c:if>
            <c:if test="${saler.statue=='freeze'}">
             	 <form action="${pageContext.request.contextPath }/ControlSalerServlet?type=thaw&&sid=${saler.uid}" method=post>
             		     <td>
             		            <button type="submit" class="btn btn-primary">�ⶳ�˻�</button> 
             		      </td>
            	 </form>
             </c:if>
             </tr>
       </c:forEach>
</table>


</c:if>
</body>
</html>