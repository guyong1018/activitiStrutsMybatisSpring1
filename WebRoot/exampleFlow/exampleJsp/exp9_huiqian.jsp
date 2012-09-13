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
		  $.post("example_startHuiqian", {
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
		<font color="red">会签流程示例(参考流程定义中的流程图)：</font>activiti中使用多实例可以实现会签，提供两种，并行会签和串行会签。
		activiti提供三个变量存储会签实例个数：
		<br>nrOfInstances：实例总数。 
  	<br>nrOfActiveInstances：当前活跃的，也就是还没完成的，实例的个数。对于顺序的多实例，该值总是 1。 
  	<br>nrOfCompleteInstances：已经完成的实例的个数。 
		<br>一般流程设置nrOfCompletedInstances/nrOfInstances == 1，代表所有人都处理完了继续流转。
		<br>activiti会给每个实例创建一个局部变量(Element variable)，使用集合变量(Collection)存储会签人员，每个实例的局部变量分配一个集合变量的人员。
		<br><br>
		<table align="center">
			<tr>
				<td align="center">
					选择会签参与者:
				</td>
				<td align="center">
					<input type="checkbox" name="test" value="1" />测试1      
 					<input type="checkbox" name="test" value="2" />测试2      
 					<input type="checkbox" name="test" value="3" />测试3      
 					<input type="checkbox" name="test" value="4" />测试4
 					<input type="checkbox" name="test" value="5" />测试5
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
