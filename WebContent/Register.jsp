<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
<title>注册</title>
</head>
<body>
<div class="container">
        <div class="row" style="margin-top: 30px">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-user"></span>注册
                    </div>
                    <div class="panel-body">
                    
                        <form method="post" action="${pageContext.request.contextPath }/RegisterServlet?cancer=user" name="RegisterForm" onSubmit="return login(this);">
                            <div class="form-group">
                                <label for="name">用户名</label> 
                                <input type="text"
                                    class="form-control" name="username" placeholder="请输入用户名">
                            </div>
                            <div class="form-group">
                                <label for="">密码</label>
                                 <input type="password"
                                    class="form-control" name="password1" placeholder="请输入密码">
                            </div>
                            <div class="form-group">
                                <label for="">再次输入密码</label>
                                 <input type="password"
                                    class="form-control" name="password2" placeholder="请再次输入密码">
                            </div>
                            <div class="form-group">
                                <label for="name">电话号码</label> 
                                <input type="text"
                                    class="form-control" name="phone" placeholder="请输入电话">
                            </div>
                            <div class="form-group">
                                <label for="name">邮箱</label> 
                                <input type="text"
                                    class="form-control" name="email" placeholder="请输入邮箱">
                            </div>
                            <button type="submit" class="btn btn-primary" >点击注册 </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
     </div>
</body>
<script type="text/javascript">
    function login(form) {
        if (form.username.value == "") {
            alert("ID不能为空！");
            return false;
        }
        if (form.password1.value == "") {
            alert("密码不能为空！");
            return false;
        }

        if (form.password1.value != form.password2.value) {
            alert("两次输入密码不一致!!!!！");
            return false;
        }

    }
</script>
</html>