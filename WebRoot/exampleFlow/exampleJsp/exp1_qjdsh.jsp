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
	var hrefpath = '<%=path%>';
	function qjsubmit() {
		$.post("example_updateQjd", {
			taskid : $("#tid").val(),
			spyj : $("#spyj").val(),
			spyjty:$("input[name='spyjty']:checked").val()
		}, function(data, textStatus) {
			if (data == 'true') {
				$.messager.alert('提示', '审批成功!', 'info');
				window.location.href = hrefpath+"/exampleFlow/taskList.jsp";
			} else {
				$.messager.alert('错误提示', '审批失败!', 'error');
			}
		});
	}
	function cleardata() {
		$('#myform').form('clear');
	}
	function yjclick(rvalue) {
		$('#spyj').attr("value",rvalue);
	}
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
				hrefpath+"/exampleFlow/exampleJsp/exp1_qjdyj.jsp?processDefId=" + processDefId);
		$('#dd').dialog('open');
	}
	function close1() {
		$('#dd').dialog('close');
	}
</script>

<body>

	<form id="myform" method="post" >
		<table align="center">
			<tr>
				<td colspan="2" align="center"><h2>请假审批</h2>
				</td>
			</tr>
			<tr>
				<td>申请人：</td>
				<td>${tqjd.qjr}</td>
				<td align="right">请假天数：</td>
				<td>${tqjd.qjts} 天</td>
			</tr>
			<tr>
				<td>请假理由：</td>
				<td colspan="3">${tqjd.qjly}</td>
			</tr>
			<tr>
				<td>审批：</td>
				<td colspan="3"><input type="radio" name="spyjty" value="yes" onclick="yjclick('同意')" checked="checked">同意<input type="radio" name="spyjty" value="no" onclick="yjclick('不同意')">不同意</td>
			</tr>
			<tr>
				<td>审批意见：</td>
				<td colspan="3"><textarea id="spyj" name="qjly" style="height:60px;width: 300px;">同意</textarea></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="open1(${processinsid})">查看审批过程</a>
				<a class="easyui-linkbutton"
					iconCls="icon-ok" href="javascript:void(0)" onclick="qjsubmit()">确定</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="cleardata();">重置</a>
					
				</td>
			</tr>
		</table>
		<div id="dd" icon="icon-save"
			style="width: 550px; height: 400px;" align="center">
			<iframe scrolling="auto" id='myIframe' frameborder="0" src=""
				style="width:100%;height:100%;">
		</div>
		<input type="hidden" id="tid" name="taskid" value="<s:property value='#tqjd.taskid' />"/>
	</form>
</body>
</html>
