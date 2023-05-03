<%@ page language="java" import="java.util.*,com.example.entity.*" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>帖子管理—浮游岛App后台管理系统</title>
    <!-- 引入Bootstrap样式文件 -->
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
    <style>
        /* 将表格设置为居中对齐 */
        #dataTable td, #dataTable th {
            text-align: center;
            vertical-align: middle;
            border-bottom: none;
        }
    </style>
</head>

<body>
<%@ include file="menu.jsp" %>

<div class="container-fluid my-3">
    <div style="margin-top: 70px"></div>

    <table id="dataTable" class="table table-striped table-bordered" style="width:100%">
        <colgroup>
            <col width="5%">
            <col width="15%">
            <col width="25%">
            <col width="10%">
            <col width="10%">
            <col width="10%">
            <col width="5%">
            <col width="5%">
            <col width="10%">
            <col width="5%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">编号</th>
            <th scope="col">帖子资源</th>
            <th scope="col">帖子内容</th>
            <th scope="col">帖子话题</th>
            <th scope="col">发布用户</th>
            <th scope="col">发布日期</th>
            <th scope="col">点赞数</th>
            <th scope="col">收藏数</th>
            <th scope="col">评论数</th>
            <th scope="col">操作</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>

<%--  删除模态框  --%>
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">删除帖子</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>

            <form id="deleteForm" action="PostsServlet?action=DeletePostsAdmin" method="post">
                <input type="hidden" name="fromModal" value="deleteposts">

                <div class="modal-body">

                    <div class="form-group" style="display: none;">
                        <label for="deleteid">id：</label>
                        <input type="text" class="form-control" id="deleteid" name="deleteid" required>
                    </div>

                    <div class="form-group">
                        <label>确定要删除该条数据吗？</label>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-light" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">确定</button>
                </div>
            </form>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--  提示模态框  --%>
</div><div class="modal fade" id="toastModal" tabindex="-1" role="dialog" aria-labelledby="toastModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="toastmessage"></label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<!-- 引入Bootstrap和jQuery的JavaScript文件 -->
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
<script src="//cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>

<script>
    var useraccount = sessionStorage.getItem("useraccount");
    if (!useraccount) {
        // 如果用户未登录，则跳转到登录页面
        window.location.href = "index.jsp";
    }

    $(document).ready(function () {
        $('#dataTable').DataTable({
            "ajax": {
                "url": "<%=request.getContextPath()%>/PostsServlet?action=GetPostsAdmin"
            },
            "columns": [
                {"data": "pid"},
                {
                    "data": "imageurl",
                    "render": function(data, type, row) {
                        let url = '';
                        if (data && data.imageurl) { // 先判断 data 中是否有 imageurl 字段
                            url = data.imageurl;
                        } else if (row.imageurl) { // 如果 data 中没有，则从 row 中获取
                            url = row.imageurl;
                        }
                        if (url) {
                            let ext = url.split('.').pop().toLowerCase();
                            if (['jpg', 'gif', 'png'].includes(ext)) {
                                return '<img src="' + url + '" style="height: 150px; max-width:100%; width: auto; object-fit: contain;"/>';
                            } else if (['mp4', 'flv'].includes(ext)) {
                                return '<video src="' + url + '" style="height: 150px; max-width:100%; width: 200px; object-fit: contain;" controls></video>';
                            }else{
                                return '无';
                            }
                        }
                        return '无'; // 如果获取失败则返回空字符串
                    }
                },
                {"data": "content"},
                {"data": "topicname"},
                {"data": "uaccount"},
                {"data": "date"},
                {
                    "data": "likenum",
                    "render": function (data, type, row) {
                        return '<a style="display: inline-block; transform: skewX(-15deg); font-size: 18px;">' + data + '</a>';
                    }
                },
                {
                    "data": "collectionnum",
                    "render": function (data, type, row) {
                        return '<a style="display: inline-block; transform: skewX(-15deg); font-size: 18px;">' + data + '</a>';
                    }
                },
                {
                    "data": "commentnum",
                    "render": function (data, type, row) {
                        return '<a href="postscommentall.jsp?cpostsid=' + row.pid + '" style="display: inline-block; transform: skewX(-15deg); font-size: 18px;">' + data + '（进入管理）</a>';
                    }
                },
                {
                    "data": null,
                    "defaultContent": "<button class='delete-btn btn btn-danger btn-sm'>删除</button>"
                }
            ],
            "pagingType": "full",
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 条结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 条结果，共 _TOTAL_ 条",
                "sInfoEmpty": "显示第 0 至 0 条结果，共 0 条",
                "sInfoFiltered": "",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "columnDefs": [
                {
                    "targets": [2],
                    "className": "text-left"
                }
            ],
        });

        // 监听模态框弹出和关闭事件，禁止改变滚动条宽度
        $('body').on('show.bs.modal', '.modal', function () {
            $('body').css('overflow', 'hidden');
        });
        $('body').on('hidden.bs.modal', '.modal', function () {
            $('body').css('overflow', 'auto');
        });

        //删除按钮的点击事件监听
        $(document).on("click", ".delete-btn", function () {
            //获取所在行
            var tr = $(this).closest("tr");
            //获取该行数据
            var rowData = $('#dataTable').DataTable().row(tr).data();
            $('#deleteid').val(rowData.pid);
            $('#deleteModal').modal('show'); // 弹出提示框
        });

        // 监听删除表单的提交事件
        $('#deleteForm').submit(function(event) {
            // 阻止表单的默认提交行为
            event.preventDefault();
            // 获取表单数据
            var deleteformData = $(this).serialize();
            // 发送 AJAX 请求
            $.ajax({
                url: $(this).attr('action'),
                type: $(this).attr('method'),
                data: deleteformData,
                success: function(response) {
                    var resultdata = $.parseJSON(response);
                    if (resultdata.code == 200) {
                        $('#toastmessage').text(resultdata.message); // 显示提交状态信息
                        // 关闭第一个模态框的时候，将 body 的 overflow 设置为 hidden
                        $('#deleteModal').on('hidden.bs.modal', function () {
                            $('body').css('overflow', 'hidden');
                        });
                        // 关闭第一个模态框并等待一段时间后再进行后续操作
                        $('#deleteModal').modal('hide');
                        setTimeout(function() {
                            // 将 body 的 overflow 设置为 auto
                            $('body').css('overflow', 'auto');
                            // 打开第二个模态框并显示提示框
                            $('#toastModal').modal('show');
                        }, 500);
                        // 打开第二个模态框之后，将 body 的 overflow 设置为 auto
                        $('#toastModal').on('shown.bs.modal', function () {
                            $('body').css('overflow', 'auto');
                        });
                        // 关闭模态框并刷新页面
                        $('#toastModal').on('hidden.bs.modal', function () {
                            $('#dataTable').DataTable().ajax.reload();
                        });
                    }else{
                        alert("请求失败");
                    }
                },
                error: function(xhr, status, error) {
                    console.error(xhr, status, error);
                }
            });
        });

    });
</script>
</body>
</html>