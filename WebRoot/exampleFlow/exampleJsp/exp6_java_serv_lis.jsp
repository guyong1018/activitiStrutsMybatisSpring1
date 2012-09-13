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
		  $.post("example_startJavaSerLisInvoke", {
				v_flow : $("#flow").val()
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
		<font color="red">调用脚本、java类、监听流程示例：</font>可以调用js脚本和groovy脚本；
		调用java类时，java类必须实现JavaDelegate接口，可以通过类全路径调用。
		也可以通过spring中的beanID调用。还可以调用类中的方法；
		可以给转移线和节点添加监听，转移线监听类实现JavaDelegate接口，用户任务监听类要实现TaskListener接口，转移线只有转移时触发。用户任务可以再创建时、分配责任人时、完成工作项时触发。
		java调用和监听均可给java类中传递流程中的变量，java类中可对变量进行修改
		<br><br>
		<table align="center">
			<tr>
				<td align="center">
					v_flow值
				</td>
				<td align="center">
					<input type="text" id="flow" name="v_flow" value="测试flow" />      
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><a class="easyui-linkbutton"
					iconCls="icon-ok" href="javascript:void(0)" onclick="ptfzsubmit()">启动流程</a>
				</td>
			</tr>
		</table>
		流程说明：流程跑完控制台打印如下：控制台打印的语句均为调用的java类或监听所打印，都是读取全局变量，给全局变量赋值所用
		<br>------java类调用---star,将v_flow值修改为java1-Example6_javaInvoke1--    <font color="red">//最初v_flow的值是流程启动输入的值测试flow，通过java类全路径调用的方式将v_flow值改为java1</font>
		<br>exp1(v_flow) = 测试flow
		<br>#####读取变量开始#####
		<br>v_flow=测试flow
		<br>#####读取变量结束#####
		<br>------java类调用---end,将v_flow值修改为java1--------------------
		<br>
		<br>
		<br>------java类调用---star,将v_flow值修改为2-Example6_javaInvoke2spring--  <font color="red">//通过spring的beanID方式调用java类，类中将v_flow修改为2，走排他分支</font>
		<br>#####读取变量开始#####
		<br>v_flow=java1
		<br>#####读取变量结束#####
		<br>------java类调用---end,将v_flow值修改为2-Example6_javaInvoke2spring--
		<br>
		<br>
		<br>getMessage........................    <font color="red">//通过spring的bean方式调用bean中的方法getMessage</font>
		<br>
		<br>
		<br>------转移线take监听调用---star--Example6_listener4-    <font color="red">//转移线监听类读取变量信息</font>
		<br>#####读取变量开始#####
		<br>v_flow=2
		<br>#####读取变量结束#####
		<br>------转移线take监听调用---end----------------
		<br>
		<br>
		<br>----节点assignment监听调用--star-将v_flow值改为listener2-Example6_listener2-   <font color="red">//节点assignment监听触发，将v_flow修改为listener2，此节点前有一个脚本任务，用js脚本将v_flow修改为了js调用修改，并创建了一个新的变量js_var</font>
		<br>Assignee = 1
		<br>#####读取变量开始#####
		<br>v_flow=js调用修改
		<br>js_var=2
		<br>#####读取变量结束#####
		<br>----节点assignment监听调用--end--Example6_listener2-
		<br>
		<br>
		<br>-----节点create监听调用--star--将v_flow值改为listener1-Example6_listener1-   <font color="red">//节点create监听触发，将v_flow值改为listener1</font>
		<br>Assignee = 1
		<br>#####读取变量开始#####
		<br>v_flow=listener2
		<br>js_var=2
		<br>#####读取变量结束#####
		<br>-----节点create监听调用--end--将v_flow值改为listener1------------------
		<br>
		<br>
		<br>------节点complete监听调用---star-将v_flow值改为4-Example6_listener3-    <font color="red">//节点complete监听触发，将v_flow值改为4，走排他分支</font>
		<br>Assignee = 1
		<br>#####读取变量开始#####
		<br>v_flow=listener1
		<br>js_var=2
		<br>#####读取变量结束#####
		<br>------节点complete监听调用---end-------------------------
		

	</form>
</body>
</html>
