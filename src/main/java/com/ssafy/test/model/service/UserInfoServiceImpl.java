package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.UserInfoDao;
import com.ssafy.test.model.dto.UserInfo;

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

}
