<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 表单验证 jQuery Validation</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>任务提交</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm" method="post" action="CreateReportServlet" enctype="multipart/form-data">
                            <input type="hidden" name="taskId" value="${taskId} ">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">报告次数：</label>
                                <div class="col-sm-8">
                                    <input id="reportIndex" name="reportIndex" class="form-control" type="number"">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle" ></i> 第几次的报告</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">报告内容</label>
                                <div class="col-sm-8">
                                    <textarea id="comment" name="comment" class="form-control valid" required="true"></textarea>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">文件选择（多选）</label>
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


    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="js/plugins/validate/jquery.validate.min.js"></script>
    <script src="js/plugins/validate/messages_zh.min.js"></script>

    <script src="js/demo/form-validate-demo.js"></script>

    
    

</body>

</html>
