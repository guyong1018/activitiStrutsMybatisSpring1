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
<link rel="stylesheet" type="text/css"
	href="jqueryjs/themes/default/easyui.css">
<script type="text/javascript" src="jqueryjs/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jqueryjs/jquery.easyui.min.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		var lastIndex;
		var processdefid = '<%=processDefId%>';
		$('#tt').datagrid(
				{
					url : 'activiti_getActivityList?processDefId='
							+ processdefid,
					title : '流程定义列表',
					width : fixWidth(0.9),
					height : 'auto',
					fitColumns : true,
					columns : [ [ {
						field : 'activityid',
						title : '活动ID',
						width : 120,
						align : 'center'
					}, {
						field : 'activityname',
						title : '活动名称',
						width : 80,
						align : 'center'
					}, {
						field : 'processname',
						title : '流程名称',
						width : 80,
						align : 'center'
					}, {
						field : 'formurl',
						title : '页面URL',
						width : 220,
						align : 'left',
						editor : 'text'
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
					},
					toolbar : [
							{
								text : '返回',
								iconCls : 'icon-undo',
								handler : function() {
									window.location.href = "../processDef.jsp";
								}
							},
							'-',
							{
								text : '保存',
								iconCls : 'icon-save',
								handler : function() {
									var rows = $('#tt').datagrid('getChanges');
									var var_json = '[';
									for ( var i = 0; i < rows.length; i++) {
										var_json = var_json + '{';
										var_json = var_json + '"activityid":"'
												+ rows[i].activityid + '",';
										var_json = var_json + '"formurl":"'
												+ rows[i].formurl + '",';
										var_json = var_json
												+ '"activityname":"'
												+ rows[i].activityname + '",';
										var_json = var_json + '"processname":"'
												+ rows[i].processname + '",';
										var_json = var_json
												+ '"processdefid":"'
												+ processdefid + '"';
										var_json = var_json + '}';
										if (i < rows.length - 1)
											var_json = var_json + ',';
									}
									var_json = var_json + ']';
									$('#tt').datagrid('acceptChanges');
									addActivity(var_json);
									//alert('changed rows: ' + rows.length + ' lines');
								}
							} ],
					onBeforeLoad : function() {
						$(this).datagrid('rejectChanges');
					},
					onClickRow : function(rowIndex) {
						if (lastIndex != rowIndex) {
							$('#tt').datagrid('endEdit', lastIndex);
							$('#tt').datagrid('beginEdit', rowIndex);
						}
						lastIndex = rowIndex;
					}
				});
	});

	function fixWidth(percent) {
		return document.body.clientWidth * percent; //这里你可以自己做调整  
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

	function addActivity(var_json) {
		$.post("activiti_saveForm", {
			var_act : var_json,
			state : "jquery"
		}, function(data, textStatus) {
			if (data == 'true') {
				$.messager.alert('信息提示', '保存成功!', 'info');
				return false;
			} else {
				$.messager.alert('信息提示', '保存错误!', 'error');
			}
		});
	}
</script>
<body>
	说明：注册表单页面给用户任务类型的节点注册表单，可以写struts的action地址，也可以以WebRoot为根路径写jsp页面的名称。
	都会传递参数taskid和processinsid。
	<br><br>
	<table id="tt" style="width:700px;height:auto"
		data-options="iconCls:'icon-edit',singleSelect:true,idField:'itemid'"
		title="活动定义列表">
	</table>
</body>
</html>
