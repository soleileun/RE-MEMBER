package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.UserInfoDao;
import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.dto.Pools;
import com.ssafy.test.model.dto.Project;
import com.ssafy.test.model.dto.SearchParameter;
import com.ssafy.test.model.dto.Two;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.dto.UsersInterest;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	
	UserInfoDao uDao;
	
	@Override
	public int insert(UserInfo info) {
		// TODO Auto-generated method stub
		return uDao.insert(info);
	}

	@Override
	public int update(UserInfo info) {
		// TODO Auto-generated method stub
		return uDao.update(info);
	}

	@Override
	public int delete(String keyword) {
		// TODO Auto-generated method stub
		return uDao.delete(keyword);
	}

	@Override
	public UserInfo select(String keyword) {
		// TODO Auto-generated method stub
		return uDao.select(keyword);
	}

	@Override
	public List<UserInfo> selectAll() {
		// TODO Auto-generated method stub
		return uDao.selectAll();
	}

	@Override
	public UserInfo login(UserInfo info) {
		// TODO Auto-generated method stub
		return uDao.login(info);
	}

	@Override
	public UserInfo signin(String id, String pw) {
		//System.out.println("serviceimpl");
		return uDao.signin(id, pw);
	}

	@Override
	public String getServerInfo() {
		// TODO Auto-generated method stub
		return "TRY BY EUNJOUNG...";
	}

	@Override
	public int updateVal(String id) {
		// TODO Auto-generated method stub
		return uDao.updateVal(id);
	}

	@Override
	public List<UserInfo> getCurrList() {
		return uDao.getCurrList();
	}

	@Override
	public List<UserInfo> selectByAddr(Addr v) {
		// TODO Auto-generated method stub
		return uDao.selectByAddr(v);
	}

	@Override
	public List<Pools> searchAll(SearchParameter sp) {
		return uDao.searchAll(sp);
	}

	@Override
	public List<UserInfo> getRecommendedUser(String id) {
		// TODO Auto-generated method stub
		return uDao.getRecommendedUser(id);
	}

	@Override
	public List<Project> getRecommendedPJT(String id) {
		// TODO Auto-generated method stub
		return uDao.getRecommendedPJT(id);
	}

	@Override
	public List<UserInfo> getAddressList(int pid) {
		return uDao.getAddressList(pid);
	}

	@Override
	public int updateLastDate(UserInfo user) {
		// TODO Auto-generated method stub
		return uDao.updateLastDate(user);
	}

	@Override
	public List<Pools> getPools() {//Two<Integer,Integer> v
		return uDao.getPools();
	}

	@Override
	public UserInfo loginForKakao(String kakaoId) {
		// TODO Auto-generated method stub
		return uDao.loginForKakao(kakaoId);
	}

	@Override
	public Pools searchPoolById(String id) {
		// TODO Auto-generated method stub
		return uDao.searchPoolById(id);
	}

	@Override
	public List<UsersInterest> getUserByInterest(String userid) {
		// TODO Auto-generated method stub
		return  uDao.getUserByInterest(userid);
	}

}
