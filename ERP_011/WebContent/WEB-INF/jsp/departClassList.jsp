<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


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
                      <h5>工作分类列表</h5>
                      <div class="ibox-tools">
                          <a href="javascipt:void(0)" onclick="showModal()" data-toggle="modal" class="btn btn-primary btn-xs">添加工作</a>
                      </div>
           </div>
           <div class="ibox-content">
                <div class="project-list">
                    <table class="table table-hover">
                      <thead>
                        <tr>
                          <th>工作名称</th>
                          <th>工作数量</th>
                          <th>操作</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${departClassList}" var="departClass">
                          <tr>
                            <td>${departClass.departClassName}</td>
                            <td>待开发</td>
                            <td>
                              <a href="javascipt:void(0)" onclick="showModal_1('${departClass.departClassId}')" data-toggle="modal" class="btn btn-white btn-sm"><i class="fa fa-edit"></i> 修改 </a>
                             <a href="#" onclick="showPwd(${departClass.departClassId})" class="btn btn-white btn-sm"><i class="fa fa-close"></i> 删除 </a>
                             
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
                                <h5>增加工作类别</h5>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="signupForm" method="post" action="DepartClassListServlet">                                  
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">工作类别名称:</label>
                                        <div class="col-sm-8">
                                            <input type="text" autocomplete="off" id="departClassName" name="departClassName"  class="form-control" required>
                                        </div>
                                    </div>
                                    
                                    <input type="hidden" name="departClassId" id="departClassId" />
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
        function showModal(){
          $('#actiontype').val(0)
          $('#modal-form').modal('show');
        }
        function showModal_1(id){
          $('#actiontype').val(1)
          $('#departClassId').val(id)
          $('#modal-form').modal('show');
        }
        
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


					checkPwd(inputValue,id);
        		
        			
        		}
        	);
        	
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
            			console.log("result : " + result);
            			if(result =="true"){
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
            				swal('删除失败: 请检查密码。');
            			}
            		}
            	}
            	xmlhttp.open("GET","QueryHighPwdServlet?type=2&password="+str+"&id="+id,true);
            	xmlhttp.send();
            }
        }
    </script>

</body>

</html>
