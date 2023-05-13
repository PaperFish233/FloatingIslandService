<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页—浮游岛App后台管理系统</title>
    <!-- 引入Bootstrap样式文件 -->
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
    <style>
        .username {
            color: blue;
        }

        /* 将表格设置为居中对齐 */
        #dataTable1 td, #dataTable1 th {
            text-align: center;
            vertical-align: middle;
            border-bottom: none;

            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            max-width: 0;
            width: auto;
        }
        #dataTable2 td, #dataTable2 th {
            text-align: center;
            vertical-align: middle;
            border-bottom: none;

            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            max-width: 0;
            width: auto;
        }
        #dataTable3 td, #dataTable3 th {
            text-align: center;
            vertical-align: middle;
            border-bottom: none;

            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            max-width: 0;
            width: auto;
        }
    </style>
</head>

<body>
<%@include file="menu.jsp" %>

<div style="margin-top: 80px"></div>

<div style="text-align:center; margin-bottom: 50px;">
    <h2>欢迎管理员：<i class="username"></i>，登录浮游岛App后台管理系统！</h2>
</div>

<div class="container-fluid">
    <div class="row">
        <!-- 饼图 -->
        <div class="col-md-6">
            <div style="text-align:center; margin-bottom: 10px">
                <h4>帖子——话题分布图</h4>
            </div>
            <div class="card mb-3 shadow p-3 mb-5 bg-white rounded">
                <div class="card-body">
                    <canvas id="myChart" width="400" height="458"></canvas>
                </div>
            </div>
        </div>
        <!-- 排行榜 -->
        <div class="col-md-6">
            <div style="text-align:center; margin-bottom: 10px">
                <h4>帖子——排行榜</h4>
            </div>
            <div class="container shadow p-3 mb-5 bg-white rounded">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link active" href="#list1" data-toggle="tab">点赞榜</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#list2" data-toggle="tab">收藏榜</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#list3" data-toggle="tab">评论榜</a>
                    </li>
                </ul>

                <div class="tab-content">
                    <!-- 排行榜1 -->
                    <div class="tab-pane active" id="list1">
                        <table id="dataTable1" class="table table-striped table-bordered" style="width:100%">
                            <colgroup>
                                <col width="10%">
                                <col width="80%">
                                <col width="10%">
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col">编号</th>
                                <th scope="col">内容</th>
                                <th scope="col">点赞数</th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>

                    <!-- 排行榜2 -->
                    <div class="tab-pane" id="list2">
                        <table id="dataTable2" class="table table-striped table-bordered" style="width:100%">
                            <colgroup>
                                <col width="10%">
                                <col width="80%">
                                <col width="10%">
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col">编号</th>
                                <th scope="col">内容</th>
                                <th scope="col">收藏数</th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>

                    <!-- 排行榜3 -->
                    <div class="tab-pane" id="list3">
                        <table id="dataTable3" class="table table-striped table-bordered" style="width:100%">
                            <colgroup>
                                <col width="10%">
                                <col width="80%">
                                <col width="10%">
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col">编号</th>
                                <th scope="col">内容</th>
                                <th scope="col">评论数</th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 引入Bootstrap的JavaScript文件 -->
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
<script src="//cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
<!-- 引入 Chart.js 库 -->
<script src="https://cdn.staticfile.org/Chart.js/2.9.4/Chart.min.js"></script>

<script>
    var useraccount = sessionStorage.getItem("useraccount");
    if (useraccount) {
        var usernameLink = document.getElementsByClassName('username')[0];
        //显示登录用户名
        usernameLink.textContent = useraccount;
    }else{
        // 如果用户未登录，则跳转到登录页面
        window.location.href = "index.jsp";
    }

    $(document).ready(function () {
        $.ajax({
            url: "<%=request.getContextPath()%>/TopicServlet?action=GetNumTopic",
            success: function(response) {
                var resultdata = $.parseJSON(response);
                if (resultdata.code == 200) {
                    let dataLabels = resultdata.data.map(item => item.tname);
                    let dataValues = resultdata.data.map(item => item.tpostsnum);

                    // 颜色数组
                    var colors = ["#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0", "#9966CC", "#FF9900", "#00cc66", "#993300", "#9999FF", "#669999"];

                    // 数据和选项配置
                    var data = {
                        labels: dataLabels,
                        datasets: [{
                            label: "话题分布",
                            backgroundColor: dataValues.map((item, index) => colors[index % colors.length]),
                            data: dataValues
                        }]
                    };

                    var options = {
                        responsive: true,
                        maintainAspectRatio: false
                    };

                    // 创建和渲染饼图
                    var ctx = document.getElementById("myChart").getContext("2d");
                    var myPieChart = new Chart(ctx, {
                        type: 'pie',
                        data: data,
                        options: options
                    });

                } else {
                    alert("请求失败");
                }
            },
            error: function(xhr, status, error) {
                console.error(xhr, status, error);
            }
        });

        $('#dataTable1').DataTable({
            paging: false,
            searching: false,
            info: false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/PostsServlet?action=GetRankingPosts"
            },
            "columns": [
                {
                    "data": 'id',
                    "render": function (data, type, row, meta) {
                        return meta.row + 1; // 从 1 开始编号
                    }
                },
                {"data": "content"},
                {"data": "likenum"}
            ],
            "columnDefs": [
                {
                    "targets": [1],
                    "className": "text-left"
                }
            ],
        });

        $('#dataTable2').DataTable({
            paging: false,
            searching: false,
            info: false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/PostsServlet?action=GetRankingCollectionPosts"
            },
            "columns": [
                {
                    "data": 'id',
                    "render": function (data, type, row, meta) {
                        return meta.row + 1; // 从 1 开始编号
                    }
                },
                {"data": "content"},
                {"data": "likenum"}
            ],
            "columnDefs": [
                {
                    "targets": [1],
                    "className": "text-left"
                }
            ],
        });

        $('#dataTable3').DataTable({
            paging: false,
            searching: false,
            info: false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/PostsServlet?action=GetRankingCommentPosts"
            },
            "columns": [
                {
                    "data": 'id',
                    "render": function (data, type, row, meta) {
                        return meta.row + 1; // 从 1 开始编号
                    }
                },
                {"data": "content"},
                {"data": "likenum"}
            ],
            "columnDefs": [
                {
                    "targets": [1],
                    "className": "text-left"
                }
            ],
        });


    });

</script>
</body>
</html>