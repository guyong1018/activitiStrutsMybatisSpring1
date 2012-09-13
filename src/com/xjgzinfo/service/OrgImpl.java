package com.xjgzinfo.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.identity.Account;

public class OrgImpl implements IdentityService{

	public boolean checkPassword(String userid, String pwd) {
		
		return false;
	}

	public GroupQuery createGroupQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	public void createMembership(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public UserQuery createUserQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteGroup(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteMembership(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUser(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUserAccount(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUserInfo(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public Account getUserAccount(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getUserAccountNames(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserInfo(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getUserInfoKeys(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Picture getUserPicture(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Group newGroup(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public User newUser(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveGroup(Group arg0) {
		// TODO Auto-generated method stub
		
	}

	public void saveUser(User arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setAuthenticatedUserId(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setUserAccount(String arg0, String arg1, String arg2,
			String arg3, String arg4, Map<String, String> arg5) {
		// TODO Auto-generated method stub
		
	}

	public void setUserInfo(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	public void setUserPicture(String arg0, Picture arg1) {
		// TODO Auto-generated method stub
		
	}

}
