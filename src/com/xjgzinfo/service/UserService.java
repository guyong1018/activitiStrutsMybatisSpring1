package com.xjgzinfo.service;

import com.xjgzinfo.organizational.UserVo;

public interface UserService {

	public UserVo checkLogin(String username,String pwd);
	
	public UserVo getUserAll(Long userid);
	
}
