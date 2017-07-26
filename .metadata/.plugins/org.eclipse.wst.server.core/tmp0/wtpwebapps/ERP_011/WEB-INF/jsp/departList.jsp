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
                      <h5>任务分配</h5>
                      <div class="ibox-tools">
                          <a href="javascipt:void(0)" onclick="showModal()" data-toggle="modal" class="btn btn-primary btn-xs">添加单位</a>
                      </div>
           </div>
           <div class="ibox-content">
                <div class="project-list">
                    <table class="table table-hover">
                      <thead>
                        <tr>
                          <th>责任单位</th>
                          <th>工作人员数量</th>
                          <th>工作任务数量</th>
                          <th>操作</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${departs}" var="depart">
                          <tr>
                            <td>${depart.departName}</td>
                            <td>待开发</td>
                            <td>待开发</td>
                            <td>
                              <a href="javascipt:void(0)" onclick="showModal_1('${depart.departId}')" data-toggle="modal" class="btn btn-white btn-sm"><i class="fa fa-edit"></i> 修改  </a>
                             <a href="#" onclick="showPwd(${depart.departId})" class="btn btn-white btn-sm"><i class="fa fa-close"></i> 删除 </a>
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
                                <form class="form-horizontal m-t" id="signupForm" method="post" action="DepartListServlet">                                  
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">工作名称:</label>
                                        <div class="col-sm-8">
                                            <input type="text" autocomplete="off" id="departName" name="departName"  class="form-control" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                    	<label class="col-sm-3 control-label">工作分类:</label>
                                    	<div class="col-sm-8">
                                    		<c:forEach items="${departClassList}" var="departClass">
                                    			<input type="checkbox" name="departClass" id="departClass" value="${departClass.departClassId }"/>${departClass.departClassName }<br/>
	                                        </c:forEach>
										</div>
                                    </div>
                                    
                                    <input type="hidden" name="departId" id="departId" />
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
      
        function showModal(){
          $('#actiontype').val(0)
          $('#modal-form').modal('show');
        }
        function showModal_1(id){
          $('#actiontype').val(1)
          $('#departId').val(id)
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
            	xmlhttp.open("GET","QueryHighPwdServlet?type=1&password="+str+"&id="+id,true);
            	xmlhttp.send();
            }
        }
    </script>

</body>

</html>
