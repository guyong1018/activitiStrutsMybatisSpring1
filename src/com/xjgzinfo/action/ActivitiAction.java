package com.xjgzinfo.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

import com.xjgzinfo.common.BaseAction;
import com.xjgzinfo.common.DateUtil;
import com.xjgzinfo.organizational.UserVo;
import com.xjgzinfo.service.ActivitiService;
import com.xjgzinfo.service.UserService;
import com.xjgzinfo.vo.TForm;

public class ActivitiAction extends BaseAction {

	private ActivitiService activitiService;
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String taskid;
	private String taskdefid;
	private String deployId;

	public String getDeployId() {
		return deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	public String getTaskdefid() {
		return taskdefid;
	}

	public void setTaskdefid(String taskdefid) {
		this.taskdefid = taskdefid;
	}

	private String processDefId;

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}
	/**
	 * 查询待办列表
	 */
	public void getTaskList() {
		UserVo tuser = (UserVo) session.get("tuser");
		List<Map> resultListMap = activitiService.getTaskListBySql(tuser.getUserid().toString());
		JSONArray array = new JSONArray();
		if (resultListMap != null && resultListMap.size() > 0) {
			for (Map resultMap : resultListMap) {
				JSONObject json = new JSONObject();
				json.put("taskid", resultMap.get("TASKID"));
				json.put("procedefname", resultMap.get("PRODEFNAME"));
				json.put("taskname", resultMap.get("TASKNAME"));
				json.put("proinsid", resultMap.get("PROCESSINSID"));
				json.put("taskdefid", resultMap.get("TASK_DEF_KEY"));
				json.put("assignee", tuser.getName());
				json.put("createtime", new DateUtil().getDateBySqlTimestamp(resultMap.get("CREATE_TIME"), null));
				json.put("chuli", "处理");
				json.put("see", "流程查看");
				array.add(json);
			}
		}
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
//		List<Task> taskList = activitiService.getTaskList(tuser.getUserid()
//				.toString());
//
//		JSONArray array = new JSONArray();
//		for (Task task : taskList) {
//			JSONObject json = new JSONObject();
//			json.put("taskid", task.getId());
//			json.put("taskname", task.getName());
//			json.put("proinsid", task.getProcessInstanceId());
//			json.put("taskdefid", task.getTaskDefinitionKey());
//			json.put("assignee", tuser.getName());
//			SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			json.put("createtime", from.format(task.getCreateTime()));
//			json.put("chuli", "处理");
//			json.put("see", "流程查看");
//			array.add(json);
//		}
		
	}
	/**
	 * 查询已办列表
	 */
	public void getTaskHisList() {
		UserVo tuser = (UserVo) session.get("tuser");
		List<Map> resultListMap = activitiService.getTaskHisListBySql(tuser.getUserid().toString());
		JSONArray array = new JSONArray();
		if (resultListMap != null && resultListMap.size() > 0) {
			for (Map resultMap : resultListMap) {
				JSONObject json = new JSONObject();
				json.put("taskid", resultMap.get("TASKID"));
				json.put("procedefname", resultMap.get("PROCESSDEFNAME"));
				json.put("taskname", resultMap.get("ACTNAME"));
				json.put("proinsid", resultMap.get("PROCESSINSID"));
				json.put("taskdefid", resultMap.get("ACTID"));
				json.put("assignee", resultMap.get("UNAME"));
				json.put("createtime", new DateUtil().getDateBySqlTimestamp(resultMap.get("STARTTIME"), null));
				json.put("endtime", new DateUtil().getDateBySqlTimestamp(resultMap.get("ENDTIME"), null));
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
	/**
	 * 流程监控列表
	 */
	public void getTaskMonitorList() {
		try {
			List<Map> resultListMap = activitiService.getTaskMoniterListBySql();
			JSONArray array = new JSONArray();
			if (resultListMap != null && resultListMap.size() > 0) {
				for (Map resultMap : resultListMap) {
					JSONObject json = new JSONObject();
					json.put("pinsid", resultMap.get("PINSID"));
					json.put("processdefname", resultMap.get("PROCESSDEFNAME"));
					json.put("starttime", new DateUtil().getDateBySqlTimestamp(resultMap.get("STARTTIME"), null));
					json.put("see", "流程查看");
					array.add(json);
				}
			}
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void redirectTaskForm() {
		try {
			List<TForm> list = activitiService.getFormByTaskidAndTaskDefId(
					taskid, taskdefid);
			response.getWriter().write(list.get(0).getUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取监控图片
	 */
	public void getMonitorImg() {
		String idtype = request.getParameter("idtype");
		InputStream inputStream = activitiService.getMonitorImgStream(taskid,idtype);
		reponseOutputStream(inputStream);
	}

	/**
	 * 获取流程图片
	 */
	public void getProcessDefimg() {
		InputStream inputStream = activitiService
				.getProcessDefImageStream(processDefId);
		reponseOutputStream(inputStream);
	}

	/**
	 * 待办监控
	 */
	public void getDivXyImg() {
		try {
			String idtype = request.getParameter("idtype");
			response.setCharacterEncoding("utf-8");
			response.getWriter()
					.write(activitiService.getActivityDivXY(taskid,idtype));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void reponseOutputStream(InputStream inputStream) {
		response.setContentType("image/png");
		try {
			OutputStream out = response.getOutputStream();
			byte[] b = new byte[1024];
			while (inputStream.read(b) != -1) {
				out.write(b);
			}
			out.flush();
			out.close();
			response.setStatus(response.SC_OK);
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存活动表单
	 */
	public void saveForm() {
		try {
			String strJson = request.getParameter("var_act");
			JSONArray array = JSONArray.fromObject(strJson);
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = (JSONObject) array.get(i);
				String formUrl = object.get("formurl").toString();
				String processDefID = object.get("processdefid").toString();
				String act_id = object.get("activityid").toString();
				TForm tform = new TForm();
				tform.setActId(act_id);
				tform.setActName(object.get("activityname"));
				tform.setProcessdefid(processDefID);
				tform.setProcessdefname(object.get("processname"));
				tform.setUrl(formUrl);
				if (formUrl == null || formUrl.trim().equals("")) {
					activitiService.deleteForm(processDefID, act_id);
				} else {
					activitiService.registrationForm(tform);
				}
			}
			response.getWriter().write("true");
			response.setStatus(response.SC_OK);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册表单获取节点
	 */
	public void getActivityList() {
		//根据流程定义id获取节点列表，子过程在此是一个活动
		List<ActivityImpl> list = activitiService.getActivityList(processDefId);
		//记录form表单中activityID，用于查询显示用
		List<Object> act_idList = new ArrayList<Object>();
		//记录子过程的节点
		List<ActivityImpl> subActList = null;
		List<ActivityImpl> subActUserTaskList = new ArrayList<ActivityImpl>();
		for (ActivityImpl act : list) {
			String subtype = act.getProperty("type").toString();
			if("subProcess".equals(subtype)){//加子过程的节点
				subActList = act.getActivities();//获取子过程中活动列表
				if(subActList != null){
					for(ActivityImpl subAct : subActList){
						String subacttype = subAct.getProperty("type").toString();
						if("userTask".equals(subacttype)){
							act_idList.add(subAct.getId());
							subActUserTaskList.add(subAct);
						}
					}
				}
			}
			if("userTask".equals(subtype)){
				act_idList.add(act.getId());
			}
		}
		if(subActUserTaskList.size() > 0){
			list.addAll(subActUserTaskList);
		}
		ProcessDefinitionEntity pde = activitiService
				.getProcessDefinitionEntity(processDefId);
		List<TForm> tformList = activitiService.getForm(processDefId,
				act_idList);
		try {
			JSONArray array = new JSONArray();
			for (ActivityImpl act : list) {
				String type = act.getProperty("type").toString();
				if ("userTask".equals(type)) {
					JSONObject json = new JSONObject();
					json.put("activityid", act.getId());
					json.put("activityname", act.getProperty("name"));
					json.put("processname", pde.getName());
					for (TForm tform : tformList) {
						if (tform.getActId().equals(act.getId())) {
							json.put("formurl", tform.getUrl());
						}
					}
					if (!json.containsKey("formurl"))
						json.put("formurl", "");
					array.add(json);
				}
			}
			
//			list.clear();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(array.toString());
			response.setStatus(response.SC_OK);
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除流程
	 */
	public void deleteProcessDef() {
		activitiService.deleteProcessDef(deployId);
		// activitiService.deleteForm(ids)
	}
	/**
	 * 部署流程
	 */
//	public void deployProcessDefZip(){
//		
//		try {
//			String filename = request.getParameter("filename");
//			activitiService.deployProcess(filename);
//			response.getWriter().write("true");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public ActivitiService getActivitiService() {
		return activitiService;
	}

	public void setActivitiService(ActivitiService activitiService) {
		this.activitiService = activitiService;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
}
