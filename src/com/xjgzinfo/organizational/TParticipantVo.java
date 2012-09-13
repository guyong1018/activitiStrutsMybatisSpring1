package com.xjgzinfo.organizational;

import java.util.Date;

/**
 * 参与者vo类
 * 
 * @author Administrator
 * 
 */
public class TParticipantVo {

	/**
	 * 参与者ID
	 */
	private Long id;
	/**
	 * 流程示例ID
	 */
	private String proinsid;
	/**
	 * 参与者表达式名称，变量名
	 */
	private String name;
	/**
	 * 参与者表达式，例如：P[1+2]+R[2]+U[2+3]
	 */
	private String exp;
	/**
	 * 参与者表达式汉字，例如：部门1+部门2+人员1+角色1
	 */
	private String hzexp;
	/**
	 * 参与者表达式对应的人员汉字，例如：用户1+用户2+用户3
	 */
	private String userexp;
	/**
	 * 记录创建时间，数据库默认当前时间
	 */
	private Date createdate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProinsid() {
		return proinsid;
	}

	public void setProinsid(String proinsid) {
		this.proinsid = proinsid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getHzexp() {
		return hzexp;
	}

	public void setHzexp(String hzexp) {
		this.hzexp = hzexp;
	}

	public String getUserexp() {
		return userexp;
	}

	public void setUserexp(String userexp) {
		this.userexp = userexp;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
}
