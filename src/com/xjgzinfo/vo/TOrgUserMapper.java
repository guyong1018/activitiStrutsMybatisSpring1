package com.xjgzinfo.vo;

import java.util.List;

import com.xjgzinfo.organizational.UserVo;

public interface TOrgUserMapper {

	public List<UserVo> getUsersByOrgid(List<String> orgids);
	
	public List<UserVo> getOrgUser(String orgid);
}
