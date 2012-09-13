<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String strtaskid = request.getParameter("taskid");
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
		var taskid = '<%=strtaskid%>';
		  $.post("example_completeFzPtfz", {
				taskid : taskid
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
		<table align="center">
			<tr>
				<td align="center" colspan="2"><br><br><br><a class="easyui-linkbutton"
					iconCls="icon-ok" href="javascript:void(0)" onclick="ptfzsubmit()">完成任务</a>
				</td>
			</tr>
		</table>


	</form>
</body>
</html>
