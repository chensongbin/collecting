<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ZH-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>创建问卷</title>
	<link rel="shortcut icon" href="/collecting2.0/img/icon.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-collecting.css">
	<link rel="stylesheet" type="text/css" href="css/paper.css">
	<style type="text/css">
	   /*iphone: w < 768px*/
	    @media screen and (max-width: 1000px){
	        .operation-panel{
	            display: none;
	        }
	    }
	    #paper-title:hover{
	    	cursor: pointer;
	    }
	</style>
</head>
<body>
<!-- 导航 -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- 小屏幕导航按钮和logo -->
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
	          	<span class="icon-bar"></span>
	          	<span class="icon-bar"></span>
			</button>
			<a href="/collecting2.0/index.jsp" class="navbar-brand">易收集</a>
		</div>	
		<!-- 小屏幕导航按钮和logo -->
		<!-- 导航 -->
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-left">
				<li><a href="/collecting2.0/index.jsp">首页</a></li>
			 	<li><a href="/collecting2.0/createPaper.jsp">创建问卷</a></li>
			 	<li><a href="/collecting2.0/GetAllPaper">我的问卷</a></li>

				<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">操作面板 <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="#add-question-modal" data-toggle="modal">添加问答题</a></li>
					<li><a href="#add-single-modal" data-toggle="modal">添加单选题</a></li>
					<li><a href="#add-multiple-modal" data-toggle="modal">添加多选题</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="#" class="save">保存</a></li>
				    <li><a href="#" class="clear">清除</a></li>
				    <li><a href="#" class="publish">发布</a></li>
		          </ul>
		        </li>
			</ul>
			 <ul class="nav navbar-nav navbar-right">
			 	<li><a class="link-model" data-toggle="modal" data-target="#RegisterModal">注册</a></li>
			 	
			 	<c:if test="${empty sessionScope.user}">
			 		<li><a class='link-model' data-toggle='modal' data-target='#LoginModal'>登录</a></li>
			 	</c:if>
			 	
			 	<c:if test="${!empty sessionScope.user}">
			 		<li><a>${sessionScope.user.userName}</a></li>
			 	</c:if>
			 	
			 </ul>	
		</div>
	</div>
</nav>
<!-- 导航 -->

<!-- home -->
<div class="container" style="margin-top: 80px;">
	<div class="row" style="margin-bottom: 50px;">
		<div class="col-md-2">
			<div class="panel panel-default operation-panel" style="position: fixed;">
			 <div class="panel-heading text-center">操作面板</div>
			 <div class="panel-body">
			    <button type="button" class="add-question-btn btn btn-default btn-block" data-target="#add-question-modal" data-toggle="modal">添加问答题</button>
			    <button type="button" class="add-single-btn btn btn-default btn-block" data-target="#add-single-modal" data-toggle="modal">添加单选题</button>
			    <button type="button" class="add-multiple-btn btn btn-default btn-block" data-target="#add-multiple-modal" data-toggle="modal">添加多选题</button>
			    <div class="text-center" style="margin-top: 6px;">
			    <button type="button" class="save btn btn-default btn-success">保存</button> 
			    <button type="button" class="clear btn btn-default btn-danger">清除</button>
			    <button type="button" class="publish btn btn-default btn btn-info" style="margin-top: 4px;">发布</button>		
			    </div>
			 </div>
			</div>
		</div>
		<div class="col-md-1"></div>		
		<div class="col-md-9" id="paper-main">
            <div class="row">
    			<h3 id="paper-title" class="text-center">点击修改问卷名称</h3>
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<form id="problems" class="form-group">
	                  
	                </form>		
				</div>
				<div class="col-md-2"></div>
            </div>
		</div>
	</div>
</div>

<!-- home -->


<!-- 修改问卷题目模态框 -->
<div class="modal fade" id="revisde-paper-title-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" style="text-align:center;">修改问卷标题</h4>
            </div>
            <div class="modal-body">	
             	<div class="form-group">
                    <label for="">请输入新的问卷名称</label>
                    <input class="form-control" id="new-paper-title" type="text" value="">
                </div>
                <div class="text-right">
                    <button class="btn btn-primary" id="new-paper-title-ok">确定</button>
                    <button class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 修改问卷题目模态框 -->


<!-- 添加问答题模态框 -->
<div class="modal fade" id="add-question-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" style="text-align:center;">添加问答题</h4>
            </div>
            <div class="modal-body">
             	<div class="form-group">
                    <label for="">在输入框中输入问题名称</label>
                    <input class="form-control" id="input-question-title" name="input-question-title" type="text" value="">
                </div> 
                <div class="text-right">
                    <button class="btn btn-primary" id="add-question-ok">确定</button>
                    <button class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 添加问答题模态框 -->


