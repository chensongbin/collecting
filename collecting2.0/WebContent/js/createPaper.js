var index = 0;

//添加修改问卷名称的模态框
$('#paper-title').attr("data-toggle", "modal");
$('#paper-title').attr("data-target", "#revisde-paper-title-modal");
$('#new-paper-title-ok').click(function() {
	$('#paper-title').text($('#new-paper-title').val());
	$('#revisde-paper-title-modal').modal("hide");
})

//上移操作
function up() {
	var curProblem = $(this).parent().parent();
	var preProblem = curProblem.prev();
	if(preProblem.hasClass('form-group')){
		curProblem.insertBefore(preProblem);
	}
}
$('.up-btn').click(up);
//下移操作
function down() {
	var curProblem = $(this).parent().parent();
	var nextProblem = curProblem.next();
	if(nextProblem.hasClass('form-group')){
		curProblem.insertAfter(nextProblem);
	}
}
$('.down-btn').click(down);
//删除操作
function del() {
	$(this).parent().parent().remove();
}
$('.del-btn').click(del);


//添加问答题
$('#add-question-ok').click(function() {
	var problems = $("#problems");
	var title = $('#input-question-title').val();
	index++;
	var name = "p" + index.toString();
	var question = "<div class='form-group'><label class='title'>" + title + "</label><input class='form-control' type='text' name='" + name + "'><div style='padding-top: 8px;' class='text-center btns'><button type='button' class='btn btn-default up-btn'>上移</button><button type='button' class='btn btn-default del-btn'>删除</button><button type='button' class='btn btn-default down-btn'>下移</button></div></div>";
	problems.html(problems.html()+question);
	$('.up-btn').click(up);
	$('.del-btn').click(del);
	$('.down-btn').click(down);
	$('#add-question-modal').modal("hide");
});
//添加单选题
$("#add-single-ok").click(function () {
	var problems = $("#problems");
	var title = $('#input-single-title').val().trim();
	index++;
	var name = "p" + index.toString();
	var single = "<div class='form-group'><label class='title'>" + title + "</label>";
	var answer = $('#input-single-answer').val();
	answer.trim();
	var answers = answer.split("\\");
	for(var i=0;i<answers.length;++i){
		single += "<label class='radio'><input type='radio' value='" + answers[i].trim() + "' name='" + name + "'>" + answers[i].trim() + "</label>";
	}
	single += "<div style='padding-top: 8px;' class='text-center btns'><button type='button' class='btn btn-default up-btn'>上移</button><button type='button' class='btn btn-default del-btn'>删除</button><button type='button' class='btn btn-default down-btn'>下移</button></div></div>"
	problems.html(problems.html()+single);
	$('.up-btn').click(up);
	$('.del-btn').click(del);
	$('.down-btn').click(down);
	$('#add-single-modal').modal("hide");                    
});
//添加多选题
$("#add-multiple-ok").click(function () {
	var problems = $("#problems");
	var title = $('#input-multiple-title').val().trim();
	index++;
	var name = "p" + index.toString();
	var multiple = "<div class='form-group'><label class='title'>" + title + "</label>";
	var answer = $('#input-multiple-answer').val();
	answer.trim();
	var answers = answer.split("\\");
	for(var i=0;i<answers.length;++i){
		multiple += "<label class='checkbox'><input style='zoom:150%' type='checkbox' value='" + answers[i].trim() + "' name='" + name + "'>" + answers[i].trim() + "</label>";
	}
	multiple += "<div style='padding-top: 8px;' class='text-center btns'><button type='button' class='btn btn-default up-btn'>上移</button><button type='button' class='btn btn-default del-btn'>删除</button><button type='button' class='btn btn-default down-btn'>下移</button></div></div>";
	problems.html(problems.html()+multiple);
	$('.up-btn').click(up);
	$('.del-btn').click(del);
	$('.down-btn').click(down);
	$('#add-multiple-modal').modal("hide");                  
});

//将日期改为mysql datetime 格式
function getMySQLDateTime(date)
{
    if(date == null)
    {
        date = new Date();
    }
    var y = date.getFullYear();
    var M = date.getMonth() + 1;
    var d = date.getDate();
    var h = date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    var str = y + "-";
    if(M < 10)
    {
        str += "0";
    }
    str += M + "-";
 
    if(d < 10)
    {
        str += "0";
    }
    str += d + " ";
    if(h < 10)
    {
        str += "0";
    }
    str += h + ":";
    if(m < 10)
    {
        str += "0";
    }
    str += m + ":";
    if(s < 10)
 	{
        str += "0";
    }
    str += s;
    return str;
}

//发送问卷到后台
function sendPaper(statusCode, msg) {
	var data = {};
	data.paperName = $('#paper-title').text();
	data.paperHTML=$("#problems").html().trim();
	data.paperStatus = statusCode;
	var problemTitles=[];
	var labels = $(".title");
	for(var i=0;i<labels.length;++i){
		problemTitles.push(labels[i].innerText);
	}
	var datetime = new Date();
	data.createDate = getMySQLDateTime(datetime);
	data.createTimeStamp = datetime.valueOf();
	data.titles = JSON.stringify(problemTitles);
	$.ajax({
	  type: 'POST',
	  url: '/collecting2.0/CreatePaperServlet',
	  data: data,
	  success: function () {
	  	alert(msg);
	  }
	});
}

//保存
function save() {
	sendPaper(0, "保存成功");
}
$('.save').click(save);

//发布
function publish() {
	sendPaper(1, "发布成功");
}
$('.publish').click(publish);

//清除
function clear() {
	$("#problems").html("");
}
$(".clear").click(clear);