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
		$('#tt1').tree({
			animate : true,
			onClick : function(node) {
				//alert('you click ' + node.text);
			}
		});
		$('#tt2').tree({
			checkbox : true,
			url : 'example_getOrganizational',
			onClick : function(node) {
				//alert('you click ' + node.text);
			}
		});
	});

	function reload() {
		$('#tt2').tree('reload');
	}
	function getChildNodes() {
		var node = $('#tt2').tree('getSelected');
		if (node) {
			var children = $('#tt2').tree('getChildNodes', node.target);
			var s = '';
			for ( var i = 0; i < children.length; i++) {
				s += children[i].text + ',';
			}
			alert(s);
		}
	}
	function getChecked() {
		var nodes = $('#tt2').tree('getChecked');
		var s = '';
		for ( var i = 0; i < nodes.length; i++) {
			if (s != '')
				s += ',';
			s += nodes[i].id;
		}
		return s;
	}
	function getSelected() {
		var node = $('#tt2').tree('getSelected');
		alert(node.text);
	}
	function collapse() {
		var node = $('#tt2').tree('getSelected');
		$('#tt2').tree('collapse', node.target);
	}
	function expand() {
		var node = $('#tt2').tree('getSelected');
		$('#tt2').tree('expand', node.target);
	}
	function collapseAll() {
		$('#tt2').tree('collapseAll');
	}
	function expandAll() {
		$('#tt2').tree('expandAll');
	}
	function append() {
		var node = $('#tt2').tree('getSelected');
		$('#tt2').tree('append', {
			parent : node.target,
			data : [ {
				text : 'new1',
				checked : true
			}, {
				text : 'new2',
				state : 'closed',
				children : [ {
					text : 'subnew1'
				}, {
					text : 'subnew2'
				} ]
			} ]
		});
	}
	function remove() {
		var node = $('#tt2').tree('getSelected');
		$('#tt2').tree('remove', node.target);
	}
	function update() {
		var node = $('#tt2').tree('getSelected');
		if (node) {
			node.text = '<span style="font-weight:bold">new text</span>';
			node.iconCls = 'icon-save';
			$('#tt2').tree('update', node);
		}
	}
	function isLeaf() {
		var node = $('#tt2').tree('getSelected');
		var b = $('#tt2').tree('isLeaf', node.target);
		alert(b);
	}
	
	function ptfzsubmit(){  
		var var_ids = getChecked();
		if(var_ids.length==0){
			$.messager.alert('提示', '请选择参与者!', 'info');
			return;
		}
		var hrefpath = '<%=path%>';
		  $.post("example_startParticipant", {
			  strids : var_ids
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
		<font color="red">参与者表达式示例(参考流程定义中的流程图)：</font>参与者表达式是扩展功能，用户可以选择后面节点的参与者。
		可以选择部门、选择人员、也可以选择角色，后台构造表达式，P部门R角色U人员，表达式为P[1,2,3]+R[3]+U[8,9]，这个表达式代表：
		选择了id为1、2、3的部门和id为3的角色和id为8、9的用户。该表达式还可写为P[1]+P[2]+P[3]+R[3]+U[8]+U[9]。后台会遍历部门和角色的下级，读取出所有人员转换成人员列表。
		该示例后面节点是竞争用户关系。后台设置参与者变量，直接设置例如map.put("v_user","P[1,2,3]+R[3]+U[8,9]")，方便使用。

		<br><br><br>


		<table align="center">
			<tr>
				<td align="center">选择下一步参与者:</td>
			</tr>
			<tr>
				<td align="center">
					<ul id="tt2"></ul></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><br><br>
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="ptfzsubmit()">启动流程</a>
				</td>
			</tr>
		</table>


	</form>
</body>
</html>
