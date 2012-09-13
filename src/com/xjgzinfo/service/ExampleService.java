package com.xjgzinfo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.pvm.process.ActivityImpl;

import com.xjgzinfo.vo.TQjd;
import com.xjgzinfo.vo.TQjgc;

/**
 * 示例接口
 * 
 * @author Administrator
 * 
 */
public interface ExampleService {
	/********************* 示例流程1-请假 ***********************/
	/**
	 * 保存请假单
	 * 
	 * @param tqjd
	 * @param userid
	 */
	public void saveQjd(TQjd tqjd, Long userid) throws Exception;

	/**
	 * 审核加载请假单
	 * 
	 * @param processInsid
	 * @return
	 */
	public TQjd loadTQjd(String processInsid);

	/**
	 * 审批请假单
	 * 
	 * @param tqjd
	 * @param param
	 *            审批意见，审批人，活动示例ID
	 */
	public void spqjd(TQjd tqjd, String... param);

	/**
	 * 查询请假审批过程
	 * 
	 * @param processinsid
	 * @return
	 */
	public List<TQjgc> getQjdGcList(String processinsid);

	/********************* 示例流程2-排他分支 ***********************/
	public void startPaitaProcess(String processDefKey,String businessKey,Map<String, Object> paramMap,String startUserId);

	public void completeProcess(String userId, String taskId,
			Map<String, Object> paramMap);

	public void sendReceiveTaskMsg(String activityId);

	public List<ActivityImpl> getActivityListByTaskId(String taskId);

	public void freeFlow(String taskId, String activityId);
}
