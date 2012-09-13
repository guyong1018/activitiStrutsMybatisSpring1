<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	function login() {
		$.post("login.action", {
			username : $("#myname").val(),
			password : $("#mypwd").val(),
			state : "jquery"
		}, function(data, textStatus) {
			if (data == 'false') {
				$.messager.alert('错误提示', '用户名或密码有误!', 'error');
				return false;
			} else {
				$("#myform").submit();
			}
		});
	}
	function cleardata(){
	    $('#myform').form('clear');
	}
</script>


<body>
	<div id="loginWin" class="easyui-window" title="登录"
		style="width:350px;height:188px;padding:5px;" minimizable="false"
		maximizable="false" resizable="false" collapsible="false">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding:5px;background:#fff;border:1px solid #ccc;">
				<form action="login.action" id="myform">
					<div style="padding:5px 0;">
						<label for="username">用户名:</label> <input id="myname"
							class="easyui-validatebox" type="text" name="username"
							data-options="required:true" size="30" missingMessage="用户名必填"></input>
					</div>
					<div style="padding:5px 0;">
						<label for="password">密　码:</label> <input id="mypwd"
							class="easyui-validatebox" size="32" type="password"
							name="password" data-options="required:true"
							missingMessage="密码必填"></input>
					</div>
					<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg"></div>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align:right;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:void(0)" onclick="login()">登录</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="cleardata()">重置</a>
			</div>
		</div>
	</div>
</body>
</html>
