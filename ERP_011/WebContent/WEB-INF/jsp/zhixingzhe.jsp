<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

     <title>执行力管理云平台(试用版)</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
        <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">

    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">执行力管理云平台 <font size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（试用版）</font></strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">执行力管理云平台（试用版）
                        </div>
                    </li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">分类</span>
                    </li>
                    <c:forEach items="${departClassEntries}" var ="departClass">
                    <li>
                        <a class="J_menuItem" href="taskServlet?departId=${depart.departId}&departName=${depart.departName}&departClassName=${departClass.departClassName}&departClassId=${departClass.departClassId}">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">${departClass.departClassName}</span>
                          
                        </a>
                       
                    </li>
                    </c:forEach>
                   
                    <li class="line dk"></li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">分类</span>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">提交报告</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <c:forEach items="${taskList}" var="task">
                                <li>
                                    <a class="J_menuItem" href="CreateReportServlet?taskId=${task.taskId}">
                                        ${task.taskName}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li> 
                </ul>
            </div>
        </nav>   
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                	<div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
                	</div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li><span class="label label-primary">执行者</span></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                                ${depart.departName }<span class="label label-primary"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-alerts">
                                <li>
                                    <a href="#" class="demo1">个人信息</a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                	<a class="J_menuItem" href="UpdateUserInfoServlet" type="link">信息修改</a>
                                </li>
                                <li>
                                    <a href="LogoutServlet" type="link">注销</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" src="taskServlet?departId=${depart.departId}&departName=${depart.departName}" frameborder="0" data-id="tasks" seamless></iframe>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="js/plugins/pace/pace.min.js"></script>
     <!-- Sweet alert -->
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script>
        $(document).ready(function () {

            $('.demo1').click(function () {
                swal({
                    title: "个人信息",
                    text: "姓名:\t${stuff.name}\n电话:\t${stuff.telNum}"
                });
            });
        });
    </script>

</body>

</html>