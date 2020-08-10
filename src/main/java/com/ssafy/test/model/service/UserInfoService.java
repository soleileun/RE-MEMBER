package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.dto.SearchParameter;
import com.ssafy.test.model.dto.UserInfo;


public interface UserInfoService {
	
	public int insert(UserInfo info);
	public int update(UserInfo info);
	public int updateVal(String id);
	public int delete(String keyword);
	public UserInfo select(String keyword);
	public List<UserInfo> selectAll();
	
	
	public UserInfo login(UserInfo info);
	//로그인은 고정값을 이용할 것
	public UserInfo signin(String id, String pw);
	public String getServerInfo();
	public List<UserInfo> getCurrList();
	public List<UserInfo> selectByAddr(Addr v);
	
	public List<UserInfo> searchAll(SearchParameter sp);

}
