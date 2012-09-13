<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String processDefId = request.getParameter("processDefId");
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
	$(function() {
		var proid = '<%=processDefId%>';
		$('#tt')
				.datagrid(
					{
						url : 'example_getQjdGcList?processinsid='+proid,
						title : '审批过程列表',
						width : 500,
						height : 'auto',
						fitColumns : true,
						columns : [ [
								{
									field : 'rwmc',
									title : '任务名称',
									width : 100,
									align : 'center'
								},
								{
									field : 'czr',
									title : '操作人',
									width : 80,
									align : 'center'
								},
								{
									field : 'spyj',
									title : '审批意见',
									width : 220,
									align : 'center'
								},
								{
									field : 'sj',
									title : '操作时间',
									width : 130,
									align : 'center'
								} ] ],
						onHeaderContextMenu : function(e, field) {
							e.preventDefault();
							if (!$('#tmenu').length) {
								createColumnMenu();
							}
							$('#tmenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						}
					});
	});
</script>

<body>
	<table id="tt"></table>
</body>
</html>
