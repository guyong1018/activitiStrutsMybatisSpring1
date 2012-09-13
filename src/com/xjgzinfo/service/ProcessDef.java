package com.xjgzinfo.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;

public interface ProcessDef {

	public List<ProcessDefinition> getProcessDef();
	
	public List<Map> getProcessDefList();
}
