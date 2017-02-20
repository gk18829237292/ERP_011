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
                                 <h5>${departName } ${departClassName}</h5> 
                                <a href="CreateProject?taskId=${task.taskId}" class="btn btn-white btn-xs pull-right">编辑项目</a>
                                <div class="col-sm-offset-5">
                                  <h2>项目A<span class="label label-danger">重点</span></h2>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>状态：</dt>
                                <dd><span class="label label-primary">进行中</span>
                                </dd>
                            </dl>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>上报频率：</dt>
                                <dd>周报</dd>
                            </dl>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>责任人：</dt>
                                <dd>高科</dd>
                            </dl>
                        </div>                        
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>开始时间：</dt>
                                <dd>2017年2月20日11:04:40</dd>
                            </dl>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <dl class="dl-horizontal">
                                <dt>结束时间：</dt>
                                <dd>2017年2月20日11:04:46</dd>
                            </dl>
                        </div>                     
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <dl class="dl-horizontal">
                                <dt>当前进度</dt>
                                <dd>
                                    <div class="progress progress-striped active m-b-sm">
                                        <div style="width: 83%;" class="progress-bar"></div>
                                    </div>
                                    <small>当前已完成项目总进度的 <strong>83%</strong></small>
                                </dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <dl class="dl-horizontal">
                                <dt>任务目标</dt>
                                <dd><i>全部完成</i></dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row m-t-sm">
                        <div class="col-sm-12">
                            <div class="panel blank-panel">
                                <div class="panel-heading">
                                    <div class="panel-options">
                                        <ul class="nav nav-tabs" id="myTab">
                                            <li><a id="mytab-1" href="#tab-1" data-toggle="tab">第 1 次</a></li>
                                            <li><a id="mytab-2" href="#tab-2" data-toggle="tab">第 2 次</a></li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="panel-body">
                                    <div class="tab-content">
                                       
                                            <div id="tab-1" class="tab-pane active">
                                                <div class="contact-box">
                                                <div class="row">
                                                    <center>
                                                        <table class="table table-bordered table-hover" style="width:95%">
                                                            <tbody>
                                                                <tr>
                                                                    <td>第1次报告</td>
                                                                    <td>提交时间：2017年2月20日11:06:18</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>文字汇报</td>
                                                                    <td>仅仅是一个测试</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>图片汇报</td>
                                                                    <td>
                                                                      <a class="fancybox" href="img/gk1.jpg"><img src="img/gk1.jpg" alt="img"></a>
                                                                      <a class="fancybox" href="img/gk2.jpg"><img src="img/gk2.jpg" alt="img"></a>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </center>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-8">
                                                      <dl class="dl-horizontal" >
                                                        <dt>督查意见：</dt>
                                                        <dd>完成的还可以</dd>
                                                        <dt>图片核实：</dt>
                                                        <dd>
                                                          <a class="fancybox" href="img/gk1.jpg"><img src="img/gk1.jpg" alt="img"></a>
                                                          <a class="fancybox" href="img/gk2.jpg"><img src="img/gk2.jpg" alt="img"></a>
                                                        </dd>
                                                      </dl>
                                                    </div>
                                                    <div class="col-sm-2 pull-right">
                                                        44585
                                                    </div>
                                                </div>
                                                <div class="row">
                                                  <div class="col-sm-8">
                                                    <dl class="dl-horizontal">
                                                        <dt>领导点评：</dt>
                                                        <dd>测试一下吧</dd>
                                                    </dl>
                                                  </div>
                                                  <div class="col-sm-2 pull-right">
                                                      456
                                                  </div>
                                                </div>  
                                                </div>
                                            </div>
                                            <div id="tab-2" class="tab-pane">
                                              46
                                            </div>
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
