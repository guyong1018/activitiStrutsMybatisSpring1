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
<link rel="stylesheet" type="text/css"
	href="jqueryjs/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jqueryjs/themes/demo.css">
<script type="text/javascript" src="jqueryjs/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jqueryjs/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/workflow.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$('#tt')
				.datagrid(
						{
							url : 'activiti_getTaskHisList',
							title : '已办工作项列表',
							width : 930,
							height : 'auto',
							fitColumns : true,
							align : 'center',
							columns : [ [
									{
										field : 'taskid',
										title : '工作项ID',
										width : 80,
										align : 'center'
									},
									{
										field : 'procedefname',
										title : '流程名称',
										width : 250,
										align : 'center'
									},
									{
										field : 'taskname',
										title : '任务名称',
										width : 120,
										align : 'center'
									},
									{
										field : 'proinsid',
										title : '流程实例ID',
										width : 80,
										align : 'center'
									},
									{
										field : 'taskdefid',
										title : '活动定义ID',
										width : 80,
										align : 'center'
									},
									{
										field : 'assignee',
										title : '办理人',
										width : 80,
										align : 'center'
									},
									{
										field : 'createtime',
										title : '创建时间',
										width : 150,
										align : 'center'
									},
									{
										field : 'endtime',
										title : '结束时间',
										width : 150,
										align : 'center'
									}] ],
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
	function createColumnMenu() {
		var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo(
				'body');
		var fields = $('#tt').datagrid('getColumnFields');
		for ( var i = 0; i < fields.length; i++) {
			$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
		}
		tmenu.menu({
			onClick : function(item) {
				if (item.iconCls == 'icon-ok') {
					$('#tt').datagrid('hideColumn', item.text);
					tmenu.menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					$('#tt').datagrid('showColumn', item.text);
					tmenu.menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			}
		});
	}
</script>

<body>
	<h2>已办工作项列表</h2>
	<table id="tt"></table>
	<form id="myform" method="post">
		<input id="proid" type="hidden" name="processinsid">
		<input id="tid" type="hidden" name="taskid">
	</form>
	<div id="dd" icon="icon-save"
		style="padding: 5px; width: 800px; height: 500px;">
		<iframe scrolling="auto" id='openIframe' frameborder="0" src=""
			style="width:100%;height:100%;">
	</div>
</body>
</html>
