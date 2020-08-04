package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.UsertagDAO;
import com.ssafy.test.model.dto.TagList;
import com.ssafy.test.model.dto.TagResult;
import com.ssafy.test.model.dto.Usertag;

@Service
public class UsertagServiceImpl implements UsertagService{

	@Autowired
	UsertagDAO Dao;

	@Override
	public List<Usertag> selectAll(String k) {
		// TODO Auto-generated method stub
		return Dao.selectAll(k);
	}

	@Override
	public Usertag select(Usertag v) {
		// TODO Auto-generated method stub
		return Dao.select(v);
	}

	@Override
	public int insert(Usertag v) {
		// TODO Auto-generated method stub
		return Dao.insert(v);
	}

	@Override
	public int delete(Usertag v) {
		// TODO Auto-generated method stub
		return Dao.delete(v);
	}

	@Override
	public List<String> selectSame(TagList v) {
		// TODO Auto-generated method stub
		return Dao.selectSame(v);
	}

	@Override
	public List<TagResult> selectSimilar(TagList v) {
		// TODO Auto-generated method stub
		return Dao.selectSimilar(v);
	}
	
}
