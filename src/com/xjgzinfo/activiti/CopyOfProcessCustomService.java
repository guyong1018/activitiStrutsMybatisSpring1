package com.xjgzinfo.activiti;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
  
  
/** 
 * 
 * 流程操作核心类<br> 
 * 此核心类主要处理：流程通过、驳回、转办、中止、挂起等核心操作<br> 
 *  
 * // 用的几个主要的功能函数，一个是驳回函数：backProcess（），
 * 一个是查询可以驳回的节点列表：findBackAvtivity（），
 * 还有一个就是生成流程图：getImageStream（），
 * 主要的就用到这么几个，有兴趣的可以研究一下，到如包之后代码是可以编译通过的， 
 */  
public class  CopyOfProcessCustomService{  
	
	private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	private static RepositoryService repositoryService = processEngine.getRepositoryService();  
  
	private static RuntimeService runtimeService = processEngine.getRuntimeService();


	private static TaskService taskService = processEngine.getTaskService();


	private static FormService formService = processEngine.getFormService();


	private static HistoryService historyService = processEngine.getHistoryService();


	/** 
     * 回退流程 
     *  
     * @param taskId 
     *            当前任务ID 
     * @param activityId 
     *            驳回节点ID 
     * @param variables 
     *            流程存储参数 
     * @throws Exception 
     */  
    public static void backProcess(String taskId, String activityId,  
            Map<String, Object> variables) throws Exception {  
        if (StringUtils.isEmpty(activityId)) {  
            throw new Exception("驳回目标节点ID为空！");  
        }  
  
        // 查找所有并行任务节点，同时驳回  
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(  
                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());  
        for (Task task : taskList) {  
            commitProcess(task.getId(), variables, activityId);  
        }  
    }


	/** 
     * 取回流程  ：有问题，不叫取回，和回退一样
     *  
     * @param taskId 
     *            当前任务ID 
     * @param activityId 
     *            取回节点ID 
     * @throws Exception 
     */  
    public static void callBackProcess(String taskId, String activityId)  
            throws Exception {  
        if (StringUtils.isEmpty(activityId)) {  
            throw new Exception("目标节点ID为空！");  
        }  
  
        // 查找所有并行任务节点，同时取回  
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(  
                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());  
        for (Task task : taskList) {  
            commitProcess(task.getId(), null, activityId);  
        }  
    }

	/** 
     * 根据当前任务ID，查询可以驳回的任务节点 
     *  
     * @param taskId 
     *            当前任务ID 
     */  
    public static List<ActivityImpl> findBackAvtivity(String taskId) throws Exception {  
        List<ActivityImpl> rtnList =  iteratorBackActivity(taskId, findActivitiImpl(taskId,  
                    null), new ArrayList<ActivityImpl>(),  
                    new ArrayList<ActivityImpl>());  
        return reverList(rtnList);  
    }


	/** 
     * 根据任务ID获取流程定义 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
    public static ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(  
            String taskId) throws Exception {  
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)  
                .getDeployedProcessDefinition(findTaskById(taskId)  
                        .getProcessDefinitionId()); 
  
        if (processDefinition == null) {  
            throw new Exception("流程定义未找到!");  
        }  
  
        return processDefinition;  
    }  
  
	/** 
     * 根据任务ID获取对应的流程实例 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
    public static ProcessInstance findProcessInstanceByTaskId(String taskId)  
            throws Exception {  
        // 找到流程实例  
        ProcessInstance processInstance = runtimeService  
                .createProcessInstanceQuery().processInstanceId(  
                        findTaskById(taskId).getProcessInstanceId())  
                .singleResult();  
        if (processInstance == null) {   
            throw new Exception("流程实例未找到!");  
        }  
        return processInstance;  
    }  

    /**
     * 生成流程图
     * @param taskId
     * @return
     * @throws Exception
     */
    public static InputStream getImageStream(String taskId) throws Exception{
    	ProcessDefinitionEntity pde = findProcessDefinitionEntityByTaskId(taskId);
    	InputStream imageStream = ProcessDiagramGenerator.generateDiagram(  
				pde, "png",  
		runtimeService.getActiveActivityIds(findProcessInstanceByTaskId(taskId).getId()));
    	return imageStream;
    }
    
    
	/** 
     * 清空指定活动节点流向 
     *  
     * @param activityImpl 
     *            活动节点 
     * @return 节点流向集合 
     */  
    private static List<PvmTransition> clearTransition(ActivityImpl activityImpl) {  
        // 存储当前节点所有流向临时变量  
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();  
        // 获取当前节点所有流向，存储到临时变量，然后清空  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        for (PvmTransition pvmTransition : pvmTransitionList) {  
            oriPvmTransitionList.add(pvmTransition);  
        }  
        pvmTransitionList.clear();  
  
