package com.xjgzinfo.vo;

import java.util.List;

import com.xjgzinfo.organizational.OrgVo;

public interface TOrgMapper {
	
	public List<OrgVo> getOrgRecursion(String orgid);
	
	public List<OrgVo> getOrgRoot();
	
	public List<OrgVo> getOrgOrg(String uporgid);
	
}
