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
	$(function() {
		$("#st").datebox({
			formatter : formatD
		});
		$("#en").datebox({
			formatter : formatD
		});
	});
	function qjsubmit() {
		var hrefpath = '<%=path%>';
		$.post("example_saveQjd", {
			dstar : $("#st").datebox('getValue'),
			dend : $("#en").datebox('getValue'),
			qjly : $("#ly").val()
		}, function(data, textStatus) {
			if (data == 'true') {
				$.messager.alert('提示', '申请成功!', 'info');
				window.location.href = hrefpath+"/exampleFlow/taskList.jsp";
			} else {
				$.messager.alert('错误提示', '请假单申请失败!', 'error');
			}
		});
	}
	function cleardata() {
		$('#myform').form('clear');
	}
	function formatD(date) {
		return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
	}
</script>

<body>

	<form id="myform" action="">
		<font color="red">请假流程示例：</font>流程参与者角色有三个角色，申请人、部门经理、总经理，3天以内部门经理审批，3天以上总经理审批。
		其中部门经理为test2，总经理为test3.
		<br><br>
		<table align="center">
			<td colspan="2" align="center"><h2>请假申请</h2>
			</td>
			<tr>
				<td>请假天数：</td>
				<td><input id="st" name="dstar" type="text" class="easyui-datebox"
					required="required" missingMessage="开始日期必填"></input> 至 <input id="en" name="dend" type="text"
					class="easyui-datebox" required="required" missingMessage="结束日期必填"></input></td>
			</tr>
			<tr>
				<td>请假理由：</td>
				<td><textarea id="ly" name="qjly" style="height:60px;width: 300px;"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><a class="easyui-linkbutton"
					iconCls="icon-ok" href="javascript:void(0)" onclick="qjsubmit()">确定</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="cleardata()">重置</a>
				</td>
			</tr>
		</table>


	</form>
</body>
</html>
