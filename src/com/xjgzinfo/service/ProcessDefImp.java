package com.xjgzinfo.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;

import com.xjgzinfo.activiti.ActivitiManager;
import com.xjgzinfo.vo.activiti.Act_Re_ProcdefMapper;

public class ProcessDefImp implements ProcessDef {

	private ActivitiManager activitiManager;
	private Act_Re_ProcdefMapper act_Re_ProcdefMapper;

	public Act_Re_ProcdefMapper getAct_Re_ProcdefMapper() {
		return act_Re_ProcdefMapper;
	}

	public void setAct_Re_ProcdefMapper(
			Act_Re_ProcdefMapper act_Re_ProcdefMapper) {
		this.act_Re_ProcdefMapper = act_Re_ProcdefMapper;
	}

	public ActivitiManager getActivitiManager() {
		return activitiManager;
	}

	public void setActivitiManager(ActivitiManager activitiManager) {
		this.activitiManager = activitiManager;
	}

	public List<ProcessDefinition> getProcessDef() {

		return activitiManager.getProcessDef();
	}

	public List<Map> getProcessDefList() {
		return act_Re_ProcdefMapper.getProcessDefList();
	}

}
