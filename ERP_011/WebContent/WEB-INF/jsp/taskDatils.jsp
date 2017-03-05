<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 项目详情</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
    <!--foo table -->
    <link href="css/plugins/footable/footable.core.css" rel="stylesheet">
    <!--blueimp -->
    <link href="css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">

    <link href="js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="row">
        <div class="wrapper wrapper-content animated fadeInUp">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="m-b-md">
                                 <h5>${departName }   
                                 <c:if test="${departClassName != null}">
                                (${departClassName})
                                </c:if>
                                </h5> 
                                <c:if test="${stuff.isType0_1() }">
                                 <a href="CreateTaskServlet?taskId=${task.taskId}" class="btn btn-white btn-xs pull-right">编辑项目</a>
                                </c:if>
                                <div class="col-sm-offset-5">
                                  <h2>${task.taskName } <span class="label label-danger">${entry.type==1?"重点":"" }</span></h2>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>状态：</dt>
                                <dd>
                                	<span class="label ${entry.isComplete()?"label-default":"label-primary"}">${entry.isComplete()?"已完成":"进行中"}</span>
                                </dd>
                            </dl>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>上报频率：</dt>
                                <dd>${task.reportType }</dd>
                            </dl>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>责任人：</dt>
                                <dd>${task.chairMan }</dd>
                            </dl>
                        </div>                        
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>开始时间：</dt>
                                <dd>${task.startTime}</dd>
                            </dl>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>结束时间：</dt>
                                <dd>${task.endTime}</dd>
                            </dl>
                        </div>                     
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <dl class="dl-horizontal">
                                <dt>当前进度</dt>
                                <dd>
                                    <div class="progress progress-striped active m-b-sm">
                                        <div style="width: ${task.progress}%;" class="progress-bar"></div>
                                    </div>
                                    <small>当前已完成项目总进度的 <strong>${task.progress}%</strong></small>
                                </dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <dl class="dl-horizontal">
                                <dt>任务目标</dt>
                                <dd><i>${task.goal}</i></dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row m-t-sm">
                        <div class="col-sm-12">
                            <div class="panel blank-panel">
                                <div class="panel-heading">
                                    <div class="panel-options">
                                        <ul class="nav nav-tabs" id="myTab">
                                        	<c:forEach var="i" begin="1" end="${ task.getMaxNum()}">
                                        		 <li><a id="mytab-${i }" href="#tab-${i }" data-toggle="tab">第 ${i }次</a></li>
                                        	</c:forEach>
                                        </ul>
                                    </div>
                                </div>

                                <div class="panel-body">
                                    <div class="tab-content">
                                       <c:forEach var="i" begin="1" end="${task.getMaxNum() }">
                                       		<div id="tab-${i }" class ="tab-pane">
                                       			<div class ="row">
                                       				<table class="table table-bordered table-hover" style="width:95%">
                                       					<tbody>
                                       						<tr>
                                                            	<td>第${i}次报告</td>
                                                            	<td>提交时间：${reports[i].reportTime}</td>
                                                            </tr>
                                                            <tr>
                                                            	<td>文字汇报</td>
                                                            	<td>${reports[i].comment}</td>
                                                            </tr>
                                                            <tr>
                                                            	<td>图片汇报</td>
                                                            	<td>
                                                            		<c:forEach items="${reports[i].picture}" var="pic">
                                                            			<a class="fancybox" href="img/${pic}">
                                                                        	<img height="200" width="150" src="img/${pic}" alt="img">
                                                                        </a>
                                                                    </c:forEach>
                                                                </td>
                                                            </tr>
                                       					</tbody>
                                       				</table>
                                       			</div>
                                       			<div class="row">
                                       			 	<div class="col-sm-8">
	                                       				<dl class="dl-horizontal">
	                                       					<dt>督查意见：</dt>
	                                       					<dd>${advices1[i].comment}</dd>
	                                                        <dt>图片核实：</dt>
	                                                        <dd>
	                                                        	<c:forEach items="${advices1[i].picture}" var="pic">
	                                                                <a class="fancybox" href="img/${pic}">
	                                                                    <img  src="img/${pic}" alt="img">
	                                                                </a>
	                                                            </c:forEach>
	                                                       </dd>
	                                                    </dl>
                                                    </div>
                                                    <div class="col-sm-2 pull-right">
                                                    <c:if test="${stuff.isType0_1() }">
                                                    	<a href="#" onclick="show(${i})" class="btn btn-primary btn-sm">督查信息</a> 
                                                    </c:if>
                                                    	
                                                    </div>
                                       			</div>
                                                <div class="row">
                                                	<div class="col-sm-8">
                                                		<dl class="dl-horizontal">
                                                			<dt><B>${advices2[i].name }</B> 领导点评：</dt>
                                                			<dd>${advices2[i].comment}</dd>
                                                    	</dl>
                                                	</div>
                                                  <div class="col-sm-2 pull-right">
                                                  <c:if test="${stuff.isType2_leader() }">
                                                  	<a href="#" onclick="show_1(${i})" class="btn btn-primary btn-sm">添加点评</a> 
                                                  </c:if>
                                                  </div>
                                                </div>  
                                       		</div>
                                       </c:forEach>
                                            
                                    </div>

                                </div>
                            </div>
                        </div>
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
                                <h5>审核</h5>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="signupForm" method="post" action="TaskDetailServlet" enctype="multipart/form-data">
                                    <input type="hidden" name="taskId" value="${task.taskId} ">
                                    <input type="hidden" name="index" id="index">
                                    <input type="hidden" name="actionType" value="0">
                                    <input type="hidden" name="departName" value="${departName }">
                                    <input type="hidden" name="departClassName" value="${departClassName }">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">督查意见</label>
                                        <div class="col-sm-8">
                                            <textarea id="comment" name="comment" class="form-control valid" required="true"></textarea>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">图片核实</label>
                                        <div class="col-sm-8">
                                            <input type="file"  name="picture" multiple="multiple" class="form-control">
                                        </div>
                                    </div>
                                    
                                    
                                    
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

