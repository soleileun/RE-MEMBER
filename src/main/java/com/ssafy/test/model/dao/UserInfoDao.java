package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.dto.Project;
import com.ssafy.test.model.dto.SearchParameter;
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
	
	public List<UserInfo> getCurrList();
	
	public List<UserInfo> selectByAddr(Addr v);
	
	public List<UserInfo> searchAll(SearchParameter sp);
	
	public List<UserInfo> getRecommendedUser(String id);
	public List<Project> getRecommendedPJT(String id);
}
