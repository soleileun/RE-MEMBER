package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.UserInfo;


public interface UserInfoDao {

	public int insert(UserInfo info);
	public int update(UserInfo info);
	public int updateVal(String id);
	public int delete(String keyword);
	public UserInfo select(String keyword);
	public List<UserInfo> selectAll();
	
	public UserInfo login(UserInfo info);
	public UserInfo signin(String id, String pw);
	
}
