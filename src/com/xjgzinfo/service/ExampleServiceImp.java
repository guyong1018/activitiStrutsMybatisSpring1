package com.xjgzinfo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

import com.xjgzinfo.activiti.ActivitiManager;
import com.xjgzinfo.common.ProcessDefKeyName;
import com.xjgzinfo.vo.TQjd;
import com.xjgzinfo.vo.TQjdExample;
import com.xjgzinfo.vo.TQjdExample.Criteria;
import com.xjgzinfo.vo.TQjdMapper;
import com.xjgzinfo.vo.TQjgc;
import com.xjgzinfo.vo.TQjgcExample;
import com.xjgzinfo.vo.TQjgcMapper;
/**
 * 示例服务处理类
 * @author Administrator
 *
 */
public class ExampleServiceImp implements ExampleService {
	private TQjdMapper tqjdMapper;
	private TQjgcMapper tqjgcMapper;
	private ActivitiManager activitiManager;

	/*********************示例流程1-请假
	 * @throws Exception ***********************/
	public void saveQjd(TQjd tqjd,Long userid) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("v_user", userid.toString());
		param.put("v_day", tqjd.getQjts());
		String processinsid = activitiManager.startFlowProcess(ProcessDefKeyName.EXAMPLE1_PTLC_QJ,null, param,userid.toString());
		tqjd.setProid(processinsid);
		tqjdMapper.insert(tqjd);
		TQjgc tqjgc = new TQjgc();
		tqjgc.setProid(processinsid);
		tqjgc.setQjr(tqjd.getQjr());
		tqjgc.setSpsj(new Date());
		tqjgc.setQjsj(new Date());
		tqjgc.setSpr(tqjd.getQjr());
		tqjgc.setQjts(tqjd.getQjts());
		tqjgc.setTaskname("起草请假单");
		tqjgcMapper.insert(tqjgc);
	}
	
	public TQjd loadTQjd(String processInsid){
		TQjdExample qjd = new TQjdExample();
		Criteria criteria = qjd.createCriteria();
		criteria.andProidEqualTo(processInsid);
		List<TQjd> list = tqjdMapper.selectByExample(qjd);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	/**
	 * 审批请假单
	 * @param tqjd
	 * @param param 审批意见，审批人，活动示例ID
	 */
	public void spqjd(TQjd tqjd,String... param){
		tqjdMapper.updateByPrimaryKey(tqjd);
		TQjgc tqjgc = new TQjgc();
		tqjgc.setProid(tqjd.getProid());
		tqjgc.setQjr(tqjd.getQjr());
		tqjgc.setQjts(tqjd.getQjts());
		tqjgc.setSpyj(param[0]);
		tqjgc.setSpr(param[1]);
		tqjgc.setSpsj(new Date());
		TaskEntity te = activitiManager.findTaskById(param[2]);
		tqjgc.setTaskname(te.getName());
		tqjgcMapper.insert(tqjgc);

		Map<String,Object> flowparam = new HashMap<String,Object>();
		
		flowparam.put("v_day", tqjd.getQjts());
		if(te.getName().equals("总经理审批")){
			flowparam.put("v_spr", "zjl");
		}else if(te.getName().equals("部门经理审批")){
			flowparam.put("v_spr", "bmjl");
		}
		if("yes".equals(param[3])){
			flowparam.put("v_agree", "yes");
		}else{
			flowparam.put("v_agree", "no");
		}
		activitiManager.completeWorkItem(null, param[2], flowparam);
	}
	
	public List<TQjgc> getQjdGcList(String processinsid){
		TQjgcExample qjgc = new TQjgcExample();
		com.xjgzinfo.vo.TQjgcExample.Criteria criteria = qjgc.createCriteria();
		criteria.andProidEqualTo(processinsid);
		qjgc.setOrderByClause("spsj");
		return tqjgcMapper.selectByExample(qjgc);
	}
	
	/*********************示例流程2-排他分支***********************/
	public void startPaitaProcess(String processDefKey,String businessKey,Map<String, Object> paramMap,String startUserId) {
		activitiManager.startFlowProcess(processDefKey,businessKey,paramMap,startUserId);
		
	}
	/**
	 * 排他分支完成工作项
	 * @param userId
	 * @param taskId
	 * @param paramMap
	 */
	public void completeProcess(String userId,String taskId,Map<String,Object> paramMap){
		activitiManager.completeWorkItem(userId, taskId, paramMap);
	}
	
	/**
	 * 手动驱动
	 * @param activityId 驱动的节点定义ID
	 */
	public void sendReceiveTaskMsg(String activityId) {
		activitiManager.sendReceiveTaskMsg(activityId);
	}
	
	public List<ActivityImpl> getActivityListByTaskId(String taskId)
	{
		return activitiManager.getActivityListByTaskId(taskId);
	}
	/**
	 * 自由流回退
	 * @param taskId
	 * @param activityId
	 */
	public void freeFlow(String taskId,String activityId){
		activitiManager.freeFlow(taskId, activityId);
	}
	
	public TQjdMapper getTqjdMapper() {
		return tqjdMapper;
	}

	public void setTqjdMapper(TQjdMapper tqjdMapper) {
		this.tqjdMapper = tqjdMapper;
	}

	public TQjgcMapper getTqjgcMapper() {
		return tqjgcMapper;
	}

	public void setTqjgcMapper(TQjgcMapper tqjgcMapper) {
		this.tqjgcMapper = tqjgcMapper;
	}

	public ActivitiManager getActivitiManager() {
		return activitiManager;
	}

	public void setActivitiManager(ActivitiManager activitiManager) {
		this.activitiManager = activitiManager;
	}

}
