<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css"
	href="jqueryjs/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jqueryjs/themes/icon.css">
<script type="text/javascript" src="jqueryjs/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jqueryjs/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-1.7.2.js"></script>
</head>
<script type="text/javascript">
	
	function ptfzsubmit(){  
		var hrefpath = '<%=path%>';
		  $.post("example_startMailRec", {
				
			}, function(data, textStatus) {
				if (data == 'true') {
					$.messager.alert('提示', '启动成功!', 'info');
					window.location.href = hrefpath+"/exampleFlow/taskList.jsp";
				} else {
					$.messager.alert('错误提示', '启动失败!', 'error');
				}
			});
	}
	function sendMsg(){  
		var hrefpath = '<%=path%>';
		  $.post("example_sendReceiveTaskMsg", {
				
			}, function(data, textStatus) {
				if (data == 'true') {
					$.messager.alert('提示', '操作成功!', 'info');
					window.location.href = hrefpath+"/exampleFlow/taskList.jsp";
				} else {
					$.messager.alert('错误提示', '操作失败!', 'error');
				}
			});
	}    
</script>

<body>

	<form id="myform" action="">
		<font color="red">手工、接收触发、邮件任务流程示例：</font>手工任务即不需要计算机完成的任务，流程会直接通过，只是描述流程时容易理解而用。
		接收触发任务，类似于消息驱动方式，到该节点会一直等待，知道程序给出一个指令才自动向下走。邮件任务发送邮件。这三个节点任务都不需要人工参与。
		<br><br>
		<table align="center">
			<tr>
				<td align="center" colspan="2">
				<br><br><br>
				<a class="easyui-linkbutton"
					iconCls="icon-ok" href="javascript:void(0)" onclick="ptfzsubmit()">启动流程</a>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<br><br><br>
				当流程流转到接收消息任务时，点击此按钮驱动流转
				<br><a class="easyui-linkbutton"
					iconCls="icon-ok" href="javascript:void(0)" onclick="sendMsg()">发送消息驱动接收触发节点流转</a>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>
