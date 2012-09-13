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
		  var chk_value =[];    
		  $('input[name="test"]:checked').each(function(){    
		   	chk_value.push($(this).val());    
		  });    
		  if(chk_value.length==0){
			  $.messager.alert('提示', '你还没有选择竞争节点参与者!', 'info');
			  
		  }
		  $.post("example_startFzPtfz", {
				v_flow : $("#flow").val(),
				userids : chk_value
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
		<font color="red">分支-排他分支流程示例(参考流程定义中的流程图)：</font>排他分支在分发时，只允许走一条分支，如果表达式不满足，则流转配置的默认分支，如果没有配置默认分支，在表达式不满足时会报错。
		而在汇聚时，只要有一条分支到达汇聚点则流程就向下流转。
		图中如果竞争节点完成，将选择一个分支（如test2）和test4并行流转，如果test4和test2都完成了工作项，则test5就有两个工作项。
		<br><br>
		<table align="center">
			<tr>
				<td align="center">
					选择竞争节点参与者:
				</td>
				<td align="center">
					<input type="checkbox" name="test" value="1" />测试1      
 					<input type="checkbox" name="test" value="2" />测试2      
 					<input type="checkbox" name="test" value="3" />测试3      
 					<input type="checkbox" name="test" value="4" />测试4
				</td>
			</tr>
			<tr>
				<td align="center">
					流程走向(1/2/3)
				</td>
				<td align="center">
					<input type="text" id="flow" name="v_flow" value="" />      
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><a class="easyui-linkbutton"
					iconCls="icon-ok" href="javascript:void(0)" onclick="ptfzsubmit()">启动流程</a>
				</td>
			</tr>
		</table>


	</form>
</body>
</html>
