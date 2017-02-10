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
                                <h5>所有项目</h5>
                                <div class="ibox-tools">
                                    <a href="CreateProject" class="btn btn-primary btn-xs">创建新项目</a>
                                </div>
                            </div>
                     <div class="ibox-content">
                          <div class="project-list">
                              <table class="table table-hover">
                                   <tbody>
                                   		<c:forEach items="${projects}" var="entry">
                                   			<tr>
                                   				<td class="project-status"><span class="label ${entry.haveComplete()?"label-default":"label-primary"}">${entry.haveComplete()?"已完成":"进行中"}</span></td>
                                   				<td class="project-title">
                                   				<a href="ProjectDetail?assignmentId=${entry.assignment_id}">${entry.assignmentName }</a>
                                   				<span class="label label-danger">${entry.type==1?"重点":"" }</span>
                                   				<br><small>创建于  ${entry.startTime }</small>
                                   				</td>
                                   				<td class="project-completion">
                                   					<small>当前进度  ${entry.processNum}%</small>
                                   					<div class="progress progress-mini">
                                              <div style="width:  ${entry.processNum}%;" class="progress-bar"></div>
                                            </div>
                                   				</td>
                                          <td class="project-people">
                                            <span class="label label-info">${entry.departmentName}</span>
                                          </td>
                                   		<td class="project-actions">
                                            <a href="ProjectDetail?assignmentId=${entry.assignment_id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                            <a href="CreateProject?assignmentId=${entry.assignment_id}" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 编辑 </a>
                                            <a href="deleteTask?taskId=${entry.assignment_id}" 
                                            onclick="return confirm('确认删除吗？');" class="btn btn-white btn-sm"><i class="fa fa-close"></i> 删除 </a>
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
        
    </script>

</body>

</html>
