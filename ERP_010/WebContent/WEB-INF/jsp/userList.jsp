<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--360浏览器优先以webkit内核解析-->


    <title> - 主页示例</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
	
    <div class="wrapper wrapper-content">
    	<div class="row">
       	<div class="ibox">
           <div class="ibox-title">
                      <h5>用户列表</h5>
                      <div class="ibox-tools">
                          <a href="javascipt:void(0)" onclick="showModal(${type})" data-toggle="modal" class="btn btn-primary btn-xs">添加用户</a>
                      </div>
           </div>
           <div class="ibox-content">
                <div class="project-list">
                    <table class="table table-hover">
                      <thead>
                        <tr>
                          <th>账户</th>
                          <th>密码</th>
                          <th>姓名</th>
                          <th>操作</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${userList}" var="user">
                          <tr>
                            <td>${user.account}</td>
                            <td>${user.pwd}</td>
                            <td>${user.name}</td>
                            <td>
                              <a href="javascipt:void(0)" onclick="showModal_1(${type},'${user.account}')" data-toggle="modal" class="btn btn-white btn-sm"><i class="fa fa-edit"></i> 修改 </a>
                            </td>
                          </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                </div>
			     </div>
		    </div>
		  </div>

    </div>


    <div id="modal-form" class="modal fade" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-body">
                   <div class="row">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>添加用户</h5>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="signupForm" method="post" action="userList">                                  
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">账号:</label>
                                        <div class="col-sm-8">
                                            <input type="text" autocomplete="off" id="account" name="account"  class="form-control" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                      <label class="col-sm-3 control-label">密码</label>
                                      <div class="col-sm-8">
                                        <input id="pwd" type="password" autocomplete="off" name="pwd" class="form-control" required>
                                      </div>
                                    </div>
                                    <div class="form-group">
                                      <label class="col-sm-3 control-label">姓名</label>
                                      <div class="col-sm-8">
                                        <input type="text" name="name" class="form-control" required>
                                      </div>
                                    </div>
                                    
                                    
                                    <input type="hidden" name="type" id="type">
                                    <input type="hidden" name="actiontype" id="actiontype">
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">提交</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                   </div>
               </div>
           </div>
       </div>
    </div>
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="js/content.js"></script>
    <!-- Sweet alert -->
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script>
        function foo(){
          swal({
                        title: "您确定要删除这条信息吗",
                        text: "删除后将无法恢复，请谨慎操作！",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是的，我要删除！",
                        cancelButtonText: "让我再考虑一下…",
                        closeOnConfirm: false,
                        closeOnCancel: false
                    },
                    function () {
                        if (isConfirm) {
                            swal("删除成功！", "您已经永久删除了这条信息。", "success");
                        } else {
                            swal("已取消", "您取消了删除操作！", "error");
                        }
                    });
        }
        function showModal(obj){
          $('#account').removeAttr('readonly')
          $('#account').val('')
          $('#pwd').val('')
          $('#type').val(obj)
          $('#actiontype').val(1)
          $('#modal-form').modal('show');
        }
        function showModal_1(obj,account){
          $('#account').attr("readonly","true")
          $('#account').val(account)
          $('#pwd').val('')
          $('#type').val(obj)
          $('#actiontype').val(2)
          $('#modal-form').modal('show');
        }
    </script>

</body>

</html>
