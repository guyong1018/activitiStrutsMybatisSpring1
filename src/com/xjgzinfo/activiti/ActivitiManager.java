package com.xjgzinfo.activiti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import net.sf.json.JSONArray;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.ProcessDefinitionBuilder;
import org.activiti.engine.impl.pvm.PvmExecution;
import org.activiti.engine.impl.pvm.PvmProcessDefinition;
import org.activiti.engine.impl.pvm.PvmProcessInstance;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.examples.pvm.WaitState;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.xjgzinfo.common.ActivityVo;
import com.xjgzinfo.organizational.ParticipantService;
import com.xjgzinfo.organizational.UserVo;
import com.xjgzinfo.vo.UserVoMapper;

/**
 * 工作流操作类
 * 
 * @author guyong
 * 
 */
public class ActivitiManager {
	/**
	 * 工作流资源库接口，用于部署流程，获取图片或xml文件资源
	 */
	private RepositoryService repositoryService;
	/**
	 * 运行服务接口，用于启动流程等获取运行时信息
	 */
	private RuntimeService runtimeService;
	/**
	 * 任务接口，用于完成工作项等
	 */
	private TaskService taskService;
	/**
	 * 历史接口，用于获取历史信息
	 */
	private HistoryService historyService;
	/**
	 * 管理接口，用于获取流程管理方面信息
	 */
	private ManagementService managementService;
	/**
	 * 表单接口，获取工作流支持的表单信息
	 */
	private FormService formService;
	/**
	 * 用户接口，获取用户信息
	 */
	private UserVoMapper tuserMapper;
	/**
	 * 参与者接口，转换参与者表达式为工作流引擎认可的格式
	 */
	private ParticipantService participantService;
	/**
	 * activiti自带的人员信息操作接口
	 */
	private IdentityService identityService; 

	Logger log = Logger.getLogger(ActivitiManager.class);

	public void PvmProcessDemo(){
		PvmProcessDefinition processDefinition = new ProcessDefinitionBuilder()
		.createActivity("mypvm1").property("assignee", "2").initial()
		.behavior(new WaitState()).transition("mypvm2").endActivity()
		.createActivity("mypvm2").property("assignee", "3").behavior(new WaitState()).transition("c")
		.endActivity().createActivity("c").property("assignee", "4").behavior(new WaitState()).endActivity()
		.buildProcessDefinition();
		PvmProcessInstance pvmProcessIns = processDefinition.createProcessInstance();
		pvmProcessIns.start();
		
		PvmExecution activityIns = pvmProcessIns.findExecution("mypvm1");
		activityIns.signal(null, null);
		
	}
	
	

	/**
	 * 启动流程
	 * @param processDefKey 流程定义Key
	 * @param businessKey 业务key
	 * @param paramMap 流程变量
	 * @param startUserId 流程启动人（登录人ID）
	 * @return 流程示例ID
	 */
	public String startFlowProcess(String processDefKey,String businessKey,Map<String, Object> paramMap,String startUserId) {
		ProcessInstance processInstance = getProcessIns(processDefKey,businessKey,paramMap,startUserId);
		return processInstance.getId();

	}
	/**
	 * 启动流程
	 * @param processDefKey 流程定义Key
	 * @param businessKey 业务key
	 * @param paramMap 流程变量
	 * @param startUserId 流程启动人（登录人ID）
	 * @return 流程示例对象
	 */
	public ProcessInstance startFlowProcess_ProcessIns(String processDefKey,String businessKey,Map<String, Object> paramMap,String startUserId) {
		return getProcessIns(processDefKey,businessKey,paramMap,startUserId);
	}

	
	/**
	 * 获取流程定义列表
	 * @return
	 */
	public List<ProcessDefinition> getProcessDef() {
		return repositoryService.createProcessDefinitionQuery()
				.orderByProcessDefinitionName().desc().list();
	}

