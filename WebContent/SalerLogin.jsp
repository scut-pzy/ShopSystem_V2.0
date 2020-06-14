<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row" style="margin-top: 30px">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-user"></span>登录
                    </div>
                    <div class="panel-body">            
                        <form action="${pageContext.request.contextPath}/salerloginServlet"  method="post" name="LoginForm">
                            <div class="form-group">
                                <label for="username">用户名</label> 
                                <input type="text"
                                    class="form-control" name="username" placeholder="请输入用户名">
                            </div>
                            <div class="form-group">
                                <label for="">密码</label>
                                 <input type="password"
                                    class="form-control" name="password" placeholder="请输入密码">
                            </div>
                             <div class="form-group">
                                <label for="autologin">一小时内自动登录</label>
                                 <input type="checkbox"
                                    class="form-control" name="autologin" >
                            </div>
                            <div style="padding-top: 10px; margin-left: 25%;">
                            <button type="submit" class="btn btn-primary" onclick="return checkForm()">销售登录</button>
                         </div>
                        </form>           
                    </div>
                </div>
            </div>
        </div>
         <div >
        <p aligen="center" id="time">当前时间:
  <!-- 显示当前时间 -->
 <script language=JavaScript>
        function gettime(){
        	var d = new Date();
        	document.getElementById("time").innerHTML ="当前时间；"+d;
        	window.setTimeout("gettime()",1000);
        	}
        	window.onload = gettime;
</script>
        </p>
    </div>
    </div>
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous">
    
</script>
<script type="text/javascript">
    function checkForm() {
        var name = loginForm.name.value;
        var pwd = loginForm.pwd.value;
        var re = /[a-zA-Z0-9]/;
        function match(str) {
        	var reg = {letter:"^[A-Za-z]+$",num:"^([+-]?)\\d*\\.?\\d+$"};
        	if((new RegExp(reg.letter)).test(str) || (new RegExp(reg.num)).test(str)) {
        	return true;
        	}esle {
        	return false;
        	}
        	} 
        if (name == "" || name == null) {
            alert("请输入用户名");
            loginForm.name.focus();
            return false;
        }

        if(name.length<6){
        	alert("请输入至少6位账号")
        	 loginForm.name.focus();
        	return false;
        }
        if (pwd == "" || pwd == null) {
            alert("请输入密码");
            loginForm.pwd.focus();
            return false;
        }
        return true;
    }
</script>
   
</body>
</html>