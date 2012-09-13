package com.xjgzinfo.vo;

import java.util.List;

import com.xjgzinfo.organizational.UserVo;

public interface TRoleUserMapper {

	public List<UserVo> getUsersByRoleid(List<String> roleids);
	
	public List<UserVo> getRoleUser(String roleid);
}
