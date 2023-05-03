<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <title>登录—浮游岛App后台管理系统</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
          integrity="sha512-JmnXZ0AnJpKjVBqPivH/LAXtk+mxztRZeokV7K/cBf50uVXHo9nGYpNUg7q3yPxBwzLHDBhbPhr5v5c5I1Xd5Q=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/bootstrap/4.6.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/popper.js/2.9.3/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap/4.6.0/js/bootstrap.min.js"></script>
</head>
<body class="bg-light">

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8">
            <div class="card shadow-sm">
                <div class="card-header bg-white text-center">
                    <h4 class="mb-0">浮游岛App后台管理系统</h4>
                    <small>登录</small>
                </div>
                <div class="card-body">
                    <form name="form" method="post" action="UsersServlet?action=Login">
                        <div class="form-group">
                            <label for="uaccount">账号：</label>
                            <input type="text" name="uaccount" id="uaccount" class="form-control"
                                   placeholder="请输入账号" required>
                        </div>
                        <div class="form-group">
                            <label for="upassword">密码：</label>
                            <input type="password" name="upassword" id="upassword" class="form-control"
                                   placeholder="请输入密码" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">登录</button>
                        <button type="reset" class="btn btn-light btn-block">重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $('form').submit(function () {
            var uaccount = $('input[name=uaccount]').val();
            var upassword = $('input[name=upassword]').val();
            var url = "UsersServlet?action=LoginAdmin&uaccount=" + uaccount + "&upassword=" + upassword;
            $.get(url, function (result) {
                result = JSON.parse(result);
                if (result.code == 200) {
                    if (result.message == "登录成功") {
                        sessionStorage.setItem("useraccount", uaccount);
                        window.location.href = "main.jsp";
                    } else {
                        $('#upassword').val('');
                        var alert = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                            result.message + '，请检查用户名或密码' + '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                            '<span aria-hidden="true">&times;</span></button>' +
                            '</div>';
                        $('.card-body').prepend(alert);
                    }
                } else {
                    alert("请求失败");
                }
            });
            return false;
        });
    });
</script>

</body>
</html>