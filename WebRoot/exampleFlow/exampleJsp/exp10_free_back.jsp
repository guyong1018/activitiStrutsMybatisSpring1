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
		  $.post("example_startFreeFlowBack", {
				
			}, function(data, textStatus) {
				if (data == 'true') {
					$.messager.alert('提示', '启动成功!', 'info');
					window.location.href = hrefpath+"/exampleFlow/taskList.jsp";
				} else {
					$.messager.alert('错误提示', '启动失败!', 'error');
				}
			});
	}
</script>

<body>

	<form id="myform" action="">
		<font color="red">自由流回退流程示例：</font>动态创建转移线，自由流转
		<br><br>
		<table align="center">
			<tr>
				<td align="center" colspan="2">
				<br><br><br>
				<a class="easyui-linkbutton"
					iconCls="icon-ok" href="javascript:void(0)" onclick="ptfzsubmit()">启动流程</a>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>
