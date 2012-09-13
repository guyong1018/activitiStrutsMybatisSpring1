package com.xjgzinfo.organizational;

import java.util.List;
/**
 * 组织结构管理接口
 * @author guyong
 *
 */
public interface OrgManagerService {

	/**
	 * 根据部门Id串获取用户列表
	 * @param orgIds 部门ID串
	 * @return 用户列表list
	 */
	public List<UserVo> getUsersInOrgIds(List<String> orgIds);
	/**
	 * 根据角色Id串获取用户列表
	 * @param roleIds 角色ID串
	 * @return 用户列表list
	 */
	public List<UserVo> getUsersInRoleIds(List<String> roleIds);
	/**
	 * 根据人员id串获取用户列表
	 * @param userIds 用户id串
	 * @return
	 */
	public List<UserVo> getUsersInUserIds(List<String> userIds);
	/**
	 * 获得根部门
	 * @return 部门对象
	 */
	public OrgVo getOrgRoot();
	/**
	 * 获得根角色
	 * @return 角色对象
	 */
	public RoleVo getRoleRoot();
	/**
	 * 根据部门id获取部门下的人员列表
	 * @param orgid
	 * @return
	 */
	public List<UserVo> getUserByOrg(String orgid);
	/**
	 * 根据角色id获取角色下的人员列表
	 * @param orgid
	 * @return
	 */
	public List<UserVo> getUserByRole(String roleid);
	/**
	 * 获取指定部门下级部门列表
	 * @param uporgid
	 * @return
	 */
	public List<OrgVo> getOrgOrg(String uporgid);
	/**
	 * 获取指定角色下级角色
	 * @param roleid
	 * @return
	 */
	public List<RoleVo> getRoleRole(String roleid);
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<RoleVo> getAllRoles();
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<UserVo> getAllUsers();
	/**
	 * 获取所有部门
	 * @return
	 */
	public List<UserVo> getAllGroups();
	/**
	 * 根据指定部门递归获取部门对应人员
	 * @param orgVo
	 * @return
	 *//*
	public Map<OrgVo,List<UserVo>> getOrgSystem(OrgVo orgVo);
	
	*//**
	 * 根据指定角色递归获取角色对应人员
	 * @param orgVo
	 * @return
	 *//*
	public Map<RoleVo,List<UserVo>> getRoleSystem(RoleVo orgVo);*/
}