	/**
	 * 删除部署的流程及实例
	 * 
	 * @param deployId
	 */
	public void deleteProcessDef(String deployId) {
		repositoryService.deleteDeployment(deployId, true);
	}
	/**
	 * 获取某用户的工作项列表
	 * 
	 * @param userId
	 *            查询的用户id条件
	 * @return
	 */
	public List<Task> getTaskListByUserId(String userId) {
		List<Task> tasks = new ArrayList<Task>();
		// 根据当前人的ID查询
		List<Task> todoList = taskService.createTaskQuery()
				.taskAssignee(userId).orderByTaskPriority().desc()
				.orderByTaskCreateTime().desc().list();
		// 根据当前人未签收的任务
		List<Task> unsignedTasks = taskService.createTaskQuery()
				.taskCandidateUser(userId).orderByTaskPriority().desc()
				.orderByTaskCreateTime().desc().list();
		// 合并
		tasks.addAll(todoList);
		tasks.addAll(unsignedTasks);
		return tasks;
	}

	/**
	 * 查询所有待办列表
	 * 
	 * @return
	 */
	public List<Task> getTaskMonitorList() {
		List list = taskService.createTaskQuery().list();
		return taskService.createTaskQuery().orderByTaskPriority().desc()
				.orderByTaskCreateTime().desc().list();
	}

	/**
	 * 手动驱动
	 */
	public void sendReceiveTaskMsg(String activitId) {
		Execution execution = runtimeService.createExecutionQuery()
				.activityId(activitId).singleResult();
		runtimeService.signal(execution.getId());
	}

	public ExecutionEntity getExecution(String executionId) {
		ExecutionEntity execution = (ExecutionEntity) runtimeService
				.createExecutionQuery().executionId(executionId).singleResult();
		return execution;
	}