<div id="modal-form_leader" class="modal fade" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-body">
                   <div class="row">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>审核</h5>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="signupForm" method="post" action="TaskDetailServlet" enctype="multipart/form-data">
                                    <input type="hidden" name="taskId" value="${task.taskId} ">
                                    <input type="hidden" name="index_1" id="index_1">
                                    <input type="hidden" name="actionType" value="1">
                                        <input type="hidden" name="departName" value="${departName }">
                                    <input type="hidden" name="departClassName" value="${departClassName }">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">评语</label>
                                        <div class="col-sm-8">
                                            <textarea id="comment" name="comment" class="form-control valid" required="true"></textarea>
                                        </div>
                                    </div>
                                    
                                    
                                    
                                    
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


    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>

    <!---->
    <script src="js/plugins/footable/footable.all.min.js"></script>
    <script>
        $(document).ready(function () {

            $('#loading-example-btn').click(function () {
                btn = $(this);
                simpleLoad(btn, true)

                // Ajax example
                //                $.ajax().always(function () {
                //                    simpleLoad($(this), false)
                //                });

                simpleLoad(btn, false)
            });
        });

        function simpleLoad(btn, state) {
            if (state) {
                btn.children().addClass('fa-spin');
                btn.contents().last().replaceWith(" Loading");
            } else {
                setTimeout(function () {
                    btn.children().removeClass('fa-spin');
                    btn.contents().last().replaceWith(" Refresh");
                }, 2000);
            }
        }
        
        function show(index){
        	$('#index').val(index);
        	$('#modal-form').modal('show');
        }
        
        function show_1(index){
        	$('#index_1').val(index);
        	$('#modal-form_leader').modal('show');
        }
    </script>


    
     <!-- blueimp gallery -->
    <script src="js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>

    <!-- Fancy box -->
    <script src="js/plugins/fancybox/jquery.fancybox.js"></script>


    <script>
        $(document).ready(function () {
             $('.footable').footable();
            $('.fancybox').fancybox({
                openEffect: 'none',
                closeEffect: 'none'
            });
            $('#myTab a:first').tab('show');
        });
    </script>

</body>

</html>
