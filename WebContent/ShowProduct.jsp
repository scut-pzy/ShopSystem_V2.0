<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="Bean.*" %>
<%@ page language="java" import="Dao.*" %>
<link rel="stylesheet" type="text/css" href=/ShopSystem/css/detail.css> 
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>商品展示</title>
<style>
            .fk {
                width: 30px;
                margin-bottom: 10px;
                height: 30px;
                padding: 0px 5px;
                display: inline-block;
            }
        </style>
 <script>
           function numPlus() {
        	   var p = document.getElementById('num')
        	   var a = document.getElementById('sum');
        	   var p1 = document.getElementById('num1');
        	   if(parseInt(p.value)<parseInt(a.innerHTML))
        	   {p.value++;}
        	   p1.innerHTML=String(p.value)
           }
           function numincres() {
        	   var p1 = document.getElementById('num1');
               var p = document.getElementById('num');
               if(parseInt(p.value)>1)
            	   {p.value--;}
               p1.innerHTML=p.value
          }
           
        </script>
</head>
<%

ProductBean product=null;
UserBean user=null;
ProductDao dao=new ProductDao();
String id=request.getParameter("id");
user=(UserBean)request.getSession().getAttribute("user");
try{
if(user!=null){
String username=user.getUsername();
product=(ProductBean)dao.finDetailProduct(id);
request.setAttribute("product", product);
internetHistory hdao=new internetHistory();
hdao.insert(username,product);

 String catelog=product.getCatelog();
if(catelog.equals("手机数码")) {catelog="1";}
else if(catelog.equals("电子产品")) {catelog="2";}
else if(catelog.equals("衣服服饰")) {catelog="3";}
else if(catelog.equals("穿戴设备")) {catelog="4";}
else if(catelog.equals("生活用品")) {catelog="5";}
else if(catelog.equals("医疗药物")) {catelog="6";}
else if(catelog.equals("交通出行")) {catelog="7";}
if(request.getParameter("tila")!=""){
String lasttime=request.getParameter("tila");
System.out.print(lasttime);
String lastname=request.getSession().getAttribute("lastname").toString();
System.out.print(lastname);
String lastcatelog=request.getSession().getAttribute("lastcatelog").toString();
String lastprice=request.getSession().getAttribute("lastprice").toString();
internetHistory historydao=new internetHistory();
historydao.insert(username, lastname, lastcatelog, lasttime, lastprice);
}
String name=product.getName();
String price=product.getPrice();
request.getSession().setAttribute("lastname", name);
request.getSession().setAttribute("lastcatelog",catelog );
request.getSession().setAttribute("lastprice", price);
}
}
catch(Exception e){
	
}

%>
<body>
<c:if test="${empty user }">
<a href="Login.jsp"><button class="ok">登录</button></a>
<a href="Register.jsp"><button class="yes">注册</button></a>
</c:if>
<div>
<c:if test="${not empty product }">
<form action="${pageContext.request.contextPath}/viewproduct.jsp" method="post">
<button class="yes" type="submit" > 返回</button>
<input class="fk"  type="text" id="timelast" name="timelast" style="display:none" />
</form>
</c:if>
<c:if test="${empty product }">

</c:if>
<p>
    <div class="box7">
<img alt="" src="">
</img>
    图片
    </div>
</p>
 <div class="container "> 
             <div class="mui-row mui-col-xs-12 mui-clearfix">
                 <span class="mui-col-xs-6">
                     <p class="red"> 商品名称:${product.name}</p>
                     <p class="red"> 商品分类:${product.catelog}</p>
                     <p class="red"> 商品价格:${product.price}</p>
                     <p class="red" >  商品库存:<p class="red" id="sum">  ${product.num}</p>                    
                     <p class="red">  商品描述:${product.des}</p>

                 </span>
             </div>
             <c:if test="${not empty user }">
    <form method=post action="${pageContext.request.contextPath}/cartServlet">
                        <span>
                        <p>购买数量：</p>
                        <div>
                        <input class="fk" type="button"  value="-" onclick="numincres ()" />
                        <input name="pid" type="hidden" value= <%=id %>></input>
                        <input name="pname" type="hidden" value= "${product.name}"></input>
                        <%String catelog=product.getCatelog();
                        if(catelog.equals("手机数码")) {catelog="1";}
                        else if(catelog.equals("电子产品")) {catelog="2";}
                        else if(catelog.equals("衣服服饰")) {catelog="3";}
                        else if(catelog.equals("穿戴设备")) {catelog="4";}
                        else if(catelog.equals("生活用品")) {catelog="5";}
                        else if(catelog.equals("医疗药物")) {catelog="6";}
                        else if(catelog.equals("交通出行")) {catelog="7";} %>
                        <input name="pcatelog" type="hidden" value= "<%=catelog%>"></input>
                        <p class="fk" id="num1"    type="button" >0</p>
                        <input class="fk" name="pid" value=${product.id}  type="hidden" ></input>
                         <input class="fk" id="num"  name="pnum" value="1"  type="hidden" ></input>
                         <input class="fk" type="button"  value="+" onclick="numPlus()" />
                         </div>
                        </span>
                        
                     
     <c:if test="${not buy}">
          <input type="submit" class="mui-col-xs-6" value="加入购物车">
      </c:if>
       <c:if test="${buy}">
          <input type="button" class="mui-col-xs-6" value="已经添加">
      </c:if>     
    </form>
</c:if>
</div>
</div>
</body>
</html>
<script type="text/javascript">
<!--

//-->

function statisticsStay(){
    console.log(localStorage.getItem('testSecond'));
    var second = 0;
    var p=document.getElementById("timelast");
    
    //开启定时器记录页面停留时间
    var timer = setInterval(function(){
        second++;
    },1000);

    //页面刷新、关闭时触发onbeforeunload事件把停留时间记录到localStorage
    window.onbeforeunload = function(){ 
        localStorage.setItem('testSecond',second);
        p.value=localStorage.getItem('testSecond');
    };
}
statisticsStay();
</script>
