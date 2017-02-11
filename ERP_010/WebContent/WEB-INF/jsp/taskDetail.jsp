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
                                <c:if test="${stuff.type == 0}">
                                <a href="CreateProject?taskId=${task.taskId}" class="btn btn-white btn-xs pull-right">编辑项目</a></c:if>
                                <h2>${task.taskName}<span class="label label-danger">${task.type==1?"重点":"" }</span></h2>
                            </div>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>状态：</dt>
                                <dd><span class="label ${task.isComplete()?"label-default":"label-primary"}">${task.isComplete()?"已完成":"进行中"}</span>
                                </dd>
                            </dl>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>上报频率：</dt>
                                <dd>${task.reportType}</dd>
                            </dl>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>责任人：</dt>
                                <dd>${task.chairMan}</dd>
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
                                            <c:forEach var="i" begin="1" end="${task.reportNum}">
                                                <li><a id="mytab-${i}" href="#tab-${i}" data-toggle="tab">第 ${i} 次</a></li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>

                                <div class="panel-body">
                                    <div class="tab-content">
                                        <c:forEach var="i" begin="1" end="${task.reportNum}">
                                            <div id="tab-${i}" class="tab-pane active">
                                                <div class="contact-box">
                                                <div class="row">
                                                    <center>
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
                                                                            <img src="img/${pic}" alt="img">
                                                                            </a>
                                                                        </c:forEach>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </center>
                                                </div>
                                                <div class="row">
                                                    <dl class="dl-horizontal">
                                                        <dt>督查意见：</dt>
                                                        <dd>${advices1[i].comment}</dd>
                                                        <dt>图片核实：</dt>
                                                        <dd>
                                                            <c:forEach items="${advices1[i].picture}" var="pic">
                                                                <a class="fancybox" href="img/${pic}">
                                                                    <img src="img/${pic}" alt="img">
                                                                </a>
                                                            </c:forEach>
                                                        </dd>
                                                    </dl>
                                                </div>
                                                <!-- 后面添加 
                                                <div class="row">
                                                    <dl class="dl-horizontal">
                                                        <dt>领导点评：</dt>
                                                        <dd>${advices2[i].comment}</dd>
                                                    </dl>
                                                </div>  -->
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
