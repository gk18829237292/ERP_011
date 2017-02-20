<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> 个人信息</title>
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
                        <h5>${stuff.name} 的信息修改</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm" method="post" action="UpdateUserInfoServlet">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">账号：</label>
                                <div class="col-sm-8">
                                    <input id="account" name="num" class="form-control" readonly value="${stuff.account}">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle" ></i>账号不可修改</span>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-8">
                                    <input id="password" name="password" class="form-control" type="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">确认密码：</label>
                                <div class="col-sm-8">
                                    <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" class="form-control" value="${stuff.name }">
                                </div>
                            </div>
                            
							<div class="form-group">
                                <label class="col-sm-3 control-label">电话号码：</label>
                                <div class="col-sm-8">
                                    <input id="telNum" name="telNum" class="form-control" type="number" value="${stuff.telNum }">
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
