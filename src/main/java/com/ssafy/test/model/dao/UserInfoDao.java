package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.dto.Pools;
import com.ssafy.test.model.dto.Project;
import com.ssafy.test.model.dto.SearchParameter;
import com.ssafy.test.model.dto.Two;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.dto.UsersInterest;


public interface UserInfoDao {

	public int insert(UserInfo info);
	public int update(UserInfo info);
	public int updateVal(String id);
	public int updateLastDate(UserInfo user);
	public int delete(String keyword);
	public UserInfo select(String keyword);
	public List<UserInfo> selectAll();
	
	public UserInfo login(UserInfo info);
	public UserInfo signin(String id, String pw);
	
	public List<UserInfo> getCurrList();
	
	public List<UserInfo> selectByAddr(Addr v);
	
	public List<Pools> searchAll(SearchParameter sp);
	public Pools searchPoolById(String id);
	
	public List<UserInfo> getRecommendedUser(String id);
	public List<Project> getRecommendedPJT(String id);
	
	public List<UserInfo> getAddressList(int pid);
	
	public List<Pools> getPools(); //Two<Integer,Integer> v
	public UserInfo loginForKakao(String kakaoId);
	
	public List<UsersInterest> getUserByInterest(String userid);
}
