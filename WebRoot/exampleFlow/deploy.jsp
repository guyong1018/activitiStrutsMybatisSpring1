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

<link rel="stylesheet" type="text/css"
	href="<%=path%>/jqueryjs/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jqueryjs/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jqueryjs/themes/default/easyui.css">
<script type="text/javascript" src="<%=path%>/jqueryjs/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/jqueryjs/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
</head>
<script type="text/javascript">
    function ajaxFileUpload()
    {
    	var hrefpath = '<%=path%>';
        $.ajaxFileUpload
        (
            {
                url:'fileUploadAction.action',
                secureuri:false,
                fileElementId:'file',
                dataType: 'json',
                success: function (data, status)  
                {
                	if(data=='1'){
                		$.messager.alert('提示', '必须是zip文件!', 'error');
                	}else if(data=='3'){
                		$.messager.alert('提示', '部署失败!', 'error');
                	}else if(data=='2'){
                		$.messager.alert('提示', '部署成功!', 'info');
                    	window.location.href = hrefpath+"/processDef.jsp";
                	}
                },
                error: function (data, status, e)
                {
                	$.messager.alert('提示', e, 'error');
                }
            }
        );
        return false;
    }
    </script>
    </head>
    <body>
        activiti 支持部署的文件格式：zip、bar、bpmn20.xml，此示例建议使用zip方式，将xml文件和png图片打成一个压缩包进行上传。
                     因为activiti自己的流程图坐标有问题，label不显示。<br>
        <input type="file" id="file" name="file" />
        <br/>
        <input type="button" value="部署流程" onclick="return ajaxFileUpload();">
    </body>
</html>
