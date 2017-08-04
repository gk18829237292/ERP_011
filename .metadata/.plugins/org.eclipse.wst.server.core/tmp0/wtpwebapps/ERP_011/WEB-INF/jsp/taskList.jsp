<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> 任务列表</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert_1.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
    


</head>

<body class="gray-bg">
	
    <div class="wrapper wrapper-content">
    	<div class="row">
           	<div class="ibox">
                     <div class="ibox-title">
                                <h5>${departName }所有项目
                                <c:if test="${departClassName != null}">
                                (${departClassName})
                                </c:if>
                                </h5>
                         <c:if test="${stuff.isType0_1() }">
                         	<div class="ibox-tools">
                    		<a href="CreateTaskServlet" class="btn btn-primary btn-xs">创建新项目</a>
                    	</div>   
                         </c:if>
					</div>
                     <div class="ibox-content">
                     <div class="panel blank-panel">
                        <div class="panel-heading">
                            <ul class="nav nav-tabs" id="myTab">
                              <li><a id="mytab-1" href="#tab-1" data-toggle="tab">未完成任务</a></li>
                              <li><a id="mytab-2" href="#tab-2" data-toggle="tab">已完成任务</a></li>
                            </ul>
                        </div>
                        <div class="panel-body">
                            <div class="tab-content">
                              <div id="tab-1" class="tab-pane active">
                                    <div class="project-list">
                                    <table class="table table-hover">
                                         <tbody>
                                            <c:forEach items="${taskEntries_not_complete}" var="entry">
                                              <tr>
                                                <td class="project-status"><span class="label ${entry.isComplete()?"label-default":"label-primary"}">${entry.isComplete()?"已完成":"进行中"}</span></td>
                                                <td class="project-title">
                                                <a href="TaskDetailServlet?taskId=${entry.taskId}& departName=${departName} &departClassName=${deprtClassName}">${entry.taskName }</a>
                                                <span class="label label-danger">${entry.type==1?"重点":"" }</span>
                                                <br><small>创建于  ${entry.startTime }</small>
                                                </td>
                                                <td class="project-completion">
                                                  <small>当前进度  ${entry.progress}%</small>
                                                  <div class="progress progress-mini">
                                                    <div style="width:  ${entry.progress}%;" class="progress-bar"></div>
                                                  </div>
                                                </td>
                                                <td class="project-people">
                                                  <span class="label label-info">${entry.departName}</span>
                                                </td>
                                                <td class="project-status">
                                                    <span class="label ${entry.isChecked1()?"label-default":"label-primary"}">${entry.isChecked1()?"已按期限":"未按期限"}</span>
                                                </td>
                                                <td class="project-status">
                                                    <span class="label ${entry.isChecked2()?"label-warning":"label-default"}">${entry.isChecked2()?"有点评":""}</span>
                                                </td>
                                                <td class="project-actions">
                                                  <a href="TaskDetailServlet?taskId=${entry.taskId}&departName=${departName}&departClassName=${deprtClassName}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                                  <c:if test="${stuff.isType0_1() }">
                                                  	<a href="CreateTaskServlet?taskId=${entry.taskId}" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 编辑 </a>
                                                  </c:if>
                                                  <c:if test="${stuff.isType0() }">
                                                  <!--deleteTask?taskId=${entry.taskId}  -->
                                                	  <a href="#" onclick="showPwd(${entry.taskId})" class="btn btn-white btn-sm"><i class="fa fa-close"></i> 删除 </a>
                                                  </c:if>
                                                  </td>
                                              </tr>
                                            </c:forEach>
                                         </tbody>
                                    </table>
                                    </div>
                              </div>
                              <div id="tab-2" class="tab-pane">
                                  <div class="project-list">
                                    <table class="table table-hover">
                                         <tbody>
                                            <c:forEach items="${taskEntries_complete}" var="entry">
                                              <tr>
                                                <td class="project-status"><span class="label ${entry.isComplete()?"label-default":"label-primary"}">${entry.isComplete()?"已完成":"进行中"}</span></td>
                                                <td class="project-title">
                                                <a href="TaskDetailServlet?taskId=${entry.taskId}&departName=${departName }&departClassName=${deprtClassName}">${entry.taskName }</a>
                                                <span class="label label-danger">${entry.type==1?"重点":"" }</span>
                                                <br><small>创建于  ${entry.startTime }</small>
                                                </td>
                                                <td class="project-completion">
                                                  <small>当前进度  ${entry.progress}%</small>
                                                  <div class="progress progress-mini">
                                                    <div style="width:  ${entry.progress}%;" class="progress-bar"></div>
                                                  </div>
                                                </td>
                                                <td class="project-people">
                                                  <span class="label label-info">${entry.departName}</span>
                                                </td>
                                                <td class="project-status">
                                                    <span class="label ${entry.isChecked1()?"label-default":"label-primary"}">${entry.isChecked1()?"已按期限":"未按期限"}</span>
                                                </td>
                                                <td class="project-status">
                                                    <span class="label ${entry.isChecked2()?"label-warning":"label-default"}">${entry.isChecked2()?"有点评":""}</span>
                                                </td>
                                                <td class="project-actions">
                                                  <a href="TaskDetailServlet?taskId=${entry.taskId}&departName=${departName }&departClassName=${deprtClassName}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                                  <c:if test="${stuff.isType0_1() }">
                                                  	<a href="CreateProject?assignmentId=${entry.taskId}" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 编辑 </a>
                                                  </c:if>
                                                  <c:if test="${stuff.isType0() }">
                                                  <!--deleteTask?taskId=${entry.taskId}  -->
                                                	  <a href="#" onclick="showPwd(${entry.taskId})" class="btn btn-white btn-sm"><i class="fa fa-close"></i> 删除 </a>
                                                  </c:if>
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
					</div>
				</div>
		</div>

    </div>
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/layer/layer.min.js"></script>


    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>

    <!---->
    <script src="js/plugins/footable/footable.all.min.js"></script>

  <!-- blueimp gallery -->
    <script src="js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>

    <!-- Fancy box -->
    <script src="js/plugins/fancybox/jquery.fancybox.js"></script>

    <!-- 自定义js -->
    <script src="js/content.js"></script>
    <!-- Sweet alert -->
    <script src="js/plugins/sweetalert/sweetalert.min_1.js"></script>
    <script>
        $(document).ready(function () {
            $('.footable').footable();
            $('.fancybox').fancybox({
                openEffect: 'none',
                closeEffect: 'none'
            });
            $('#myTab a:first').tab('show');
        });
        
        function showPwd(id){
        	swal({   
        		title: "删除任务 ",   
        		text: "请输入最高权限密码",   
        		type: "input",   
        		showCancelButton: true,   
        		closeOnConfirm: false,   
        		showLoaderOnConfirm:true,
        		animation: "slide-from-top",   
        		inputPlaceholder: "密码" 
        		},
        		function(inputValue){   
        			if (inputValue === false) return false;      
        			if (inputValue === "") { 
        				swal.showInputError("You need to write something!");     
        				return false;   
        			}    


					checkPwd(inputValue,taskId);
        		
        			
        		}
        	);
        }
        
        function checkPwd(str,id){
        	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
        	  xmlhttp=new XMLHttpRequest();
        	}
        	else{// code for IE6, IE5
        	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        	}

        	xmlhttp.onreadystatechange=function(){
        		if (xmlhttp.readyState==4 && xmlhttp.status==200){
        		 	
        			result = xmlhttp.responseText;
        			if(result){
        				swal({   
        					title: "成功",   
   							text: "删除成功",   
   							type: "success",   
   							showCancelButton: false,    
   							confirmButtonText: "确认",   
   							closeOnConfirm: false 
   							}, function(){   
   								window.location.reload(true);
   							});
        				
        			}else{
        				swal('删除失败: ');
        			}
        		}
        	}
        	xmlhttp.open("GET","QueryHighPwdServlet?type=0&password="+str+"&id="+taskId,true);
        	xmlhttp.send();
        }
    </script>

</body>

</html>
