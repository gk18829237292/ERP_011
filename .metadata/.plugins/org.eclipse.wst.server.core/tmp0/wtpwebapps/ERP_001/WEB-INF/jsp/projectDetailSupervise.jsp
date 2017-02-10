<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 审核项目</title>
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
                                <c:if test="${stuff.type == 0}">
                                <a href="CreateProject?assignmentId=${task.assignment_id}" class="btn btn-white btn-xs pull-right">编辑项目</a></c:if>
                                <h2>${task.assignmentName}<span class="label label-danger">${task.type==1?"重点":"" }</span></h2>
                            </div>
                            <dl class="dl-horizontal">
                                <dt>状态：</dt>
                                <dd><span class="label ${entry.haveComplete()?"label-default":"label-primary"}">${task.haveComplete()?"已完成":"进行中"}</span>
                                </dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-5">
                            <dl class="dl-horizontal">

                                <dt>创建人：</dt>
                                <dd>${task.accountName}</dd>
                                <dt>预算：</dt>
                                <dd>${task.financing}</dd>
                                <dt>项目地址：</dt>
                                <dd>${task.place}</dd>
                            </dl>
                        </div>
                        <div class="col-xs-12 col-sm-7">
                            <dl class="dl-horizontal">
                                <dt>开始时间：</dt>
                                <dd>${task.startTime}</dd>
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
                                        <div style="width: ${task.processNum}%;" class="progress-bar"></div>
                                    </div>
                                    <small>当前已完成项目总进度的 <strong>${task.processNum}%</strong></small>
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
                                            <c:forEach var="i" begin="1" end="${task.completNum}">
                                                <li><a id="mytab-${i}" href="#tab-${i}" data-toggle="tab">第 ${i} 次</a></li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>

                                <div class="panel-body">
                                    <div class="tab-content">
                                        <c:forEach var="i" begin="1" end="${task.completNum}">
                                            <div id="tab-${i}" class="tab-pane active">
                                                <div class="ibox float-e-margins">
                                                    <div class="ibox-content">
                                                        <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="12">
                                                            <thead>
                                                                <tr>
                                                                    <th data-toggle="true">姓名</th>
                                                                    <th>更新时间</th>
                                                                    <th>状态</th>
                                                                    <th data-hide="all"></th>
                                                                    <th data-hide="all"></th>
                                                                    <th>操作</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${reports}" var="report">
                                                                    <c:if test="${report.reportIndex == i}">
                                                                    <tr>
                                                                        <td>${report.name}</td>
                                                                        <td>${report.reportTime}</td>
                                                                        <td>
                                                                            <span class="label ${report.checkd?"label-success":"label-warning"}">
                                                                            ${report.checkd?"已审核":"待审核"}
                                                                            </span>
                                                                        </td>
                                                                        <td>
                                                                            <div class="social-body">
                                                                                <p>${report.comment}</p>
                                                                                <c:forEach items="${report.picture}" var="pic">
                                                                                    <a class="fancybox" href="img/${pic}" title="">
                                                                                        <img alt="image" src="img/${pic}" />
                                                                                    </a>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <c:if test="${report.checkd}">
                                                                            <c:forEach items="${report.supervise}" var="sv">
                                                                            <div class="social-comment">
                                                                                <p class="pull-left">${sv.name}<i class="fa fa-commenting"></i> &nbsp;</p>
                                                                                <div class="media-body">
                                                                                    <p>${sv.comment}</p>
                                                                                    <c:forEach items="${sv.picture}" var="pic">
                                                                                        <a class="fancybox" href="img/${pic}">
                                                                                        <img src="img/${pic}" alt="img">
                                                                                        </a>
                                                                                    </c:forEach>
                                                                                </div>
                                                                            </div>
                                                                            </c:forEach>
                                                                            </c:if>
                                                                        </td>
                                                                        <td>
                                                                            <a data-toggle="modal"  href="javascript:void(0)" onclick="showModal(${report.reportId})" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 审核 </a>
                                                                        </td>
                                                                    </tr>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </tbody>
                                                            <tfoot>
                                                                <tr><td colspan="5"><ul class="pagination pull-right"></ul></td></tr>
                                                            </tfoot>
                                                        </table>
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
                                <form class="form-horizontal m-t" id="signupForm" method="post" action="ProjectDetail" enctype="multipart/form-data">
                                    <input type="hidden" name="taskid" value="${task.assignment_id} ">
                                    <input type="hidden" name="reportid" id="reportid">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">评语</label>
                                        <div class="col-sm-8">
                                            <textarea id="comment" name="comment" class="form-control valid" required="true">${report.user}</textarea>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">照片（可多选）</label>
                                        <div class="col-sm-8">
                                            <input type="file"  name="img" multiple="multiple" class="form-control">
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
        function showModal(obj){
            $('#reportid').val(obj);
            $('#modal-form').modal('show');
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
