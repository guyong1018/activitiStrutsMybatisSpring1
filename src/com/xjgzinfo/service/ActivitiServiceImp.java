package com.xjgzinfo.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;

import com.xjgzinfo.activiti.ActivitiManager;
import com.xjgzinfo.vo.TForm;
import com.xjgzinfo.vo.TFormExample;
import com.xjgzinfo.vo.TFormExample.Criteria;
import com.xjgzinfo.vo.TFormMapper;
import com.xjgzinfo.vo.activiti.Act_Ru_TaskMapper;

public class ActivitiServiceImp implements ActivitiService{
	private ActivitiManager activitiManager;
	private TFormMapper tformMapper;
	private Act_Ru_TaskMapper act_Ru_TaskMapper;
	
	public List<TForm> getForm(String processdefid,String act_id) {
		TFormExample tformExample = new TFormExample();
		Criteria criteria = tformExample.createCriteria();
		criteria.andActIdEqualTo(act_id).andProcessdefidEqualTo(processdefid);
		return tformMapper.selectByExample(tformExample);
	}
	public void registrationForm(TForm form) {
		
		tformMapper.insert(form);
	}
//	public void updateForm(TForm form) {
//		TFormExample tformExample = new TFormExample();
//		Criteria criteria = tformExample.createCriteria();
//		criteria.andIdEqualTo(form.getId());
//		tformMapper.updateByExample(form, tformExample);
//	}
	
	public void deleteForm(String processDefId,String act_id){
		TFormExample tformExample = new TFormExample();
		Criteria criteria = tformExample.createCriteria();
		criteria.andProcessdefidEqualTo(processDefId).andActIdEqualTo(act_id);
		tformMapper.deleteByExample(tformExample);
	}

	public List<ActivityImpl> getActivityList(String processDefId){
		return activitiManager.getActivityList(processDefId);
	}
	public ProcessDefinitionEntity getProcessDefinitionEntity(
			String processDefId) {
		return activitiManager.getProcessDefinitionEntity(processDefId);
	}
	public List<TForm> getForm(String processdefid, List<Object> act_idList) {
		TFormExample tformExample = new TFormExample();
		Criteria criteria = tformExample.createCriteria();
		criteria.andActIdIn(act_idList).andProcessdefidEqualTo(processdefid);
		return tformMapper.selectByExample(tformExample);
	}
	public List<TForm> getFormByTaskidAndTaskDefId(String taskId,String taskDefId) {
		TFormExample tformExample = new TFormExample();
		Criteria criteria = tformExample.createCriteria();
		String processdefid = activitiManager.findProcessDefinitionEntityByTaskId(taskId).getId();
		activitiManager.findProcessDefinitionEntityByTaskId(taskId);
		criteria.andActIdEqualTo(taskDefId).andProcessdefidEqualTo(processdefid);
		return tformMapper.selectByExample(tformExample);
	}
	/**
	 * 部署流程
	 * @param filename 部署的zip文件名
	 */
	public void deployProcess(String filename) {
		activitiManager.flowDeploy(filename);
		
	}
	public void deleteProcessDef(String deployId) {
		activitiManager.deleteProcessDef(deployId);
		
	}
	
	/**
	 * 获取节点信息
	 * @param id 活动实例id或流程实例id
	 * @param idtype p流程示例id，t活动实例id
	 * @return
	 */
	public String getActivityDivXY(String id,String idtype){
		return activitiManager.getActivityDivXY(id,idtype);
	}
	public List<Task> getTaskMonitorList() {
		// TODO Auto-generated method stub
		return activitiManager.getTaskMonitorList();
	}
	
	/**
	 * 查询待办列表
	 */
	public List<Map> getTaskListBySql(String userid) {
		return act_Ru_TaskMapper.getTaskInsList(userid);
	}
	/**
	 * 查询已办列表
	 */
	public List<Map> getTaskHisListBySql(String userid) {
		return act_Ru_TaskMapper.getTaskHisInsList(userid);
	}
	/**
	 * 查询监控列表
	 */
	public List<Map> getTaskMoniterListBySql() {
		return act_Ru_TaskMapper.getTaskMoniterList();
	}
	public Act_Ru_TaskMapper getAct_Ru_TaskMapper() {
		return act_Ru_TaskMapper;
	}
	public void setAct_Ru_TaskMapper(Act_Ru_TaskMapper act_Ru_TaskMapper) {
		this.act_Ru_TaskMapper = act_Ru_TaskMapper;
	}
	public TFormMapper getTformMapper() {
		return tformMapper;
	}
	public void setTformMapper(TFormMapper tformMapper) {
		this.tformMapper = tformMapper;
	}
	public ActivitiManager getActivitiManager() {
		return activitiManager;
	}
	public void setActivitiManager(ActivitiManager activitiManager) {
		this.activitiManager = activitiManager;
	}
	public List<Task> getTaskList(String userId) {
		return activitiManager.getTaskListByUserId(userId);
	}
	public InputStream getProcessDefImageStream(String processDefId){
		return activitiManager.getProcessDefImageStream(processDefId);
	}
	/**
	 * 获取监控图
	 * @param id 流程实例ID或活动实例ID
	 * @param idtype p是流程实例id，t是活动实例id
	 * @return
	 */
	public InputStream getMonitorImgStream(String id,String idtype) {
		return activitiManager.getMonitorImgStream(id,idtype);
	}
	public Object getFormKey(String taskid) {
		return activitiManager.getFormKey(taskid);
	}

	
}
