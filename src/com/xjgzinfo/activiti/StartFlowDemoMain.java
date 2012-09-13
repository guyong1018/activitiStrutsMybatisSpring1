package com.xjgzinfo.activiti;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;


public class StartFlowDemoMain {

	private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	private static RepositoryService repositoryService = processEngine.getRepositoryService();  
  
	private static RuntimeService runtimeService = processEngine.getRuntimeService();


	private static TaskService taskService = processEngine.getTaskService();


	private static FormService formService = processEngine.getFormService();


	private static HistoryService historyService = processEngine.getHistoryService();
	
	public static void main(String[] args) {
		String str = "2012-7-31 13:50:21";
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		try {
//			param.put("duedate", format.parse(str));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		StartFlowDemoMain sfdm = new StartFlowDemoMain();
		String fileName = "test.bpmn20.xml";
		String processDefId = "test:4:4213";
		//部署流程
//		sfdm.flowDeploy(fileName);
		//启动流程
		Map<String,Object> param = new HashMap<String,Object>();
//		String[] users = {"kermit","gonzo","fozzie"};
//		param.put("v_users2", Arrays.asList(users));
//		param.put("v_users", "fozzie");
//		String processInsId = sfdm.startFlowProcess(processDefId,param);
		//获得任务
//		sfdm.getTask("kermit",false);
		//工作项拾取
//		sfdm.claimTask("kermit");
		//完成工作项
//		Map<String,Object> param = new HashMap<String,Object>();
//		param.put("var_zjl", "true");
//		param.put("var_bmjl", "2");
//		param.put("var_cyz", "fozzie");
//		sfdm.completeWorkItem(null,"5111",param);
		
		//自由流  //mytest1/mytest2/mytest3
//		sfdm.genFreeFlow("5011","mytest3");
		
		//生成流程图
//		sfdm.generatorDiagramImg(processInsId,processDefId);
//		sfdm.generatorDiagramImg("4710",processDefId);
//		sfdm.sendReceiveTaskMsg("receivetask2");
		
//		sfdm.getFormData(null, "3913");
		
	}
	/**
	 * 启动流程
	 * @param key  流程定义ID
	 */
	public String startFlowProcess(String key,Map<String,Object> arg1){
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(key, arg1);
		System.out.println("processInstance.getId() = " + processInstance.getId());
		return processInstance.getId();
		
	}
	public ProcessInstance startFlowProcess_ProcessIns(String key,Map<String,Object> arg1){
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(key, arg1);
		System.out.println("processInstance.getId() = " + processInstance.getId());
		return processInstance;
		
	}
	/**
	 * 手动驱动
	 */
	public void sendReceiveTaskMsg(String activitId){
		Execution execution = runtimeService.createExecutionQuery().activityId(activitId).singleResult();
		runtimeService.signal(execution.getId());
	}
	/**
	 * 拾取工作项
	 * @param name  责任人名字
	 */
	public void claimTask(String name){
		List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(name).list();
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			//认领任务
			taskService.claim(task.getId(), name);
		}
	}
	/**
	 * 完成工作项
	 * @param username  用户id
	 */
	public void completeWorkItem(String username,String taskId,Map<String,Object> param){
		List<Task> taskList = null;
		// 查看username 现在是否能够获取到该任务
		if(username == null){
			taskList = taskService.createTaskQuery().taskId(taskId).list();
			
		}else{
			taskList = taskService.createTaskQuery().taskAssignee(username).list();
		}
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			System.out.println("task.getName() = " + task.getName());
			//完成工作项
			taskService.complete(task.getId(),param);
		}
		
