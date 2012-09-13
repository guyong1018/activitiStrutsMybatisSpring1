package com.xjgzinfo.vo.activiti;

import java.util.List;
import java.util.Map;

public interface Act_Ru_TaskMapper {
	/**
	 * 待办列表
	 * @param userid
	 * @return
	 */
	public List<Map> getTaskInsList(String userid);
	/**
	 * 已办列表
	 * @param userid
	 * @return
	 */
	public List<Map> getTaskHisInsList(String userid);
	/**
	 * 监控列表
	 * @return
	 */
	public List<Map> getTaskMoniterList();
}
