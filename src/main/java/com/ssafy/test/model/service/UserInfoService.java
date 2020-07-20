package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.UserInfo;


public interface UserInfoService {
	
	public int insert(UserInfo info);
	public int update(UserInfo info);
	public int delete(String keyword);
	public UserInfo select(String keyword);
	public List<UserInfo> selectAll();
	
	public UserInfo login(UserInfo info);

}
