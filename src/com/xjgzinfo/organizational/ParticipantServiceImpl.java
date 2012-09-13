package com.xjgzinfo.organizational;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 参与者表达式管理类
 * 
 * @author guyong
 * 
 */
public class ParticipantServiceImpl implements ParticipantService {
	/**
	 * 组织机构管理接口
	 */
	private OrgManagerService orgManagerService;

	public Map<String, Object> saveParseParticipantByVariable(
			Map<String, Object> paramMap) {
		try {
			if (paramMap != null && paramMap.size() > 0) {
				Set<String> keyset = paramMap.keySet();
				Iterator<String> keyIter = keyset.iterator();
				while (keyIter.hasNext()) {
					String strKey = keyIter.next();
					Object paramValue = paramMap.get(strKey);
					if (paramValue instanceof String) {
						// 如果map值是string类型，获取表达式，例如P[1+2+4]+R[3+2]+U[1]
						String strParamValue = paramValue.toString().toUpperCase();
						if (strParamValue.startsWith("P[") || strParamValue.startsWith("R[")
								|| strParamValue.startsWith("U[")) {
							String[] strExps = strParamValue.split("\\+");
							// 用户参与者List列表
							List<String> userIdList = new ArrayList<String>();
							for (int i = 0; i < strExps.length; i++) {
								String strParticipant = strExps[i];

								if (strParticipant.startsWith("P[")) {
									// 查询部门表达式对应的人员列表
									parseExp(strParticipant, userIdList, "P");
								} else if (strParticipant.startsWith("U[")) {
									// 查询人员表达式对应的人员列表
									parseExp(strParticipant, userIdList, "U");
								} else if (strParticipant.startsWith("R[")) {
									// 查询角色表达式对应的人员列表
									parseExp(strParticipant, userIdList, "R");
								}
							}
							userIdList = filterList(userIdList);
							// 覆盖参与者表达式为用户列表
							paramMap.put(strKey, userIdList);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramMap;
	}

	/**
	 * 根据类型查询部门、角色、用户对应的用户ID列表
	 * 
	 * @param strParticipant
	 *            参与者表达式
	 * @param userIdList
	 *            保存节点责任人的list列表
	 * @param flag
	 *            P部门，R角色，U用户
	 * @param idType
	 *            S主键是字符型，N主键是数据型
	 */
	private void parseExp(String strParticipant, List<String> userIdList,
			String flag) {
		
		String strExp = strParticipant.substring(2,strParticipant.lastIndexOf("]"));
		String[] strIds = strExp.split("\\,");
		List<String> idsList = new ArrayList<String>();
		
		for (int j = 0; j < strIds.length; j++) {
			String strid = strIds[j];
			// 根据部门Id查询用户ID
			idsList.add(strid);
		}
		idsList = filterList(idsList);//过滤重复
		// 查询用户列表
		List<UserVo> userList = null;
		if ("P".equals(flag)) {
			userList = orgManagerService.getUsersInOrgIds(idsList);
		} else if ("R".equals(flag)) {
			userList = orgManagerService.getUsersInRoleIds(idsList);
		} else if ("U".equals(flag)) {
			userList = orgManagerService.getUsersInUserIds(idsList);
		}
		if (userList != null) {
			for (UserVo userVo : userList) {
				userIdList.add(userVo.getUserid().toString());
			}
		}
		
	}

	/**
	 * 过滤list中重复的记录
	 * 
	 * @param list
	 * @return
	 */
	private List<String> filterList(List<String> list) {
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			String element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}

	public OrgManagerService getOrgManagerService() {
		return orgManagerService;
	}

	public void setOrgManagerService(OrgManagerService orgManagerService) {
		this.orgManagerService = orgManagerService;
	}
}