<!-- 添加单选题模态框 -->
<div class="modal fade" id="add-single-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" style="text-align:center;">添加单选题</h4>
            </div>
            <div class="modal-body">
            	<p>分别在下面的输入框中输入问题的名称以及选项, 选项用半角反斜杠"\"分隔开</p>
            	 
             	<div class="form-group">
                    <label>输入题目标题</label>
                    <input class="form-control" id="input-single-title" name="input-single-title" type="text" value="">
                    <label>输入选项</label>
                    <input class="form-control" id="input-single-answer" name="input-single-answer" type="text" placeholder="选项1\选项2\选项3" value="">
                </div>
                
                <div class="text-right">
                    <button class="btn btn-primary" id="add-single-ok">确定</button>
                    <button class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 添加单选题模态框 -->

<!-- 添加多选题模态框 -->
<div class="modal fade" id="add-multiple-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" style="text-align:center;">添加多选题</h4>
            </div>
            <div class="modal-body">
            	<p>分别在下面的输入框中输入问题的名称以及选项, 选项用半角反斜杠"\"分隔开</p>
            	
                 	<div class="form-group">
                        <label for="">输入题目标题</label>
                        <input class="form-control" id="input-multiple-title" name="input-multiple-title" type="text" value="">
                        <label for="">输入选项</label>
                        <input class="form-control" id="input-multiple-answer" name="input-multiple-answer" type="text" placeholder="选项1\选项2\选项3" value="">
                    </div>
               
                <div class="text-right">
                    <button class="btn btn-primary" id="add-multiple-ok">确定</button>
                    <button class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 添加多选题模态框 -->






<!-- 登录模态框 -->
<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" style="text-align:center;">登录</h4>
            </div>
            <div class="modal-body">
				 <div id="login-return-msg" class="alert alert-danger" role="alert" style="display: none;"></div>
            	 <form id="login-form" class="form-group">
                 	<div class="form-group">
                        <label for="">用户名</label>
                        <div class="login-danger-msg" style="color: red; display: none;">用户名不能为空!</div>
                        <input class="form-control" id="login-user" name="user" type="text" value="">
                    </div>
                    <div class="form-group">
                        <label for="">密码</label>
                        <div class="login-danger-msg" style="color: red; display: none;"></div>
                        <input class="form-control" id="login-pw" name="pw" type="password" value="">
                    </div>
                </form>
                  <div class="text-right">
                         <button class="btn btn-primary" id="login-button" >登录</button>
                         <button class="btn btn-danger" data-dismiss="modal">取消</button>
                  </div>
                  <a href="" data-toggle="modal" data-dismiss="modal" data-target="#RegisterModal">还没有账号？点我注册</a>
            </div>
        </div>
    </div>
</div>
<!-- 登录模态框 -->


<!-- 注册模态框 -->
<div class="modal fade" id="RegisterModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" style="text-align:center;">注册</h4>
            </div>
            <div class="modal-body">
           		<div id="register-return-msg" class="alert alert-danger" role="alert" style="display: none;"></div>
            	<form class="form-group">
                    <div class="form-group">
                        <label for="">用户名</label>
                        <div id="register-user-msg" class="register-msg" style="color: red; display: none;"></div>
                        <input id="register-user" class="form-control" type="text" name="register-user" placeholder="6-15位字母或数字">
                    </div>
                    <div class="form-group">
                        <label for="">密码</label>
                        <div id="register-pw-msg" class="register-msg" style="color: red; display: none;"></div>
                        <input id="register-pw" class="form-control" type="password" name='register-pw' placeholder="至少6位">
                    </div>
                    <div class="form-group">
                        <label for="">再次输入密码</label>
                        <div id="register-re-pw-msg" class="register-msg" style="color: red; display: none;"></div>
                        <input id='register-re-pw' class="form-control" type="password" name='register-re-pw' placeholder="至少6位">
                    </div>
                    <div class="form-group">
                        <label for="">邮箱</label>
                        <div id="register-email-msg" class="register-msg" style="color: red; display: none;"></div>
                        <input id='register-email' class="form-control" type="email" name='register-email' placeholder="例如:123@123.com">
                    </div>     
                </form>
                <div class="text-right">
                        <button class="btn btn-primary" id="register-button">提交</button>
                        <button class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
                <a href="" data-toggle="modal" data-dismiss="modal" data-target="#LoginModal">已有账号？点我登录</a>
            </div>
        </div>
    </div>
</div>
<!-- 注册模态框 -->


<!-- 引入js-->
<script src="js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/LoginRegister.js" type="text/javascript"></script>
<script src="js/createPaper.js" type="text/javascript"></script>
</body>
</html>