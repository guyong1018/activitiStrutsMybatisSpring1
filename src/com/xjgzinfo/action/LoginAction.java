package com.xjgzinfo.action;

import java.io.IOException;

import com.xjgzinfo.common.BaseAction;
import com.xjgzinfo.organizational.UserVo;
import com.xjgzinfo.service.UserService;

public class LoginAction extends BaseAction {

	private String username;
	private String password;
	private UserService userService;
	private String state;
	
	public String login() {
		UserVo tuser = null;
		try {
			if ("jquery".equals(state)) {
				tuser = userService.checkLogin(username, password);
				if (tuser == null)
					response.getWriter().write("false");
				else {
					session.put("tuser", tuser);
					return "yes";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
