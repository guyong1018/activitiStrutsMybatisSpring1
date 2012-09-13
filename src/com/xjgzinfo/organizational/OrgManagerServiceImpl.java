package com.xjgzinfo.organizational;

import java.util.ArrayList;
import java.util.List;

import com.xjgzinfo.vo.TOrgMapper;
import com.xjgzinfo.vo.TOrgUserMapper;
import com.xjgzinfo.vo.TRoleMapper;
import com.xjgzinfo.vo.TRoleUserMapper;
import com.xjgzinfo.vo.UserVoMapper;

/**
 * 组织结构管理类
 * @author guyong
 *
 */
public class OrgManagerServiceImpl implements OrgManagerService {
	/**
	 * 角色用户关系接口
	 */
	private TRoleUserMapper troleuserMapper;
	/**
	 * 部门用户关系接口
	 */
	private TOrgUserMapper torguserMapper;
	/**
	 * 部门查询接口
	 */
	private TOrgMapper torgMapper;
	/**
	 * 用户查询接口
	 */
	private UserVoMapper tuserMapper;
	/**
	 * 角色查询接口
	 */
	private TRoleMapper troleMapper;
	
	/**
	 * 根据部门id串获取对应的人员列表
	 */
	public List<UserVo> getUsersInOrgIds(List<String> orgIds) {
		List<String> orgidList = new ArrayList<String>();
		if(orgIds != null){
			for (int i = 0; i < orgIds.size(); i++) {
				String orgid = orgIds.get(i);
				orgidList.add(orgid);
				//递归获取部门id，放入list
				getOrgRecursion(orgid,orgidList);
			}
		}
		return torguserMapper.getUsersByOrgid(orgidList);
	}
	/**
	 * 根据角色id串获取对应的人员列表
	 */
	public List<UserVo> getUsersInRoleIds(List<String> roleIds) {
		return troleuserMapper.getUsersByRoleid(roleIds);
	}
	/**
	 * 根据人员id串获取人员列表
	 */
	public List<UserVo> getUsersInUserIds(List<String> userIds) {
		return tuserMapper.getUsersByuserids(userIds);
	}
	/**
	 * 部门递归
	 * @param orgUpId
	 */
	private void getOrgRecursion(String orgUpId,List<String> orgidList){
		List<OrgVo> orgVoList = torgMapper.getOrgRecursion(orgUpId);
		if(orgVoList != null && orgVoList.size() > 0){
			OrgVo orgVo = orgVoList.get(0);
			if(orgVo.getGroupid() != null){
				orgidList.add(orgVo.getGroupid().toString());
				getOrgRecursion(orgVo.getGroupid().toString(),orgidList);
			}
		}
		
	}
	/**
	 * 角色递归
	 * @param roleUpId
	 */
	private void getRoleRecursion(String roleUpId){
		//此demo角色没有上下级关系，所以不用递归.预留
	}
	/**
	 * 获得根部门
	 * @return 部门对象
	 */
	public OrgVo getOrgRoot() {
		List<OrgVo> orglist = torgMapper.getOrgRoot();
		if(orglist != null && orglist.size()>0)
			return orglist.get(0);
		return null;
	}
	/**
	 * 获得根角色
	 * @return 角色对象
	 */
	public RoleVo getRoleRoot() {
		//预留
		return null;
	}
	/**
	 * 根据部门id获取部门下的人员
	 * @param orgid
	 * @return
	 */
	public List<UserVo> getUserByOrg(String orgid){
		return torguserMapper.getOrgUser(orgid);
	}
	/**
	 * 根据角色id获取角色下的人员
	 * @param orgid
	 * @return
	 */
	public List<UserVo> getUserByRole(String roleid){
		return troleuserMapper.getRoleUser(roleid);
	}
	
	/**
	 * 获取指定部门下级部门列表
	 * @param uporgid
	 * @return
	 */
	public List<OrgVo> getOrgOrg(String uporgid){
		return torgMapper.getOrgOrg(uporgid);
	}
	/**
	 * 获取指定角色下级角色
	 * @param roleid
	 * @return
	 */
	public List<RoleVo> getRoleRole(String uproleid){
		//预留
		return null;
	}
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<RoleVo> getAllRoles(){
		return troleMapper.getAllRoles();
	}
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<UserVo> getAllUsers(){
		//预留
		return null;
	}
	/**
	 * 获取所有部门
	 * @return
	 */
	public List<UserVo> getAllGroups(){
		//预留
		return null;
	}
	public TRoleUserMapper getTroleuserMapper() {
		return troleuserMapper;
	}

	public void setTroleuserMapper(TRoleUserMapper troleuserMapper) {
		this.troleuserMapper = troleuserMapper;
	}

	public TOrgUserMapper getTorguserMapper() {
		return torguserMapper;
	}

	public void setTorguserMapper(TOrgUserMapper torguserMapper) {
		this.torguserMapper = torguserMapper;
	}
	public TOrgMapper getTorgMapper() {
		return torgMapper;
	}
	public void setTorgMapper(TOrgMapper torgMapper) {
		this.torgMapper = torgMapper;
	}
	
	public UserVoMapper getTuserMapper() {
		return tuserMapper;
	}
	public void setTuserMapper(UserVoMapper tuserMapper) {
		this.tuserMapper = tuserMapper;
	}
	public TRoleMapper getTroleMapper() {
		return troleMapper;
	}
	public void setTroleMapper(TRoleMapper troleMapper) {
		this.troleMapper = troleMapper;
	}
}
