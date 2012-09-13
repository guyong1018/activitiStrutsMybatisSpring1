package com.xjgzinfo.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.xjgzinfo.common.BaseAction;
import com.xjgzinfo.common.DateUtil;
import com.xjgzinfo.service.ProcessDef;

public class ProcessDefListAction extends BaseAction {

	private ProcessDef processDef;

	public void processDefList() {
		List<Map> resultListMap = processDef.getProcessDefList();
		JSONArray array = new JSONArray();
		if (resultListMap != null && resultListMap.size() > 0) {
			for (Map resultMap : resultListMap) {
				JSONObject json = new JSONObject();
				json.put("processdefid", resultMap.get("PDID"));
				json.put("key", resultMap.get("PDKEY"));
				json.put("name", resultMap.get("PNAME"));
				json.put("resourcename", resultMap.get("RESOURCE_NAME"));
				json.put("version", resultMap.get("VERSION"));
				json.put("deploymentid", resultMap.get("DEPLOYMENT_ID"));
				json.put("diagramresourcename", resultMap.get("DGRM_RESOURCE_NAME"));
				json.put("deploytime", new DateUtil().getDateBySqlTimestamp(resultMap.get("DEPLOY_TIME"), null));
				json.put("option", "查看");
				json.put("prodelete", "删除");
				json.put("pfrom", "注册表单");
				array.add(json);
			}
		}
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ProcessDef getProcessDef() {
		return processDef;
	}

	public void setProcessDef(ProcessDef processDef) {
		this.processDef = processDef;
	}

}
