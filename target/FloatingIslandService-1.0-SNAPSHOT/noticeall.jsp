<%@ page language="java" import="java.util.*,com.example.entity.*" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>资讯管理—浮游岛App后台管理系统</title>
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
    <div style="margin-top: 60px"></div>
    <div class="row">
        <div class="col-md-12" style="margin-bottom: 10px">
            <a class="btn btn-primary mt-2" id="addBtn">发布资讯</a>
        </div>
    </div>

    <table id="dataTable" class="table table-striped table-bordered" style="width:100%">
        <colgroup>
            <col width="5%">
            <col width="20%">
            <col width="50%">
            <col width="5%">
            <col width="10%">
            <col width="10%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">编号</th>
            <th scope="col">资讯图片</th>
            <th scope="col">资讯内容</th>
            <th scope="col">发布者</th>
            <th scope="col">发布日期</th>
            <th scope="col">操作</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>

<%--  发布模态框  --%>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">发布资讯</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>

            <form id="addForm" action="NoticeServlet?action=InsertNotice" method="post">
                <input type="hidden" name="fromModal" value="addnotice">

                <div class="modal-body">

                    <div class="form-group">
                        <label>上传图片：</label>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" name="file" accept="image/*">
                            <label class="custom-file-label">选择文件</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nimageurl">资讯图片：</label>
                        <input type="text" class="form-control" id="nimageurl" name="nimageurl" placeholder="自动获取图片链接" required>
                    </div>

                    <div class="form-group">
                        <label for="ncontent">资讯内容：</label>
                        <textarea class="form-control" id="ncontent" name="ncontent" maxlength="1000" placeholder="请输入资讯内容" required></textarea>
                    </div>

                    <div class="form-group">
                        <label for="naccount">发布者：</label>
                        <input type="text" class="form-control" id="naccount" name="naccount" placeholder="请输入发布用户" required readonly>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-light" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </form>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--  更新模态框  --%>
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">更新资讯</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>

            <form id="updateForm" action="NoticeServlet?action=UpdateNotice" method="post">
                <input type="hidden" name="fromModal" value="updatenotice">

                <div class="modal-body">

                    <div class="form-group" style="display: none;">
                        <label for="updateid">id：</label>
                        <input type="text" class="form-control" id="updateid" name="updateid" required>
                    </div>

                    <div class="form-group">
                        <label>上传图片：</label>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" name="file" accept="image/*">
                            <label class="custom-file-label">选择文件</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nimageurl1">资讯图片：</label>
                        <input type="text" class="form-control" id="nimageurl1" name="nimageurl1" placeholder="自动获取图片链接" required>
                    </div>

                    <div class="form-group">
                        <label for="ncontent1">资讯内容：</label>
                        <textarea class="form-control" id="ncontent1" name="ncontent1" maxlength="1000" placeholder="请输入资讯内容" required></textarea>
                    </div>

                    <div class="form-group">
                        <label for="naccount1">更新者：</label>
                        <input type="text" class="form-control" id="naccount1" name="naccount1" placeholder="请输入更新用户" required readonly>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-light" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </form>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--  删除模态框  --%>
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">删除资讯</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>

            <form id="deleteForm" action="NoticeServlet?action=DeleteNotice" method="post">
                <input type="hidden" name="fromModal" value="deletenotice">

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
                "url": "<%=request.getContextPath()%>/NoticeServlet?action=GetAllNotice"
            },
            "columns": [
                {"data": "nid"},
                {
                    "data": "nimageurl",
                    "render": function (data, type, row) {
                        return '<img src="' + data + '" style="height: 200px; max-width:100%; width: auto; object-fit: contain;"/>';
                    }
                },
                {"data": "ncontent"},
                {"data": "naccount"},
                {"data": "ndate"},
                {
                    "data": null,
                    "defaultContent": "<button class='edit-btn btn btn-primary btn-sm' style='margin-right: 5px'>更新</button><button class='delete-btn btn btn-danger btn-sm'>删除</button>"
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

        //上传文件
        var fileInputs = document.querySelectorAll('.custom-file-input');

        for (var i = 0; i < fileInputs.length; i++) {
            var fileInput = fileInputs[i];

            fileInput.addEventListener('change', function(event) {
                var fileName = event.target.value.split('\\').pop();
                var fileLabel = event.target.nextElementSibling;
                fileLabel.innerHTML = fileName;

                var file = event.target.files[0];
                var formData = new FormData();
                formData.append('file', file);
                formData.append('upload', 'file');

                var xhr = new XMLHttpRequest();
                xhr.open("POST", "FileUploadServlet");
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var savePath = xhr.responseText;
                        // $('#nimageurl').val('http://172.16.44.101:8080' + savePath);
                        // $('#nimageurl1').val('http://172.16.44.101:8080' + savePath);
                        $('#nimageurl').val(window.location.origin + savePath);
                        $('#nimageurl1').val(window.location.origin + savePath);
                        //window.location.origin 获取当前地址与端口
                    }
                };
                xhr.send(formData);
            });
        }

        // 点击发布按钮后显示模态框
        $('#addBtn').click(function () {
            $('#naccount').val(useraccount); // 设置用户账号
            $('#addModal').modal('show');
        });

        // 监听模态框弹出和关闭事件，禁止改变滚动条宽度
        $('body').on('show.bs.modal', '.modal', function () {
            $('body').css('overflow', 'hidden');
        });
        $('body').on('hidden.bs.modal', '.modal', function () {
            $('body').css('overflow', 'auto');
        });

        //修改、删除按钮的点击事件监听
        $(document).on("click", ".edit-btn", function () {
            //获取所在行
            var tr = $(this).closest("tr");
            //获取该行数据
            var rowData = $('#dataTable').DataTable().row(tr).data();
            $('#updateid').val(rowData.nid);
            $('#nimageurl1').val(rowData.nimageurl);
            $('#ncontent1').val(rowData.ncontent);
            $('#naccount1').val(useraccount); // 设置用户账号
            $('#updateModal').modal('show'); // 弹出提示框
        });

        $(document).on("click", ".delete-btn", function () {
            //获取所在行
            var tr = $(this).closest("tr");
            //获取该行数据
            var rowData = $('#dataTable').DataTable().row(tr).data();
            $('#deleteid').val(rowData.nid);
            $('#deleteModal').modal('show'); // 弹出提示框
        });

        // 监听新增表单的提交事件
        $('#addForm').submit(function(event) {
            // 阻止表单的默认提交行为
            event.preventDefault();
            // 获取表单数据
            var addformData = $(this).serialize();
            // 发送 AJAX 请求
            $.ajax({
                url: $(this).attr('action'),
                type: $(this).attr('method'),
                data: addformData,
                success: function(response) {
                    var resultdata = $.parseJSON(response);
                    if (resultdata.code == 200) {
                        $('#toastmessage').text(resultdata.message); // 显示提交状态信息
                        // 关闭第一个模态框的时候，将 body 的 overflow 设置为 hidden
                        $('#addModal').on('hidden.bs.modal', function () {
                            $('body').css('overflow', 'hidden');
                        });
                        // 关闭第一个模态框并等待一段时间后再进行后续操作
                        $('#addModal').modal('hide');
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
                            // 重置所有上传标签为“选择文件”
                            var allFileLabels = document.querySelectorAll('.custom-file-label');
                            for (var j = 0; j < allFileLabels.length; j++) {
                                allFileLabels[j].innerHTML = '选择文件';
                            }
                            $('#ncontent').val('');
                            $('#nimageurl').val('');
                            $('#nimageurl1').val('');
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

        // 监听更新表单的提交事件
        $('#updateForm').submit(function(event) {
            // 阻止表单的默认提交行为
            event.preventDefault();
            // 获取表单数据
            var updateformData = $(this).serialize();
            // 发送 AJAX 请求
            $.ajax({
                url: $(this).attr('action'),
                type: $(this).attr('method'),
                data: updateformData,
                success: function(response) {
                    var resultdata = $.parseJSON(response);
                    if (resultdata.code == 200) {
                        $('#toastmessage').text(resultdata.message); // 显示提交状态信息
                        // 关闭第一个模态框的时候，将 body 的 overflow 设置为 hidden
                        $('#updateModal').on('hidden.bs.modal', function () {
                            $('body').css('overflow', 'hidden');
                        });
                        // 关闭第一个模态框并等待一段时间后再进行后续操作
                        $('#updateModal').modal('hide');
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
                            // 重置所有上传标签为“选择文件”
                            var allFileLabels = document.querySelectorAll('.custom-file-label');
                            for (var j = 0; j < allFileLabels.length; j++) {
                                allFileLabels[j].innerHTML = '选择文件';
                            }
                            $('#nimageurl').val('');
                            $('#nimageurl1').val('');
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