        return oriPvmTransitionList;  
    }


	/** 
	 * 自由流按照流向流转
     * @param taskId 
     *            当前任务ID 
     * @param variables 
     *            流程变量 
     * @param activityId 
     *            流程转向执行任务节点ID<br> 
     *            此参数为空，默认完成当前工作项操作 
     * @throws Exception 
     */  
    private static void commitProcess(String taskId, Map<String, Object> variables,  
            String activityId) throws Exception {  
        if (variables == null) {  
            variables = new HashMap<String, Object>();  
        }  
        // 跳转节点为空，默认提交操作  
        if (StringUtils.isEmpty(activityId)) {  
            taskService.complete(taskId, variables);  
        } else {// 流程转向操作  
            turnTransition(taskId, activityId, variables);  
        }  
    }


	/** 
     * 中止流程(特权人直接审批通过等) 
     *  
     * @param taskId 
     */  
    public static void endProcess(String taskId) throws Exception {  
        ActivityImpl endActivity = findActivitiImpl(taskId, "end");  
        commitProcess(taskId, null, endActivity.getId());  
    }

    /** 
     * 转办流程 
     *  
     * @param taskId 
     *            当前任务节点ID 
     * @param userCode 
     *            被转办人Code 
     */  
    public static void transferAssignee(String taskId, String userCode) {  
        taskService.setAssignee(taskId, userCode);  
    }  

	/** 
     * 根据流入任务集合，查询最近一次的流入任务节点 
     *  
     * @param processInstance 
     *            流程实例 
     * @param tempList 
     *            流入任务集合 
     * @return 
     */  
    private static ActivityImpl filterNewestActivity(ProcessInstance processInstance,  
            List<ActivityImpl> tempList) {  
        while (tempList.size() > 0) {  
            ActivityImpl activity_1 = tempList.get(0);  
            HistoricActivityInstance activityInstance_1 = findHistoricUserTask(  
                    processInstance, activity_1.getId());  
            if (activityInstance_1 == null) {  
                tempList.remove(activity_1);  
                continue;  
            }  
  
            if (tempList.size() > 1) {  
                ActivityImpl activity_2 = tempList.get(1);  
                HistoricActivityInstance activityInstance_2 = findHistoricUserTask(  
                        processInstance, activity_2.getId());  
                if (activityInstance_2 == null) {  
                    tempList.remove(activity_2);  
                    continue;  
                }  
  
                if (activityInstance_1.getEndTime().before(  
                        activityInstance_2.getEndTime())) {  
                    tempList.remove(activity_1);  
                } else {  
                    tempList.remove(activity_2);  
                }  
            } else {  
                break;  
            }  
        }  
        if (tempList.size() > 0) {  
            return tempList.get(0);  
        }  
        return null;  
    }


	/** 
     * 根据任务ID和节点ID获取活动节点 <br> 
     *  
     * @param taskId 
     *            任务ID 
     * @param activityId 
     *            活动节点ID <br> 
     *            如果为null或""，则默认查询当前活动节点 <br> 
     *            如果为"end"，则查询结束节点 <br> 
     *  
     * @return 
     * @throws Exception 
     */  
    private static ActivityImpl findActivitiImpl(String taskId, String activityId)  
            throws Exception {  
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);  
  
        // 获取当前活动节点ID  
        if (StringUtils.isEmpty(activityId)) {  
            activityId = findTaskById(taskId).getTaskDefinitionKey();  
        }  
  
        // 根据流程定义，获取该流程实例的结束节点  
        if (activityId.toUpperCase().equals("END")) {  
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {  
                List<PvmTransition> pvmTransitionList = activityImpl  
                        .getOutgoingTransitions();  
                if (pvmTransitionList.isEmpty()) {  
                    return activityImpl;  
                }  
            }  
        }  
  
        // 根据节点ID，获取对应的活动节点  
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)  
                .findActivity(activityId);  
  
        return activityImpl;  
    }


	/** 
     * 查询指定任务节点的最新记录 
     *  
     * @param processInstance 
     *            流程实例 
     * @param activityId 
     * @return 
     */  
    private static HistoricActivityInstance findHistoricUserTask(  
            ProcessInstance processInstance, String activityId) {  
        HistoricActivityInstance rtnVal = null;  
        // 查询当前流程实例审批结束的历史节点  
        List<HistoricActivityInstance> historicActivityInstances = historyService  
                .createHistoricActivityInstanceQuery().activityType("userTask")  
                .processInstanceId(processInstance.getId()).activityId(  
                        activityId).finished()  
                .orderByHistoricActivityInstanceEndTime().desc().list();  
        if (historicActivityInstances.size() > 0) {  
            rtnVal = historicActivityInstances.get(0);  
        }  
  
        return rtnVal;  
    }  
  
	/** 
     * 根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID 
     *  
     * @param activityImpl 
     *            当前节点 
     * @return 
     */  
    private static String findParallelGatewayId(ActivityImpl activityImpl) {  
        List<PvmTransition> incomingTransitions = activityImpl  
                .getOutgoingTransitions();  
        for (PvmTransition pvmTransition : incomingTransitions) {  
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;  
            activityImpl = transitionImpl.getDestination();  
            String type = (String) activityImpl.getProperty("type");  
            if ("parallelGateway".equals(type)) {// 并行路线  
                String gatewayId = activityImpl.getId();  
                String gatewayType = gatewayId.substring(gatewayId  
                        .lastIndexOf("_") + 1);  
                if ("END".equals(gatewayType.toUpperCase())) {  
                    return gatewayId.substring(0, gatewayId.lastIndexOf("_"))  
                            + "_start";  
                }  
            }  
        }  
        return null;  
    }  
  
  
    /** 
     * 根据任务ID获得任务实例 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
    private static TaskEntity findTaskById(String taskId) throws Exception {  
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(  
                taskId).singleResult();  
        if (task == null) {  
            throw new Exception("任务实例未找到!");  
        }  
        return task;  
    }  
  
  
    /** 
     * 根据流程实例ID和任务key值查询所有同级任务集合 
     *  
     * @param processInstanceId 
     * @param key 
     * @return 
     */  
    private static List<Task> findTaskListByKey(String processInstanceId, String key) {  
        return taskService.createTaskQuery().processInstanceId(  
                processInstanceId).taskDefinitionKey(key).list();  
    }  
  
  
    /** 
     * 迭代循环流程树结构，查询当前节点可驳回的任务节点 
     *  
     * @param taskId 
     *            当前任务ID 
     * @param currActivity 
     *            当前活动节点 
     * @param rtnList 
     *            存储回退节点集合 
     * @param tempList 
     *            临时存储节点集合（存储一次迭代过程中的同级userTask节点） 
     * @return 回退节点集合 
     */  
    private static List<ActivityImpl> iteratorBackActivity(String taskId,  
            ActivityImpl currActivity, List<ActivityImpl> rtnList,  
            List<ActivityImpl> tempList) throws Exception {  
        // 查询流程定义，生成流程树结构  
        ProcessInstance processInstance = findProcessInstanceByTaskId(taskId);  
  
        // 当前节点的流入来源  
        List<PvmTransition> incomingTransitions = currActivity  
                .getIncomingTransitions();  
        // 条件分支节点集合，userTask节点遍历完毕，迭代遍历此集合，查询条件分支对应的userTask节点  
        List<ActivityImpl> exclusiveGateways = new ArrayList<ActivityImpl>();  
        // 并行节点集合，userTask节点遍历完毕，迭代遍历此集合，查询并行节点对应的userTask节点  
        List<ActivityImpl> parallelGateways = new ArrayList<ActivityImpl>();  
        // 遍历当前节点所有流入路径  
        for (PvmTransition pvmTransition : incomingTransitions) {  
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;  
            ActivityImpl activityImpl = transitionImpl.getSource();  
            String type = (String) activityImpl.getProperty("type");  
            /** 
             * 并行节点配置要求：<br> 
             * 必须成对出现，且要求分别配置节点ID为:XXX_start(开始)，XXX_end(结束) 
             */  
            if ("parallelGateway".equals(type)) {// 并行路线  
                String gatewayId = activityImpl.getId();  
                String gatewayType = gatewayId.substring(gatewayId  
                        .lastIndexOf("_") + 1);  
                if ("START".equals(gatewayType.toUpperCase())) {// 并行起点，停止递归  
                    return rtnList;  
                } else {// 并行终点，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点  
                    parallelGateways.add(activityImpl);  
                }  
            } else if ("startEvent".equals(type)) {// 开始节点，停止递归  
                return rtnList;  
            } else if ("userTask".equals(type)) {// 用户任务  
                tempList.add(activityImpl);  
            } else if ("exclusiveGateway".equals(type)) {// 分支路线，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点  
                currActivity = transitionImpl.getSource();  
                exclusiveGateways.add(currActivity);  
            }  
        }  
  
        /** 
         * 迭代条件分支集合，查询对应的userTask节点 
         */  
        for (ActivityImpl activityImpl : exclusiveGateways) {  
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);  
        }  
  
        /** 
         * 迭代并行集合，查询对应的userTask节点 
         */  
        for (ActivityImpl activityImpl : parallelGateways) {  
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);  
        }  
  
        /** 
         * 根据同级userTask集合，过滤最近发生的节点 
         */  
        currActivity = filterNewestActivity(processInstance, tempList);  
        if (currActivity != null) {  
            // 查询当前节点的流向是否为并行终点，并获取并行起点ID  
            String id = findParallelGatewayId(currActivity);  
            if (StringUtils.isEmpty(id)) {// 并行起点ID为空，此节点流向不是并行终点，符合驳回条件，存储此节点  
                rtnList.add(currActivity);  
            } else {// 根据并行起点ID查询当前节点，然后迭代查询其对应的userTask任务节点  
                currActivity = findActivitiImpl(taskId, id);  
            }  
  
            // 清空本次迭代临时集合  
            tempList.clear();  
            // 执行下次迭代  
            iteratorBackActivity(taskId, currActivity, rtnList, tempList);  
        }  
        return rtnList;  
    }  
  
  
    /** 
     * 还原指定活动节点流向 
     *  
     * @param activityImpl 
     *            活动节点 
     * @param oriPvmTransitionList 
     *            原有节点流向集合 
     */  
    private static void restoreTransition(ActivityImpl activityImpl,  
            List<PvmTransition> oriPvmTransitionList) {  
        // 清空现有流向  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        pvmTransitionList.clear();  
        // 还原以前流向  
        for (PvmTransition pvmTransition : oriPvmTransitionList) {  
            pvmTransitionList.add(pvmTransition);  
        }  
    }  
  
    /** 
     * 反向排序list集合，便于驳回节点按顺序显示 
     *  
     * @param list 
     * @return 
     */  
    private static List<ActivityImpl> reverList(List<ActivityImpl> list) {  
        List<ActivityImpl> rtnList = new ArrayList<ActivityImpl>();  
        // 由于迭代出现重复数据，排除重复  
        for (int i = list.size(); i > 0; i--) {  
            if (!rtnList.contains(list.get(i - 1)))  
                rtnList.add(list.get(i - 1));  
        }  
        return rtnList;  
    }  
  
    
  
    /** 
     * 流程转向操作 
     *  
     * @param taskId 
     *            当前任务ID 
     * @param activityId 
     *            目标节点任务ID 
     * @param variables 
     *            流程变量 
     * @throws Exception 
     */  
    private static void turnTransition(String taskId, String activityId,  
            Map<String, Object> variables) throws Exception {  
        // 当前节点  
        ActivityImpl currActivity = findActivitiImpl(taskId, null);  
        // 清空当前流向  
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);  
  
        // 创建新流向  
        TransitionImpl newTransition = currActivity.createOutgoingTransition();  
        // 目标节点  
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);  
        // 设置新流向的目标节点  
        newTransition.setDestination(pointActivity);  
  
        // 执行转向任务  
        taskService.complete(taskId, variables);  
        // 删除目标节点新流入  
        pointActivity.getIncomingTransitions().remove(newTransition);  
  
        // 还原以前流向  
        restoreTransition(currActivity, oriPvmTransitionList);  
    }  
    

    public static FormService getFormService() {
		return formService;
	}  
  
    public static HistoryService getHistoryService() {
		return historyService;
	}  
  
  
    public static ProcessEngine getProcessEngine() {
		return processEngine;
	}  
  
    public static RepositoryService getRepositoryService() {
		return repositoryService;
	}  
  
    public static RuntimeService getRuntimeService() {
		return runtimeService;
	}  
  
    public static TaskService getTaskService() {
		return taskService;
	}
}  
