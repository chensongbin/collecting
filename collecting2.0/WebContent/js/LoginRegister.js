//登陆验证
$('#login-button').click(function () {
	$(".login-danger-msg").css("display", "none");
	var loginPw = $('#login-pw').val();
	if(loginPw.length==0){
		var pwMsg = $($(".login-danger-msg")[1]);
		pwMsg.css("display", "block");
		pwMsg.text("密码不能为空！");
		$('#login-pw')[0].focus();
	}
	var loginUser = $('#login-user').val();
	if(loginUser.length==0){
		var userMsg = $($(".login-danger-msg")[0])
		userMsg.css("display", "block");
		userMsg.text("用户名不能为空");
		$('#login-user')[0].focus();
	}
	//如果前端验证成功,ajax发送用户名和密码
	if(loginUser.length>0 && loginPw.length>0){
		$.ajax({
			type:"post",
			url:"/collecting2.0/LoginCheck",
			data:"user="+loginUser+"&pw="+loginPw,
			async:false,
			success:function(data) {
				if(data=="success"){
					location.reload();
				}else{
					$('#login-return-msg').text(data);
					$('#login-return-msg').css("display", "block");
				}
			}
		});
	}
})

//注册验证
$("#register-button").click(function () {
	$('.register-msg').css("display","none");
	var msg;
	var send = true;
	//邮箱格式检测
	var registerEmail = $('#register-email').val();
	if(registerEmail.length==0){
		msg = "邮箱不能为空！";
		$('#register-email-msg').text(msg);
		$('#register-email-msg').css("display","block");
		$('#register-email')[0].focus(); 
		send = false;
	}else{
		var regEmail = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); 
		if(!regEmail.test(registerEmail)){
			msg = "邮箱格式不正确！";
			$('#register-email-msg').text(msg);
			$('#register-email-msg').css("display","block");
			$('#register-email')[0].focus();
			send = false;
		}
	}
	//密码检测
	var registerPw = $('#register-pw').val();
	var registerRePw = $('#register-re-pw').val();
	if(registerPw!=registerRePw){
		msg="两次输入的密码不一致！";
		$('#register-re-pw-msg').text(msg);
		$('#register-re-pw-msg').css("display", "block");
		$('#register-re-pw')[0].focus();
		send = false;
	}
	if(registerPw.length<6){
		msg="密码长度不符合要求！";
		$('#register-pw-msg').text(msg);
		$('#register-pw-msg').css("display", "block");
		$('#register-pw')[0].focus();
		send = false;
	}
	//用户名检测
	var registerUser = $('#register-user').val();
	if(registerUser.length==0 || registerUser.length<6 || registerUser.length>15){
		msg="用户名长度不符合要求！";
		$('#register-user-msg').text(msg);
		$('#register-user-msg').css("display", "block");
		$('#register-user')[0].focus();
		send = false;
	}else{
		var reg = /^[0-9a-zA-Z]+$/;
		if(!reg.test(registerUser)){
			msg="用户名只能由字母或数字组成";
			$('#register-user-msg').text(msg);
			$('#register-user-msg').css("display", "block");
			$('#register-user')[0].focus();
			send = false;
		}
	}
	
	//如果前端基本验证成功，发送到后端
	if(send){
		$.ajax({
			type:"post",
			url:"/collecting2.0/register",
			data:"registerUser="+registerUser+"&registerPw="+registerPw+"&registerEmail="+registerEmail,
			async:false,
			success:function(data) {
				if(data=="success"){
					location.assign("/collecting2.0/tips/registerSuccess.jsp");
				}else{
					//调试 console.log(data);
					$('#register-return-msg').text(data);
					$('#register-return-msg').css("display", "block");
				}
			}
		});
	}
})