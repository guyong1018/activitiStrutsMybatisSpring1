package com.xjgzinfo.organizational;

/**
 * 部门VO类
 * @author guyong
 *
 */
public class OrgVo {
	/**
	 * 部门id
	 */
	private Long groupid;
	/**
	 * 部门名称
	 */
	private String group_name;
	/**
	 * 上级部门id
	 */
	private Long up_groupid;

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public Long getUp_groupid() {
		return up_groupid;
	}

	public void setUp_groupid(Long up_groupid) {
		this.up_groupid = up_groupid;
	}

}
