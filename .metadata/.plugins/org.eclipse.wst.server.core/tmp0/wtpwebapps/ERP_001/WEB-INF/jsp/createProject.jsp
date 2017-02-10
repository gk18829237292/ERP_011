<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 创建任务</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/plugins/steps/jquery.steps.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
    <link href="css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>创建任务</h5>
                    </div>
                    <div class="ibox-content">

                        <form id="form" name="form" method="post" action="CreateProject" class="wizard-big">
                            
                            <h1>Step1</h1>
                            <fieldset>
                                <h2>Step1</h2>
                                <div class="row">
                                    <div class="col-sm-8">
                                        <div class="form-group">
                                            <label>任务名称 *</label>&nbsp;
                                            <div class="checkbox checkbox-inline">
                                                <input type="checkbox" id="tasktype" name="tasktype" value="1" <c:if test="${task.type == 1}">checked="true"</c:if>>
                                                <label for="inlineCheckbox1">是否为重点项目</label>
                                            </div>
                                            <input id="taskname" name="taskname" type="text" class="form-control required" value="${task.assignmentName}">
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>任务地点 *</label>
                                            <input id="taskPlace" name="taskPlace" type="text" class="form-control required" value="${task.place}">
                                        </div>
                                        <div class="form-group">
                                            <label>任务资金 *</label>
                                            <input id="financing" name="financing" type="number" class="form-control required" value="${task.financing}">
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <h1>Step2</h1>
                            <fieldset>
                                <h2>Step2</h2>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">开始时间*</label>
                                            <div class="col-sm-10">
                                                <input id="startTime" name="startTime" class="form-control required layer-date" type="text" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm'})" placeholder="开始时间" value="${task.startTime}">
                                            </div>
                                            
                                        </div>
                                        <br><br>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">结束时间*</label>
                                            <div class="col-sm-10">
                                               <input id="endTime" name="endTime" class="form-control required layer-date" type="text" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm'})" placeholder="结束时间" value="${task.endTime}"> 
                                            </div>
                                        </div>
                                        <br><br>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">任务期数</label>
                                            <div class="col-sm-3">
                                                <input type="number" name="num" id="num" class="form-control required" value="${task.num}">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <h1>Step3</h1>
                            <fieldset>
                                <h2>Step3</h2>
                                <div class="row">
                                    <div class="form-group">
                                        <label>任务目标</label>
                                        <textarea name="goal" id="goal" class="form-control required" style="height: 200px ">${task.goal}</textarea>
                                    </div>
                                </div>
                            </fieldset>
                            <h1>Step4</h1>
                            <fieldset>
                                <h2>Step4</h2>
                                <div class="row">
                                    <div class="form-group">
                                        <label>部门选择</label><br>
                                          <div class="input-group">
                                            <select id="departmentId" name="departmentId" data-placeholder="选择部门..." style="width:350px;" tabindex="2">
                                                <option value="">请选择部门</option>
                                                <c:forEach items="${department}" var = "entry">
                                                    <option value="${entry.departmentId}" <c:if test="${entry.departmentName == task.departmentName}">selected='selected'</c:if>
                                                    >${entry.departmentName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>            
                                    </div>
                                    
                                </div>
                            </fieldset>    
                            <input type="hidden" name="taskid" value="${task.assignment_id}">
                            <input type="hidden" name="Actiontype" value="${actionType}">  
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


    <!-- Steps -->
    <script src="js/plugins/staps/jquery.steps.min.js"></script>

    <!-- Jquery Validate -->
    <script src="js/plugins/validate/jquery.validate.min.js"></script>
    <script src="js/plugins/validate/messages_zh.min.js"></script>
    <script src="js/plugins/layer/laydate/laydate.js"></script>
    <!-- Chosen -->
    <script src="js/plugins/chosen/chosen.jquery.js"></script>
    <!-- iCheck -->
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#wizard").steps();
            $("#form").steps({
                bodyTag: "fieldset",
                onStepChanging: function (event, currentIndex, newIndex) {
                    // Always allow going backward even if the current step contains invalid fields!
                    if (currentIndex > newIndex) {
                        return true;
                    }

                    // Forbid suppressing "Warning" step if the user is to young
                    if (newIndex === 3 && Number($("#age").val()) < 18) {
                        return false;
                    }

                    var form = $(this);

                    // Clean up if user went backward before
                    if (currentIndex < newIndex) {
                        // To remove error styles
                        $(".body:eq(" + newIndex + ") label.error", form).remove();
                        $(".body:eq(" + newIndex + ") .error", form).removeClass("error");
                    }

                    // Disable validation on fields that are disabled or hidden.
                    form.validate().settings.ignore = ":disabled,:hidden";

                    // Start validation; Prevent going forward if false
                    return form.valid();
                },
                onStepChanged: function (event, currentIndex, priorIndex) {
                    // Suppress (skip) "Warning" step if the user is old enough.
                    if (currentIndex === 2 && Number($("#age").val()) >= 18) {
                        $(this).steps("next");
                    }

                    // Suppress (skip) "Warning" step if the user is old enough and wants to the previous step.
                    if (currentIndex === 2 && priorIndex === 3) {
                        $(this).steps("previous");
                    }
                },
                onFinishing: function (event, currentIndex) {
                    var form = $(this);

                    // Disable validation on fields that are disabled.
                    // At this point it's recommended to do an overall check (mean ignoring only disabled fields)
                    form.validate().settings.ignore = ":disabled";

                    // Start validation; Prevent form submission if false
                    return form.valid();
                },
                onFinished: function (event, currentIndex) {
                    var form = $(this);

                    // Submit form input
                    form.submit();
                },
                onCanceled: function(event,currentIndex){
                	history.back(-1);
                }
            }).validate({
                errorPlacement: function (error, element) {
                    element.before(error);
                }
            });
        });
    </script>

    







    

</body>

</html>
