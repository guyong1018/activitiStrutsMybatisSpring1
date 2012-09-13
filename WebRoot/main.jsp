<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.xjgzinfo.organizational.UserVo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserVo tuser = (UserVo)session.getAttribute("tuser"); 
	String uname = tuser.getName(); 
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
	function showpage(url) {
		//$('#content').load(url);
		$(window.parent.document).find("#mainIframe").attr("src", url);
	}
</script>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:40px;background:#E6EFFE;padding:5px">
		<table width="100%">
			<tr valign="middle">
				<td align="left"><b>JQuery+Struts+Activiti+Mybatis+Spring示例工程</b>
				</td>
				<td align="right">当前登录用户：<%=uname%></td>
				<td align="right"><a href="index.jsp">注销</a>
				</td>
			</tr>
		</table>

	</div>
	<div data-options="region:'west',split:true" title="导航"
		style="width:200px;padding1:1px;overflow:hidden;">
		<div class="easyui-accordion" data-options="fit:true,border:false">

			<div title="流程示例" style="padding:10px;">
				<a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp1_qjd.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />普通流转-请假
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp2_fzptfz.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />分支-排他分支
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp3_fzbxfz.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />分支-并行分支
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp4_fzbrfz.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />分支-包容分支
					</p> </a> <a href="javascript:void(0)" onclick="showpage('processDef.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />定时启动
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp6_java_serv_lis.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />调用脚本、java类、监听
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp7_rec_mail.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />手工、接收触发、邮件任务
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp8_sub_wbdy.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />子过程、边界事件、外部调用
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp9_huiqian.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />会签（多实例）
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp10_free_back.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />自由流回退
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/exampleJsp/exp11_org.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />参与者表达式
					</p> </a>
			</div>
			<div title="流程管理" style="padding:10px;overflow:auto;">
				<a href="javascript:void(0)"
					onclick="showpage('exampleFlow/taskList.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />待办事宜
					</p> </a> <a href="javascript:void(0)" onclick="showpage('processDef.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />流程定义列表
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/taskMonitorList.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />流程监控
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/deploy.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />流程部署
					</p> </a> <a href="javascript:void(0)"
					onclick="showpage('exampleFlow/taskHisList.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />已办事宜
					</p> </a><a href="javascript:void(0)"
					onclick="showpage('exampleFlow/taskMonitorList.jsp')"><p>
						<img border="0" align="top"
							src="jqueryjs/themes/default/images/tree_file.gif" />---
					</p> </a>
			</div>
		</div>
	</div>
	<div data-options="region:'center'" id="content" title="功能页面"
		style="overflow:hidden;">
		<iframe scrolling="auto" id='mainIframe' frameborder="0"
			src="exampleFlow/taskList.jsp" style="width:100%;height:100%;"></iframe>
	</div>

	<div data-options="region:'south',border:false"
	align="center"	style="height:20px;background:#E6EFFE;">公众信息-顾勇2012</div>
</body>
</html>