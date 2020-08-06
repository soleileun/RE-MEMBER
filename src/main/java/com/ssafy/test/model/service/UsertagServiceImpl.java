package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.UsertagDAO;
import com.ssafy.test.model.dto.AddrAndTag;
import com.ssafy.test.model.dto.Interest;
import com.ssafy.test.model.dto.TagList;
import com.ssafy.test.model.dto.TagResult;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.dto.Usertag;

@Service
public class UsertagServiceImpl implements UsertagService{

	@Autowired
	UsertagDAO Dao;

	@Override
	public List<Interest> selectAll(String k) {
		// TODO Auto-generated method stub
		return Dao.selectAll(k);
	}

	@Override
	public Interest select(Interest v) {
		// TODO Auto-generated method stub
		return Dao.select(v);
	}

	@Override
	public int insert(Interest v) {
		// TODO Auto-generated method stub
		return Dao.insert(v);
	}

	@Override
	public int delete(Interest v) {
		// TODO Auto-generated method stub
		return Dao.delete(v);
	}

	@Override
	public List<UserInfo> selectSame(TagList v) {
		// TODO Auto-generated method stub
		return Dao.selectSame(v);
	}

	@Override
	public List<UserInfo> selectSimilar(TagList v) {
		// TODO Auto-generated method stub
		return Dao.selectSimilar(v);
	}

	@Override
	public List<UserInfo> selectAddrAndTag(AddrAndTag v) {
		// TODO Auto-generated method stub
		return Dao.selectAddrAndTag(v);
	}
	
}
