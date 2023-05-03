<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%-- 获取当前请求的URL --%>
<%
    String requestUrl = request.getRequestURI();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>导航条—浮游岛App后台管理系统</title>
    <!-- 引入 Bootstrap 样式文件 -->
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- 引入 jQuery 和 Bootstrap JavaScript 文件 -->
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand">浮游岛App后台管理系统</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item <%= requestUrl.contains("main.jsp") ? "active" : "" %>">
                    <a class="nav-link" href="main.jsp">首页</a>
                </li>
                <li class="nav-item <%= requestUrl.contains("noticeall.jsp") ? "active" : "" %>">
                    <a class="nav-link" href="noticeall.jsp">资讯管理</a>
                </li>
                <li class="nav-item <%= requestUrl.contains("postsall.jsp") ? "active" : "" %>">
                    <a class="nav-link" href="postsall.jsp">帖子管理</a>
                </li>
                <li class="nav-item <%= requestUrl.contains("postscommentall.jsp") ? "active" : "" %>">
                    <a class="nav-link" href="#" id="notoast">评论管理</a>
                </li>
                <li class="nav-item <%= requestUrl.contains("topicall.jsp") ? "active" : "" %>">
                    <a class="nav-link" href="topicall.jsp">话题管理</a>
                </li>
                <li class="nav-item <%= requestUrl.contains("usersall.jsp") ? "active" : "" %>">
                    <a class="nav-link" href="usersall.jsp">用户管理</a>
                </li>
                <li class="nav-item <%= requestUrl.contains("versionall.jsp") ? "active" : "" %>">
                    <a class="nav-link" href="versionall.jsp">版本管理</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#" id="quit">退出账号</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%--notoast提示模态框--%>
<div class="modal fade" id="notoastModal" tabindex="-1" role="dialog" aria-labelledby="notoastModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <p>请从帖子管理页面中跳转至某一条帖子的评论列表！</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">取消</button>
                <a type="button" class="btn btn-primary" href="postsall.jsp">确认</a>
            </div>
        </div>
    </div>
</div>

<%--退出账号模态框--%>
<div class="modal fade" id="quitModal" tabindex="-1" role="dialog" aria-labelledby="quitModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">退出账号</h4>
            </div>
            <div class="modal-body">
                <p>是否确认退出当前账号？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="startquit">确认</button>
            </div>
        </div>
    </div>
</div>

<script>
    var useraccount = sessionStorage.getItem("useraccount");
    if (!useraccount) {
        // 如果用户未登录，则跳转到登录页面
        window.location.href = "index.jsp";
    }

    $(document).ready(function () {

        // 点击退出账号后显示模态框
        $("#notoast").click(function (e) {
            e.preventDefault(); // 阻止默认事件
            $("#notoastModal").modal('show'); // 弹出模态框
        });

        // 点击退出账号后显示模态框
        $("#quit").click(function (e) {
            e.preventDefault(); // 阻止默认事件
            $("#quitModal").modal('show'); // 弹出模态框
        });

        $("#startquit").click(function () {
            // 当弹出框中的“确认”按钮被点击时执行的操作
            window.location.href = "index.jsp";
            // 清除 sessionStorage 中的 useraccount 和 usernickname 数据
            sessionStorage.removeItem("useraccount");
            sessionStorage.removeItem("usernickname");
            $('#quitModal').modal('hide');
        });
    });
</script>
</body>
</html>