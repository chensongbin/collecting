<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ZH-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>易收集</title>
	<link rel="shortcut icon" href="/collecting2.0/img/icon.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="/collecting2.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/collecting2.0/css/bootstrap-collecting.css">
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
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-left">
				<li><a href="/collecting2.0/index.jsp">首页</a></li>
			 	<li><a href="/collecting2.0/createPaper.jsp">创建问卷</a></li>
			 	<li><a href="/collecting2.0/GetAllPaper">我的问卷</a></li>
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
<div class=container style="margin-top: 100px;">
<div class="jumbotron">
  <h2>请先登录！</h2>
</div>
</div>
<!-- home -->


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


<!-- 引入js -->
<script src="/collecting2.0/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="/collecting2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/collecting2.0/js/LoginRegister.js" type="text/javascript"></script>
</body>
</html>