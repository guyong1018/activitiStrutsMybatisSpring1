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
<script type="text/javascript" src="jqueryjs/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jqueryjs/jquery.easyui.min.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$('#tt')
				.datagrid(
						{
							url : 'processDef.action',
							title : '流程定义列表',
							width : 'auto',
							height : 'auto',
							fitColumns : true,
							columns : [ [
									{
										field : 'processdefid',
										title : '流程定义ID',
										width : 200,
										align : 'center'
									},
									{
										field : 'key',
										title : '流程定义key值',
										width : 150,
										align : 'center'
									},
									{
										field : 'name',
										title : '流程名称',
										width : 200,
										align : 'center'
									},
									{
										field : 'resourcename',
										title : '流程文件名称',
										width : 180,
										align : 'center'
									},
									{
										field : 'version',
										title : '流程版本',
										width : 60,
										align : 'center'
									},
									{
										field : 'deploymentid',
										title : '部署ID',
										width : 60,
										align : 'center'
									},
									{
										field : 'diagramresourcename',
										title : '图片名称',
										width : 200,
										align : 'center'
									},
									{
										field : 'deploytime',
										title : '部署时间',
										width : 180,
										align : 'center'
									},
									{
										field : 'option',
										title : '流程图',
										width : 80,
										align : 'center',
										formatter : function(value, row, index) {
											var s = "<a href='#' class='easyui-linkbutton' onclick=\"open1('"
													+ row.processdefid
													+ "');return false\">"
													+ value + "</a>";
											return s;
										}
									},
									{
										field : 'prodelete',
										title : '删除流程',
										width : 80,
										align : 'center',
										formatter : function(value, row, index) {
											var s = "<a href='#' class='easyui-linkbutton' onclick=\"deleteProceDef('"
													+ row.deploymentid
													+ "');return false\">"
													+ value + "</a>";
											return s;
										}
									},
									{
										field : 'pfrom',
										title : '注册表单',
										width : 80,
										align : 'center',
										formatter : function(value, row, index) {
											var s = "<a href='#' class='easyui-linkbutton' onclick=\"addform('"
													+ row.processdefid
													+ "');return false\">"
													+ value + "</a>";
											return s;
										}
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

	function fixWidth(percent) {
		return document.body.clientWidth * percent;
	}

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
<script type="text/javascript">
	$(function() {
		$('#dd').dialog({
			title : '对话框',
			collapsible : true,
			maximizable : true,
			resizable : true,
			buttons : [ {
				text : '关闭',
				iconCls : 'icon-ok',
				handler : function() {
					close1();
				}
			} ]
		});
		close1();
	});
	function open1(processDefId) {
		$("#myIframe", document.body).attr("src",
				"activiti_getProcessDefimg?processDefId=" + processDefId);
		$('#dd').dialog('open');
	}
	function close1() {
		$('#dd').dialog('close');
	}

	function addform(processDefId) {
		window.location.href = "exampleFlow/activityList.jsp?processDefId="
				+ processDefId;
	}
	function deleteProceDef(deployId) {
		
		$.messager.confirm('提示', '确认删除流程及相关流程实例?', function(r){
			if (r){
				$.post("activiti_deleteProcessDef", {
					deployId : deployId,
					state : "jquery"
				}, function(data, textStatus) {
					window.location.reload();
				});
			}
		});
	}
</script>
<body>
	<h2>流程定义列表</h2>
	<table id="tt"></table>
		<div id="dd" icon="icon-save"
			style="padding: 5px; width: 800px; height: 500px;">
			<iframe scrolling="auto" id='myIframe' frameborder="0" src=""
				style="width:100%;height:100%;">
		</div>
</body>
</html>
