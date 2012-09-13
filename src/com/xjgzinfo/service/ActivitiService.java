package com.xjgzinfo.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;

import com.xjgzinfo.vo.TForm;

public interface ActivitiService {
	/**
	 * 根据用户id获取待办项列表
	 * @param userId
	 * @return
	 */
	public List<Task> getTaskList(String userId);
	/**
	 * 根获取待办项列表
	 * @return
	 */
	public List<Task> getTaskMonitorList();
	/**
	 * 获取流程定义图片
	 * @param processDefId
	 * @return
	 */
	public InputStream getProcessDefImageStream(String processDefId);
	/**
	 * 获取监控图
	 * @param id 流程实例ID或活动实例ID
	 * @param idtype p是流程实例id，t是活动实例id
	 * @return
	 */
	public InputStream getMonitorImgStream(String id,String idtype);
	/**
	 * 获取节点信息
	 * @param id 活动实例id或流程实例id
	 * @param idtype p流程示例id，t活动实例id
	 * @return
	 */
	public String getActivityDivXY(String id,String idtype);
	/**
	 * 获取外部渲染表单
	 * @param taskid
	 * @return
	 */
	public Object getFormKey(String taskid);
	/**
	 * 获取节点对应表单对象
	 * @param act_id
	 * @return
	 */
	public List<TForm> getForm(String processdefid,String act_id);
	/**
	 * 获取节点表单对象列表
	 * @param processdefid
	 * @param act_idList
	 * @return
	 */
	public List<TForm> getForm(String processdefid,List<Object> act_idList);
	public List<TForm> getFormByTaskidAndTaskDefId(String taskId,String taskDefid);
	/**
	 * 注册表单
	 * @param form
	 */
	public void registrationForm(TForm form);
	/**
	 * 删除表单
	 * @param ids
	 */
	public void deleteForm(String processDefId,String act_id);
	/**
	 * 根据流程定义ID获取活动列表
	 * @param processDefId
	 * @return
	 */
	public List<ActivityImpl> getActivityList(String processDefId);
	
	public ProcessDefinitionEntity getProcessDefinitionEntity(String processDefId);
	/**
	 * 删除流程定义
	 * @param deployId
	 */
	public void deleteProcessDef(String deployId);
	/**
	 * 部署流程
	 * @param filename 部署的zip文件名
	 */
	public void deployProcess(String filename);
	/**
	 * 查询待办项列表
	 * @param userid
	 * @return
	 */
	public List<Map> getTaskListBySql(String userid);
	/**
	 * 查询已办项列表
	 * @param userid
	 * @return
	 */
	public List<Map> getTaskHisListBySql(String userid);
	/**
	 * 查询监控列表
	 */
	public List<Map> getTaskMoniterListBySql();
}
