<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String taskid = request.getParameter("taskid");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jqueryjs/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jqueryjs/demo/demo.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jqueryjs/themes/default/easyui.css">
	
<link rel="stylesheet" type="text/css" href="<%=path%>/jqueryjs/themes/demo.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/qtip/jquery.qtip.css">

<script type="text/javascript" src="<%=path%>/jqueryjs/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/jqueryjs/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/qtip/jquery.qtip.pack.js"></script>
<script type="text/javascript" src="<%=path%>/js/qtip/jquery.qtip.js"></script>
</head>
<style>
.roundAct {
	font-family: Arial;
	border: 2px solid red;
	background:red;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	padding: 15px 25px;
	height: inherit;
	width: 590px;
}
.roundHis {
	font-family: Arial;
	border: 2px solid #87B711;
	background:#87B711;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	padding: 15px 25px;
	height: inherit;
	width: 590px;
}
.roundInfo {
	font-family: Arial;
	border: 2px solid #87B711;
	background:#87B711;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	padding: 15px 25px;
	height: inherit;
	width: 590px;
}
</style>
<script type="text/javascript">
	var taskid = '<%=taskid%>';
	$(function() {
		divImg(taskid);
	});
	function open1(taskid) {
		$("#openIframe", document.body).attr("src",
				"activiti_getMonitorImg?taskid=" + taskid);
		$('#dd').dialog('open');
	}

	function divImg(taskid) {
		$.post(
			"activiti_getDivXyImg",
			{
				taskid : taskid
			},
			function(data, textStatus) {

				var myobj = eval(data);
				//{"activityId":"startevent1","height":35,"width":35,"documentation":"","highlight":false,"type":"开始节点","y":180,"x":30}
				for ( var i = 0; i < myobj.length; i++) {
					var X = $('#myimg').offset().top + myobj[i].x;
					var Y = $('#myimg').offset().left + myobj[i].y;
					var act_id = myobj[i].activityId;
					var act_type = myobj[i].type;
					var act_doc = myobj[i].documentation;
					var act_starttime = myobj[i].createTime;
					var act_endtime = myobj[i].endTime;
					var act_user = myobj[i].taskUser;
					var infodiv_id = 'infodiv'+i;
					var oNewp;
					if (myobj[i].highlight) {//高亮
						//oNewp = $("<div id='div"+i+"' style='filter:alpha(opacity=20);background:red;width:"+myobj[i].width+"px;height:"+myobj[i].height+"px;left:"+X+"px; top:"+Y+"px;position:absolute;z-index:2' class='roundedd'></div>");
						oNewp = $("<div id='div"+i+"' style='filter:alpha(opacity=20);width:"+myobj[i].width+"px;height:"+myobj[i].height+"px;left:"+X+"px; top:"+Y+"px;position:absolute;z-index:2' class='roundAct'></div>");
						oNewp.insertAfter("#dd");
					} else if (myobj[i].his){//已办理
						//oNewp = $("<div id='div"+i+"' style='filter:alpha(opacity=30);background:#ACDD5F;width:"+myobj[i].width+"px;height:"+myobj[i].height+"px;left:"+X+"px; top:"+Y+"px;position:absolute;z-index:2' class='roundinfo'></div>");
						oNewp = $("<div id='div"+i+"' style='filter:alpha(opacity=30);width:"+myobj[i].width+"px;height:"+myobj[i].height+"px;left:"+X+"px; top:"+Y+"px;position:absolute;z-index:2' class='roundHis'></div>");
						oNewp.insertAfter("#dd");
					} else{//其他节点
						//oNewp = $("<div id='div"+i+"' style='filter:alpha(opacity=30);width:"+myobj[i].width+"px;height:"+myobj[i].height+"px;left:"+X+"px; top:"+Y+"px;position:absolute;z-index:2' class='roundinfo'></div>");
						oNewp = $("<div id='div"+i+"' style='filter:alpha(opacity=0);width:"+myobj[i].width+"px;height:"+myobj[i].height+"px;left:"+X+"px; top:"+Y+"px;position:absolute;z-index:2'  class='roundInfo'></div>");
						oNewp.insertAfter("#dd");
					}
					//任务节点提示
					oNewp.qtip({
				        content:{ 
				        	text: " <table class='need-border'><tr><td>责任人：</td><td>"+act_user+"</td></tr>"
						        +"<tr><td>创建时间：</td><td>"+act_starttime+"</td></tr>"
						        +"<tr><td>完成时间：</td><td>"+act_endtime+"</td></tr>"
						        +"<tr><td>任务ID：</td><td>"+act_id+"</td></tr>"
						        +"<tr><td>任务类型：</td><td>"+act_type+"</td></tr>"
						        +"<tr><td>任务描述：</td><td>"+act_doc+"</td></tr></table>"
				            , title: "任务提示"
				        	
				        	
				        }
				      });
					//oNewp = $("<div id='div"+i+"' style='filter:alpha(opacity=40);background:#ACDD5F;width:"+myobj[i].width+"px;height:"+myobj[i].height+"px;left:"+X+"px; top:"+Y+"px;position:absolute;z-index:2' class='roundinfo'></div>");
					//var infodiv = $("<div class='easyui-panel' style='width:300px;height:200px;padding:10px;position:absolute;z-index:5'  act_doc title='My Panel' data-options=\"iconCls:'icon-ok',tools:'#tt'\"><p>panel</p></div>");
					//var infodiv = $("<div id='"+infodiv_id+"' style='width:200px;height:150px;'><table width='100%'><tr><td>任务ID：</td><td>"+act_id+"</td></tr><tr><td>任务类型：</td><td>"+act_type+"</td></tr><tr><td>任务描述：</td><td>"+act_doc+"</td></tr></table></div>");
					//infodiv.text("<p>bbbbbbbbbbbb</p>");
					//infodiv.insertAfter("#dd");
					//alert($("#mybody").html());
				}
				$('#myimg').attr("src",
						"activiti_getMonitorImg?taskid=" + taskid);

				//			
			});
	};
	
	
</script>

<body id="mybody">
	<div id="dd" style="width:800px;height:500px;position:absolute;z-index:1">
		<img id="myimg" src="" />
	</div>
	
</body>
</html>
