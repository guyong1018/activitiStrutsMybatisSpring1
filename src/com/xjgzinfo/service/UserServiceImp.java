package com.xjgzinfo.service;

import java.math.BigDecimal;
import java.util.List;

import com.xjgzinfo.organizational.UserVo;
import com.xjgzinfo.organizational.UserVoExample;
import com.xjgzinfo.organizational.UserVoExample.Criteria;
import com.xjgzinfo.vo.UserVoMapper;

public class UserServiceImp implements UserService {

	private UserVoMapper tuserMapper;
	
	public UserVoMapper getTuserMapper() {
		return tuserMapper;
	}

	public void setTuserMapper(UserVoMapper tuserMapper) {
		this.tuserMapper = tuserMapper;
	}

	public UserVo checkLogin(String username, String pwd) {
		UserVoExample tUserExample = new UserVoExample();
		Criteria criteria = tUserExample.createCriteria();
		criteria.andUsernameEqualTo(username).andPasswordEqualTo(pwd);
		List<UserVo> userList = tuserMapper.selectByExample(tUserExample);
		if(userList.size()==0)
			return null;
		return userList.get(0);
	}

	public UserVo getUserAll(Long userid) {
		return tuserMapper.selectByPrimaryKey(new BigDecimal(userid));
	}

}
