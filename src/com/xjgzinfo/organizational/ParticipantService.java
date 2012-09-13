package com.xjgzinfo.organizational;

import java.util.Map;
/**
 * 参与者表达式管理解析类
 * @author guyong
 *
 */
public interface ParticipantService {
	/**
	 * 解析变量中的参与者表达式
	 * P部门，R角色，U人员，例如 P[1+2]+R[2]+U[1]
	 * @param paraMap 需要解析的参与者变量
	 * @return 解析后的变量
	 */
	public Map<String,Object> saveParseParticipantByVariable(Map<String,Object> paramMap);
}