	/**
	 * 拾取工作项
	 * 
	 * @param name
	 *            责任人名字
	 */
	public void claimTask(String name) {
		List<Task> taskList = taskService.createTaskQuery()
				.taskCandidateUser(name).list();
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			// 认领任务
			taskService.claim(task.getId(), name);
		}
	}

	/**
	 * 完成工作项
	 * 
	 * @param username
	 *            用户id
	 */
	public void completeWorkItem(String userid, String taskId,
			Map<String, Object> param) {
		param = participantService.saveParseParticipantByVariable(param);
		if (userid != null && !userid.equals("")) {
			List<Task> taskList = taskService.createTaskQuery()
					.taskCandidateUser(userid).taskId(taskId).list();
			for (int i = 0; i < taskList.size(); i++) {
				Task task = taskList.get(i);
				taskService.claim(task.getId(), userid);
			}
		}
		taskService.complete(taskId, param);

		// 核实流程是否结束
		// HistoryService historyService = processEngine.getHistoryService();
		// HistoricProcessInstance historicProcessInstance =
		// historyService.createHistoricProcessInstanceQuery().processInstanceId("410").singleResult();
		// historicProcessInstance.getEndTime());
	}

	/**
	 * 获取节点竞争对象
	 * @param taskId
	 * @return
	 */
	public List<IdentityLink> getIdentityLinksForTask(String taskId){
		return taskService.getIdentityLinksForTask(taskId);
	}
	
	/**
	 * 获取任务列表
	 * 
	 * @param name
	 *            用户名或组名
	 * @param isGroup
	 *            是否是组，true：组 flase：用户
	 */
	public void getTask(String name, boolean isGroup) {

		List<Task> taskList = null;
		if (isGroup) {
			taskList = taskService.createTaskQuery().taskCandidateGroup(name)
					.list();
		} else {
			taskList = taskService.createTaskQuery().taskCandidateUser(name)
					.list();
		}
	}

	/**
	 * 根据流程实例ID查询编历史节点，流程监控用，显示绿色，表示已完成
	 * 
	 * @param processinsid
	 * @return
	 */
	public Set<String> getHisTaskIns(String processinsid) {
		List<HistoricActivityInstance> hisActInsList = historyService
				.createHistoricActivityInstanceQuery().finished()
				.processInstanceId(processinsid).orderByActivityId().asc()
				.list();
		Set<String> hisActInsKey = new HashSet<String>();
		for (HistoricActivityInstance hai : hisActInsList) {
			hisActInsKey.add(hai.getActivityId());
		}
		return hisActInsKey;
	}

	/**
	 * 部署流程
	 * 
	 * @param fileName
	 *            zip文件
	 */
	public void flowDeploy(String zipfileName) {
		String deploymentId = repositoryService.createDeployment()
				.addClasspathResource(zipfileName).deploy().getId();
	}

	public void deployFlow(ZipInputStream zipin) {
		repositoryService.createDeployment()
				.addZipInputStream(zipin).deploy().getId();
	}

	/**
	 * zip方式部署流程
	 * 
	 * @param zipFileName
	 */
	public void flowZipDeploy(String zipFileName) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(zipFileName);
			ZipInputStream zis = new ZipInputStream(fis);
			Deployment dep = repositoryService.createDeployment()
					.addZipInputStream(zis).deploy();
			System.out.println(zipFileName + "：部署ID为：" + dep.getId());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成流程监控图片
	 * @param processInsId 流程示例id
	 * @param ProcessDefID 流程定义id
	 * @param filename png图片文件名称包括全路径
	 */
	public void generatorDiagramImg(String processInsId, String ProcessDefID,String filename) {
		List<Execution> executionList = runtimeService.createExecutionQuery()
				.processInstanceId(processInsId).list();
		List<String> highLightList = new ArrayList<String>();
		for (Execution ex : executionList) {
			// 执行实例，execId为当前正在执行的实体ID
			ExecutionEntity execution = (ExecutionEntity) runtimeService
					.createExecutionQuery().executionId(ex.getId())
					.singleResult();
			// 当前实例的执行到哪个节点
			String activitiId = execution.getActivityId();
			// 将节点存到高亮显示的集合中
			highLightList.add(activitiId);
		}

		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(ProcessDefID);
		InputStream in = ProcessDiagramGenerator.generateDiagram(pde, "png",
				highLightList);

		byte[] b = new byte[1024];
		File file = new File(filename);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			while (in.read(b) != -1) {
				fos.write(b);
			}
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成流程图
	 * 
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public InputStream getProcessDefImageStream(String processDefId) {

		ProcessDefinition singleResult = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefId).singleResult();
		String resourceName = "";
		resourceName = singleResult.getDiagramResourceName();
		InputStream resourceAsStream = repositoryService.getResourceAsStream(
				singleResult.getDeploymentId(), resourceName);
		return resourceAsStream;
	}

	/**
	 * 获取节点坐标等信息，监控用
	 * 
	 * @param id
	 *            活动实例id或流程实例id
	 * @param idtype
	 *            p是流程实例id，t是活动实例id
	 * @return 节点json列表字符串
	 */
	public String getActivityDivXY(String id,String idtype){
		ProcessDefinitionEntity pde = null;
		ProcessInstance pins = null;
		//获取已处理过的历史节点列表
		Set<String> hisKeySet = null;
		//如果是taskid待办的监控，存取当前节点的活动定义对象
		ActivityImpl actCurrent = null;
		//如果是taskid待办的监控，如果参与者有竞争关系，存竞争者对象
		List<IdentityLink> identityLinkList = null;
		if(idtype!=null && idtype.equals("p")){
			pde = findProcessDefEntityByPinsId(id);
			pins = findProcessInstanceByProcessInsId(id);
			
			hisKeySet = getHisTaskIns(id);
		}else{
			pde = findProcessDefinitionEntityByTaskId(id);
			pins = findProcessInstanceByTaskId(id);
			hisKeySet = getHisTaskIns(findProcessInstanceByTaskId(id).getId());
			//如果参与者是竞争会多实例关系，取出参与者
			identityLinkList = getIdentityLinksForTask(id);
			if(identityLinkList != null && identityLinkList.size() > 0&&identityLinkList.get(0).getType().equals("candidate")){
				actCurrent = findActivitiImpl(id, null);
			}
		}
		//根据流程定义ID获得节点列表
		List<ActivityImpl> actList = getActivityList(pde.getId());
		//过滤list中重复的记录，有子过程是API有bug，必须过滤，无子过程正常
		actList = listRemoveDuplicates(actList);
		//添加子过程活动
		List<List<ActivityImpl>> subActLists = new ArrayList<List<ActivityImpl>>();
		for(ActivityImpl act : actList){
			String subtype = act.getProperty("type").toString();
			if("subProcess".equals(subtype)){//加子过程的节点
				subActLists.add(act.getActivities());//获取子过程中活动列表
			}
		}
		for(List<ActivityImpl> elementList : subActLists){
			//将子过程中的活动添加到活动定义列表中
			actList.addAll(elementList);
		}
		//获得正在运行的实例流列表
		List<Execution> executionList = runtimeService.createExecutionQuery()
				.processInstanceId(pins.getId())
				.list();
		//用于返回的activity对象列表
		List<ActivityVo> actvoList = new ArrayList<ActivityVo>();
		
		for(ActivityImpl act : actList){
			String type = act.getProperties().get("type").toString();
			//过滤监控的节点
			if(!taskTypeFilter(type)){
				continue;
			}
			ActivityVo actVo = new ActivityVo();
			List<HistoricActivityInstance> hisActInsList;
			try {
				hisActInsList = historyService.createHistoricActivityInstanceQuery().processInstanceId(pins.getId()).activityId(act.getId()).orderByHistoricActivityInstanceEndTime().desc().list();
				if(hisActInsList!=null&&hisActInsList.size()>0){
					HistoricActivityInstance hai = hisActInsList.get(0);
					actVo.setCreateTime(getDateFormat(hai.getStartTime()));
					actVo.setEndTime(getDateFormat(hai.getEndTime()));
					if(hai.getAssignee() != null && !hai.getAssignee().equals("")){
						UserVo tuser = tuserMapper.selectByPrimaryKey(new BigDecimal(hai.getAssignee()));
						actVo.setTaskUser(tuser.getName());
					}else if(identityLinkList != null && identityLinkList.size() > 0&&identityLinkList.get(0).getType().equals("candidate")){
						//如果节点是竞争关系，查询出竞争者的名字，赋给监控的责任人字段
						if(act.getId().equals(actCurrent.getId())){
							StringBuffer actUNameSb = new StringBuffer();
							for (int i = 0; i < identityLinkList.size(); i++) {
								IdentityLink identityLink = identityLinkList.get(i);
								UserVo tuser = tuserMapper.selectByPrimaryKey(new BigDecimal(identityLink.getUserId()));
								actUNameSb.append(tuser.getName());
								if(i < identityLinkList.size()-1){
									//标明是竞争关系，只有其中一个人完成
									actUNameSb.append(" (或) ");
								}
							}
							actVo.setTaskUser(actUNameSb.toString());
						}
					}else{
						actVo.setTaskUser("");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			actVo.setActivityId(act.getId());
			actVo.setDocumentation((String)act.getProperties().get("documentation"));
			actVo.setHeight(act.getHeight());
			actVo.setType(type);
			actVo.setWidth(act.getWidth());
			actVo.setX(act.getX());
			actVo.setY(act.getY());
			//判断是否已办
			if(hisKeySet!=null && hisKeySet.size()>0){
				Object[] objs = hisKeySet.toArray();
				for(Object obj:objs){
					if(obj.toString().equals(act.getId())){
						actVo.setHis(true);
						break;
					}
				}
			}
			for (Execution ex : executionList) {
				String activitiId = ((ExecutionEntity)ex).getActivityId();
				if(act.getId().equals(activitiId)){
					//if(((ExecutionEntity)ex).isActive())
						actVo.setHighlight(true);
					break;
				}
			}
			actvoList.add(actVo);
		}
		JSONArray js=JSONArray.fromObject(actvoList);
		return js.toString();
	}

	/**
	 * 获取监控图
	 * @param id 流程实例ID或活动实例ID
	 * @param idtype p是流程实例id，t是活动实例id
	 * @return
	 */
	public InputStream getMonitorImgStream(String id,String idtype) {
		ProcessDefinitionEntity pde = null;
		if(idtype != null && idtype.equals("p")){
			pde = findProcessDefEntityByPinsId(id);
		}else{
			pde = findProcessDefinitionEntityByTaskId(id);
		}

		String resourceName = "";
		resourceName = pde.getDiagramResourceName();
		InputStream resourceAsStream = repositoryService.getResourceAsStream(
				pde.getDeploymentId(), resourceName);
		return resourceAsStream;
	}

	/**
	 * 获取自由流回退节点方法
	 * 
	 * @param taskId
	 * @return
	 */
	public List<ActivityImpl> getActivityListByTaskId(String taskId) {
		List<ActivityImpl> actList = findProcessDefinitionEntityByTaskId(taskId)
				.getActivities();
		TaskEntity task = findTaskById(taskId);

		for (ActivityImpl act : actList) {
			if (act.getId().equals(task.getTaskDefinitionKey())) {
				actList.remove(act);
			}
		}
		return actList;
	}

	/**
	 * 根据任务ID获取对应的流程实例
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	public ProcessInstance findProcessInstanceByTaskId(String taskId) {
		// 找到流程实例
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(findTaskById(taskId).getProcessInstanceId())
				.singleResult();
		if (processInstance == null) {
			log.error("流程实例未找到!");
		}
		return processInstance;
	}

	/**
	 * 根据流程定义id获取活动定义列表
	 * 
	 * @param processDefId
	 * @return
	 */
	public List<ActivityImpl> getActivityList(String processDefId) {
		ProcessDefinitionEntity processDefinition = findProcessDefEntity(processDefId);
		return processDefinition.getActivities();
	}

	private ProcessInstance getProcessIns(String processDefKey,String businessKey,Map<String, Object> paramMap,String startUserId){
		ProcessInstance processInstance = null;
		try {
			paramMap = participantService.saveParseParticipantByVariable(paramMap);
			identityService.setAuthenticatedUserId(startUserId);
			processInstance = runtimeService
					.startProcessInstanceByKey(processDefKey,businessKey, paramMap);
			
		} finally{
			identityService.setAuthenticatedUserId(null);
		}
		return processInstance;
	}
	private ProcessDefinitionEntity findProcessDefEntity(String processDefId) {
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefId);
		return processDefinition;
	}

	private ProcessDefinitionEntity findProcessDefEntityByPinsId(
			String processInsId) {
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(findProcessInstanceByProcessInsId(processInsId)
						.getProcessDefinitionId());
		return processDefinition;
	}

	private ProcessInstance findProcessInstanceByProcessInsId(String processInsId){
		return runtimeService
				.createProcessInstanceQuery().processInstanceId(processInsId)
				.singleResult();
	}
	/**
	 * 根据任务ID获得任务实例
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	public TaskEntity findTaskById(String taskId) {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery()
				.taskId(taskId).singleResult();
		if (task == null) {
			log.error("任务实例未找到!");
		}
		return task;
	}

	/**
	 * 自由流
	 * 
	 * @param taskId
	 * @param activityId
	 */
	public void freeFlow(String taskId, String activityId) {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery()
				.taskId(taskId).singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(task.getProcessDefinitionId());
		Map<String, TaskDefinition> taskDefMap = processDefinition
				.getTaskDefinitions();
		Set<String> set = taskDefMap.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String taskKey = iterator.next();
			TaskDefinition taskDef = taskDefMap.get(taskKey);
		}
		try {
			// 当前节点
			ActivityImpl currActivity = findActivitiImpl(taskId, null);
			// 清空当前流向
			List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);
			// 创建新流向
			TransitionImpl newTransition = currActivity
					.createOutgoingTransition();
			// 目标节点
			ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
			// 设置新流向的目标节点
			newTransition.setDestination(pointActivity);
			// 执行转向任务
			taskService.complete(taskId);
			// 删除目标节点新流入
			pointActivity.getIncomingTransitions().remove(newTransition);
			// 还原以前流向
			restoreTransition(currActivity, oriPvmTransitionList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	private ActivityImpl findActivitiImpl(String taskId, String activityId) {
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
	 * 根据任务ID获取流程定义
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	public ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
			String taskId) {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(findTaskById(taskId)
						.getProcessDefinitionId());

		if (processDefinition == null) {
			// throw new Exception("流程定义未找到!");
		}

		return processDefinition;
	}
	/**
	 * 根据活动定义ID获取活动定义对象
	 * 
	 * @param processDefId
	 * @return
	 */
	public ProcessDefinitionEntity getProcessDefinitionEntity(
			String processDefId) {
		return findProcessDefEntity(processDefId);
	}

	/**
	 * 清空指定活动节点流向
	 * 
	 * @param activityImpl
	 *            活动节点
	 * @return 节点流向集合
	 */
	private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
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
	 * 
	 * @param taskId
	 *            当前任务ID
	 * @param variables
	 *            流程变量
	 * @param activityId
	 *            流程转向执行任务节点ID<br>
	 *            此参数为空，默认完成当前工作项操作
	 * @throws Exception
	 */
	private void commitProcess(String taskId, Map<String, Object> variables,
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
	private void turnTransition(String taskId, String activityId,
			Map<String, Object> variables) {
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

	/**
	 * 还原指定活动节点流向
	 * 
	 * @param activityImpl
	 *            活动节点
	 * @param oriPvmTransitionList
	 *            原有节点流向集合
	 */
	private void restoreTransition(ActivityImpl activityImpl,
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
	 * 去除list中的重复
	 * @param list
	 * @return
	 */
	private List<ActivityImpl> listRemoveDuplicates(List<ActivityImpl> list){
		Set<ActivityImpl> set = new HashSet<ActivityImpl>();   
        List<ActivityImpl> newList = new ArrayList<ActivityImpl>();   
        for (Iterator<ActivityImpl> iter = list.iterator(); iter.hasNext();)   
        {   
        	ActivityImpl element = iter.next();   
            if (set.add(element))   
                newList.add(element);   
        }   
        return newList;  
	}
	/**
	 * 过滤节点类型
	 * @param type
	 * @return
	 */
	private boolean taskTypeFilter(String type){
		if(type.equals("startEvent")||type.equals("subProcess")||type.equals("endEvent")||type.equals("inclusiveGateway")||type.equals("exclusiveGateway")||type.equals("parallelGateway")||type.equals("boundaryTimer")||type.equals("boundaryError")){
			return false;
		}
		return true;
	}

	public Object getFormKey(String taskid) {
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(task.getProcessDefinitionId());

		TaskFormData tfd = formService.getTaskFormData(task.getId());
		return formService.getRenderedTaskForm(taskid);
	}

	private String getDateFormat(Date date) {
		if (date != null)
			return (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
		else
			return "";
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public ManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}

	public UserVoMapper getTuserMapper() {
		return tuserMapper;
	}

	public void setTuserMapper(UserVoMapper tuserMapper) {
		this.tuserMapper = tuserMapper;
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	
	public ParticipantService getParticipantService() {
		return participantService;
	}

	public void setParticipantService(ParticipantService participantService) {
		this.participantService = participantService;
	}
	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}
}
