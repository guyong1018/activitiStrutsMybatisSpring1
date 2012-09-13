package com.xjgzinfo.common;

import java.util.HashMap;
import java.util.Map;

public class ActivityVo {
	// {default=null, triggeredByEvent=false, initial=Activity(startevent2),
	// name=子过程调用, documentation=null, line=5,
	// timerDeclarations=[org.activiti.engine.impl.jobexecutor.TimerDeclarationImpl@1f058f3],
	// errorEventDefinitions=[org.activiti.engine.impl.bpmn.parser.ErrorEventDefinition@5fd428],
	// type=subProcess, isExpanded=true}
	private String activityId;// 活动定义id
	private int height;// 高度
	private int width;// 宽度
	private int x;// x坐标
	private int y;// y坐标
	private String documentation;// 节点说明
	private String type;// 节点类型
	private boolean Highlight = false;// 是否当前节点，高亮显示
	private boolean isHis = false;// 是否已处理过，是已办节点
	private String createTime;// 创建时间
	private String endTime;// 活动示例结束时间
	private String taskUser;// 活动责任人

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTaskUser() {
		return taskUser;
	}

	public void setTaskUser(String taskUser) {
		this.taskUser = taskUser;
	}

	public boolean isHis() {
		return isHis;
	}

	public void setHis(boolean isHis) {
		this.isHis = isHis;
	}

	public boolean isHighlight() {
		return Highlight;
	}

	public void setHighlight(boolean highlight) {
		Highlight = highlight;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getType() {
		return getTaskType(type);
	}

	public void setType(String type) {
		this.type = type;
	}

	private String getTaskType(String type) {
		Map<String, String> types = new HashMap<String, String>();
		types.put("userTask", "用户任务");
		types.put("serviceTask", "系统任务");
		types.put("startEvent", "开始节点");
		types.put("endEvent", "结束节点");
		types.put("exclusiveGateway", "排他分支任务");
		types.put("parallelgateway", "并行分支任务");
		types.put("inclusiveGateway", "包容分支任务");
		types.put("callActivity", "子流程");
		types.put("scriptTask", "脚本任务");
		types.put("manualTask", "手工任务");
		types.put("receiveTask", "接收触发任务");
		types.put("mailTask", "邮件任务");
		return types.get(type) == null ? type : types.get(type);
	}
}