		// 核实流程是否结束
//		HistoryService historyService = processEngine.getHistoryService();
//		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId("410").singleResult();
//		System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
	}
	/**
	 * 获取任务列表
	 * @param name 用户名或组名
	 * @param isGroup 是否是组，true：组  flase：用户
	 */
	public void getTask(String name,boolean isGroup){
		
		List<Task> taskList = null;
		if(isGroup){
			taskList = taskService.createTaskQuery().taskCandidateGroup(name).list();
		}else{
			taskList = taskService.createTaskQuery().taskCandidateUser(name).list();
		}
//		List<Task> taskList = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
//		List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("kermit").list();
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			System.out.println("task.getAssignee() = " + task.getAssignee());
			System.out.println("task.getDescription() = " + task.getDescription());
			System.out.println("task.getName() = " + task.getName());
			System.out.println("task.getOwner() = " + task.getOwner());
			System.out.println("task.getParentTaskId() = " + task.getParentTaskId());
			System.out.println("task.getTaskDefinitionKey() = " + task.getTaskDefinitionKey());
			System.out.println("-----------------------------");
		}
		
	}
	/**
	 * 部署流程
	 * @param fileName xml文件
	 */
	public void flowDeploy(String fileName){
//		Deployment deployment = rs.createDeployment()
//				.addClasspathResource(fileName)
//				.deploy();
		String deploymentId = repositoryService
				.createDeployment()
				.addClasspathResource(fileName)
				.deploy()
				.getId();
		System.out.println(deploymentId);
//		String barFileName = "path/to/process-one.bar";
//		ZipInputStream inputStream;
//		try {
//			inputStream = new ZipInputStream(new FileInputStream(barFileName));
//			rs.createDeployment()
//			.name("process-one.bar")
//			.addZipInputStream(inputStream)
//			.deploy();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	
	//生成流程监控图片
	public void generatorDiagramImg(String processInsId,String ProcessDefID){
		List<Execution> executionList = runtimeService.createExecutionQuery().processInstanceId(processInsId).list();
		List<String> highLightList = new ArrayList<String>();
		for(Execution ex : executionList){
//			System.out.println("ex.getClass() = " + ex.getId());
			//执行实例，execId为当前正在执行的实体ID
			ExecutionEntity execution = (ExecutionEntity)runtimeService.createExecutionQuery().executionId(ex.getId()).singleResult();
			//当前实例的执行到哪个节点
			String activitiId = execution.getActivityId();
			//将节点存到高亮显示的集合中
			highLightList.add(activitiId);
		}
		
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(ProcessDefID);
		
		
//		ProcessDefinitionEntity pde = (ProcessDefinitionEntity)imp
//                .createProcessDefinitionQuery()  
//                .processDefinitionId("myjbpmdemotest1:1:313")  
//                .singleResult();  
		InputStream in = ProcessDiagramGenerator.generateDiagram(pde, "png", highLightList);
		
		byte[] b = new byte[1024];  
	    int len = -1;  
	    File file = new File("c:\\test.png");
	    FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			while(in.read(b) != -1) {  
				fos.write(b);  
			}     
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		RepositoryServiceImpl imp = (RepositoryServiceImpl)repositoryService;  
//        ProcessDefinitionEntity entity2 = (ProcessDefinitionEntity)imp.getDeployedProcessDefinition("test:2:13708");  
//        InputStream in =  ProcessDiagramGenerator.generateDiagram(p,"png", Arrays.asList("usertask1")); 
	}
	
	/*public void genFreeFlow(String taskId,String activityId){
		
		TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(  
                taskId).singleResult();  
		
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)  
                .getDeployedProcessDefinition(task  
                        .getProcessDefinitionId());  
		
		List<ActivityImpl> activityList = processDefinition.getActivities();
		for(ActivityImpl act : activityList){
			System.out.println("act.getId() = " + act.getId());
//			act.getProperties()
		}
		System.out.println("----------------");
		Map<String,TaskDefinition> taskDefMap = processDefinition.getTaskDefinitions();
		Set<String> set = taskDefMap.keySet();
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			String taskKey = iterator.next();
			TaskDefinition taskDef = taskDefMap.get(taskKey);
		}
		//mytest1/mytest2/mytest3
		try {
			// 当前节点  
			ActivityImpl currActivity = ProcessCustomService.findActivitiImpl(taskId, null);
			
			// 清空当前流向  
	        List<PvmTransition> oriPvmTransitionList = ProcessCustomService.clearTransition(currActivity);  
			
			// 创建新流向  
	        TransitionImpl newTransition = currActivity.createOutgoingTransition();  
	        
	        // 目标节点  
	        ActivityImpl pointActivity = ProcessCustomService.findActivitiImpl(taskId, activityId);
	        // 设置新流向的目标节点  
	        newTransition.setDestination(pointActivity);  
	        // 执行转向任务  
	        taskService.complete(taskId);  
	        
	        // 删除目标节点新流入  
	        pointActivity.getIncomingTransitions().remove(newTransition);  
	        // 还原以前流向  
	        ProcessCustomService.restoreTransition(currActivity, oriPvmTransitionList); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}*/
	
	
	
	public void getFormData(String processDefinitionId,String taskId){
//		StartFormData startFormData = formService.getStartFormData(processDefinitionId);
		TaskFormData taskFormData = formService.getTaskFormData(taskId);
		System.out.println("taskFormData.getFormKey() = " + taskFormData.getFormKey());
		List<FormProperty> list = taskFormData.getFormProperties();
		for(FormProperty fp : list){
			System.out.println("fp.getId() = " + fp.getId());
			System.out.println("fp.getName() = " + fp.getName());
			System.out.println("fp.getType() = " + fp.getType());
			System.out.println("fp.getValue() = " + fp.getValue());
			System.out.println("-------------------");
		}
		Map<String,String> map = new HashMap<String, String>();
		map.put("myformdemo", "abcde");
		map.put("v_id", "fgeggggg");
		formService.submitTaskFormData(taskId, map);
	}
	
	
}
















