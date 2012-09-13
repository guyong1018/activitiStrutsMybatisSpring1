package com.xjgzinfo.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.impl.pvm.process.ActivityImpl;

import com.xjgzinfo.common.BaseAction;
import com.xjgzinfo.common.ProcessDefKeyName;
import com.xjgzinfo.organizational.OrgManagerService;
import com.xjgzinfo.organizational.OrgVo;
import com.xjgzinfo.organizational.RoleVo;
import com.xjgzinfo.organizational.UserVo;
import com.xjgzinfo.service.ExampleService;
import com.xjgzinfo.vo.TQjd;
import com.xjgzinfo.vo.TQjgc;

public class ExampleAction extends BaseAction {

	private ExampleService exampleService;

	private OrgManagerService orgManagerService;

	private String dstar;
	private String dend;
	private String qjly;
	private String processinsid;
	private String taskid;
	private String spyj;

	public String getSpyj() {
		return spyj;
	}

	public void setSpyj(String spyj) {
		this.spyj = spyj;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getProcessinsid() {
		return processinsid;
	}

	public void setProcessinsid(String processinsid) {
		this.processinsid = processinsid;
	}

	private TQjd tqjd = new TQjd();

	/**
	 * 保存请假申请单:请假申请,.example1流程启动
	 */
	public void saveQjd() {
		try {

			TQjd tqjd = new TQjd();
			tqjd.setQjly(qjly);
			tqjd.setQjr(((UserVo) session.get("tuser")).getName());
			Calendar sCalendar = Calendar.getInstance();
			sCalendar
					.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse(dstar));
			Calendar eCalendar = Calendar.getInstance();
			eCalendar.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse(dend));
			tqjd.setQjts(new BigDecimal(getDaysBetween(sCalendar, eCalendar)));
			exampleService.saveQjd(tqjd, ((UserVo) session.get("tuser"))
					.getUserid().longValue());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().write("false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 审核加载方法
	 * 
	 * @return
	 */
	public String loadQjd() {
		String state = request.getParameter("type");
		tqjd = exampleService.loadTQjd(processinsid);
		if ("update".equals(state)) {
			return "updatesucess";
		}
		return "loadsucess";
	}

	/**
	 * 审批请假单
	 */
	public void updateQjd() {
		try {
			String tybty = request.getParameter("spyjty");
			if (qjly != null && !"".equals(qjly)) {
				tqjd.setQjly(qjly);
			}
			String[] param = { spyj, ((UserVo) session.get("tuser")).getName(),
					taskid, tybty };
			exampleService.spqjd(tqjd, param);
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取请假审批过程
	 */
	public void getQjdGcList() {
		try {
			List<TQjgc> tqjgcList = exampleService.getQjdGcList(processinsid);
			JSONArray array = new JSONArray();
			for (TQjgc tqjgc : tqjgcList) {
				JSONObject json = new JSONObject();
				json.put("czr", tqjgc.getSpr());
				json.put("rwmc", tqjgc.getTaskname());
				json.put("spyj", tqjgc.getSpyj());
				SimpleDateFormat from = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				if (tqjgc.getSpsj() != null) {
					json.put("sj", from.format(tqjgc.getSpsj()));
				} else {
					json.put("sj", "");
				}
				array.add(json);
			}
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动排他分支流程示例.example2流程启动
	 */
	public void startFzPtfz() {
		try {
			String flow = request.getParameter("v_flow");
			String[] userIds = request.getParameterValues("userids[]");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("v_user", Arrays.asList(userIds));
			if (flow != null && !flow.equals("")) {
				param.put("v_flow", flow);
			} else {
				param.put("v_flow", "");
			}
			exampleService.startPaitaProcess(ProcessDefKeyName.EXAMPLE2_PTFZ,null,
					param,((UserVo) session.get("tuser")).getUserid().toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 排他分支完成工作项
	 */
	public void completeFzPtfz() {
		try {
			exampleService.completeProcess(((UserVo) session.get("tuser"))
					.getUserid().toString(), taskid, null);
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 并行分支流程启动.example3流程启动
	 */
	public void startBxfz() {
		try {
			String flow = request.getParameter("v_flow");
			Map<String, Object> param = new HashMap<String, Object>();
			if (flow != null && !flow.equals("")) {
				param.put("v_flow", flow);
			} else {
				param.put("v_flow", "");
			}
			exampleService.startPaitaProcess(ProcessDefKeyName.EXAMPLE3_BXFZ,null,
					param,((UserVo) session.get("tuser")).getUserid().toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 包容分支流程启动.example4流程启动
	 */
	public void startBrfz() {
		try {
			String flow = request.getParameter("v_flow");
			Map<String, Object> param = new HashMap<String, Object>();
			if (flow != null && !flow.equals("")) {
				param.put("v_flow", flow);
			} else {
				param.put("v_flow", "");
			}
			exampleService.startPaitaProcess(ProcessDefKeyName.EXAMPLE4_BRFZ,null,
					param,((UserVo) session.get("tuser")).getUserid().toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * java、脚本调用、监听.example6流程启动
	 * 
	 * @return
	 */
	public void startJavaSerLisInvoke() {
		try {
			String flow = request.getParameter("v_flow");
			Map<String, Object> param = new HashMap<String, Object>();
			if (flow != null && !flow.equals("")) {
				param.put("v_flow", flow);
			} else {
				param.put("v_flow", "");
			}
			exampleService.startPaitaProcess(
					ProcessDefKeyName.EXAMPLE6_SER_JAVA_LIS, null,param,((UserVo) session.get("tuser")).getUserid().toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 手工任务、接收触发、邮件示例中，手工触发
	 */
	public void sendReceiveTaskMsg() {
		try {
			// 为了方便，这里驱动的节点定义ID写死了
			exampleService.sendReceiveTaskMsg("receivetask1");
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 手工任务、接收触发、邮件示例中，启动
	 */
	public void startMailRec() {
		try {
			exampleService.startPaitaProcess(
					ProcessDefKeyName.EXAMPLE7_MAIL_REC, null,null,((UserVo) session.get("tuser")).getUserid().toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 子过程、边界事件、外部调用
	 */
	public void startSubBjEvent() {
		try {
			String flow = request.getParameter("v_flow");
			Map<String, Object> param = new HashMap<String, Object>();
			if (flow != null && !flow.equals("")) {
				param.put("v_flow", flow);
			} else {
				System.out.println("必须输入v_flow值,1或2.");
				return;
			}
			exampleService.startPaitaProcess(
					ProcessDefKeyName.EXAMPLE8_SUB_BJEVENT, null,param,((UserVo) session.get("tuser")).getUserid().toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会签（多实例）
	 */
	public void startHuiqian() {
		try {
			String[] userIds = request.getParameterValues("userids[]");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("assigneeList", Arrays.asList(userIds));
			exampleService.startPaitaProcess(
					ProcessDefKeyName.EXAMPLE9_HUIQIAN, null,param,((UserVo) session.get("tuser")).getUserid().toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自由流回退
	 */
	public void startFreeFlowBack() {
		try {
			exampleService.startPaitaProcess(
					ProcessDefKeyName.EXAMPLE10_ZYLBACK, null,null,((UserVo) session.get("tuser")).getUserid().toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取回退的流程节点
	 */
	public void getCombobox() {
		try {
			List<ActivityImpl> actList = exampleService
					.getActivityListByTaskId(taskid);
			JSONArray array = new JSONArray();
			for (ActivityImpl act : actList) {
				String type = act.getProperty("type").toString();
				if ("userTask".equals(type)) {
					JSONObject json = new JSONObject();
					json.put("taskid", act.getId());
					json.put("taskname", act.getProperty("name"));
					array.add(json);
				}
			}
			response.getWriter().write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自由流回退
	 * 
	 * @param taskId
	 *            当前活动实例ID
	 * @param activityId
	 *            回退到目的节点的活动定义ID
	 */
	public void freeFlow() {
		try {
			String activityId = request.getParameter("selectVar");
			if (activityId != null && activityId.equals("again")) {
				completeFzPtfz();
			} else {
				exampleService.freeFlow(taskid, activityId);
				response.getWriter().write("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 示例11，获取组织机构树
	 */
	public void getOrganizational() {
		try {
			// 获取根部门
			OrgVo orgVo = orgManagerService.getOrgRoot();

			JSONArray arrayRoot = new JSONArray();
			JSONObject json = new JSONObject();
			json.put("id", "Pselect");
			json.put("text", "参与者选择");

			JSONArray arrayoRoot = new JSONArray();

			// 构造部门选择树
			JSONObject jsonorg = new JSONObject();
			jsonorg.put("id", "Porg");
			jsonorg.put("text", "部门选择");
			jsonorg.put("state", "closed");
			JSONArray arrayOrgRoot = new JSONArray();
			JSONObject jsonOrgRoot = new JSONObject();
			jsonOrgRoot.put("id", "P" + orgVo.getGroupid());
			jsonOrgRoot.put("text", orgVo.getGroup_name());
			// 递归根部门下的子部门
			getOrgInfo(orgVo, jsonOrgRoot);

			arrayOrgRoot.add(jsonOrgRoot);
			jsonorg.put("children", arrayOrgRoot);
			arrayoRoot.add(jsonorg);

			// 构造角色树
			JSONObject jsonrole = new JSONObject();
			jsonrole.put("id", "Rrole");
			jsonrole.put("text", "角色选择");
			jsonrole.put("state", "closed");
			getRoleInfo(jsonrole);
			arrayoRoot.add(jsonrole);

			json.put("children", arrayoRoot);
			arrayRoot.add(json);

			response.setCharacterEncoding("utf-8");
			response.getWriter().write(arrayRoot.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自由流回退示例11，参与者表达式
	 */
	public void startParticipant() {
		try {

			String selectIds = request.getParameter("strids");
			String[] strIds = selectIds.split(",");
			StringBuffer sbExp = new StringBuffer();
			for (int i = 0; i < strIds.length; i++) {
				String strid = strIds[i];
				if (strid.equals("Pselect") || strid.equals("Porg")
						|| strid.equals("Rrole"))
					continue;
				String sid = strid.substring(1, strid.length());
				if (strid.startsWith("P")) {
					sbExp.append("P[");
					sbExp.append(sid);
					sbExp.append("]");
				} else if (strid.startsWith("R")) {
					sbExp.append("R[");
					sbExp.append(sid);
					sbExp.append("]");
				} else if (strid.startsWith("U")) {
					sbExp.append("U[");
					sbExp.append(sid);
					sbExp.append("]");
				}
				if (i < strIds.length - 1) {
					sbExp.append("+");
				}
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			System.out.println(sbExp.toString());
			if (sbExp.length() > 0)
				paramMap.put("org_user", sbExp.toString());
			exampleService.startPaitaProcess(ProcessDefKeyName.EXAMPLE11_ORG,null,
					paramMap,((UserVo) session.get("tuser")).getUserid().toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取角色树
	 * 
	 * @param jsonOrgRoot
	 */
	private void getRoleInfo(JSONObject jsonRoleRoot) {
		List<RoleVo> roleVoList = orgManagerService.getAllRoles();
		if (roleVoList != null) {
			JSONArray arrayChildren = new JSONArray();
			for (int i = 0; i < roleVoList.size(); i++) {
				RoleVo roleVo = roleVoList.get(i);
				JSONObject json1 = new JSONObject();
				json1.put("id", "R" + roleVo.getRoleid());
				json1.put("text", roleVo.getRolename());
				json1.put("state", "closed");
				List<UserVo> usersList = orgManagerService.getUserByRole(roleVo
						.getRoleid().toString());
				if (usersList != null) {
					JSONArray arrayUsers = new JSONArray();
					for (int j = 0; j < usersList.size(); j++) {
						UserVo userVo = usersList.get(j);
						JSONObject json2 = new JSONObject();
						json2.put("id", "U" + userVo.getUserid());
						json2.put("text", userVo.getName());
						json2.put("iconCls", "icon-ok");
						json2.put("state", "closed");
						arrayUsers.add(json2);
					}
					json1.put("children", arrayUsers);
				}
				arrayChildren.add(json1);
			}
			jsonRoleRoot.put("children", arrayChildren);
		}
	}

	/**
	 * 获取组织机构部门树
	 * 
	 * @param orgVo
	 * @param jsonOrgRoot
	 */
	private void getOrgInfo(OrgVo orgVo, JSONObject jsonOrgRoot) {

		// 获取指定部门下人员列表
		List<UserVo> userList = orgManagerService.getUserByOrg(orgVo
				.getGroupid().toString());
		JSONArray arrayChildren = new JSONArray();
		if (userList != null && userList.size() > 0) {
			for (int i = 0; i < userList.size(); i++) {
				UserVo userVo = userList.get(i);
				JSONObject json2 = new JSONObject();
				json2.put("id", "U" + userVo.getUserid());
				json2.put("text", userVo.getName());
				json2.put("iconCls", "icon-ok");
				arrayChildren.add(json2);
			}
		}

		// 获取指定部门下级部门列表
		List<OrgVo> orgList = orgManagerService.getOrgOrg(orgVo.getGroupid()
				.toString());
		if (orgList != null && orgList.size() > 0) {
			for (int i = 0; i < orgList.size(); i++) {
				OrgVo org = orgList.get(i);
				JSONObject json2 = new JSONObject();
				json2.put("id", "P" + org.getGroupid());
				json2.put("text", org.getGroup_name());
				json2.put("state", "closed");
				// 递归
				getOrgInfo(org, json2);
				arrayChildren.add(json2);
			}
		}
		jsonOrgRoot.put("children", arrayChildren);
	}

	public static void main(String[] args) {
		JSONArray array = new JSONArray();

		for (int i = 0; i < 4; i++) {
			JSONObject json = new JSONObject();
			json.put("id", "1");
			json.put("text", "Folder1");

			JSONArray arr2 = new JSONArray();
			JSONObject json2 = new JSONObject();
			json2.put("id", "2");
			json2.put("text", "childrenFolder2");

			arr2.add(json2);

			json.put("children", arr2);
			array.add(json);
		}
		System.out.println(array.toString());
	}

	public String getDstar() {
		return dstar;
	}

	public void setDstar(String dstar) {
		this.dstar = dstar;
	}

	public String getDend() {
		return dend;
	}

	public void setDend(String dend) {
		this.dend = dend;
	}

	public String getQjly() {
		return qjly;
	}

	public void setQjly(String qjly) {
		this.qjly = qjly;
	}

	/**
	 * 计算两个日期的天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);

			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	public TQjd getTqjd() {
		return tqjd;
	}

	public void setTqjd(TQjd tqjd) {
		this.tqjd = tqjd;
	}

	public ExampleService getExampleService() {
		return exampleService;
	}

	public void setExampleService(ExampleService exampleService) {
		this.exampleService = exampleService;
	}

	public OrgManagerService getOrgManagerService() {
		return orgManagerService;
	}

	public void setOrgManagerService(OrgManagerService orgManagerService) {
		this.orgManagerService = orgManagerService;
	}
}
