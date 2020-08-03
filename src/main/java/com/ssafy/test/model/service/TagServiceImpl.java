package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.TagDAO;
import com.ssafy.test.model.dto.Tag;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	TagDAO Dao;
	
	@Override
	public List<Tag> selectAll() {
		// TODO Auto-generated method stub
		return Dao.selectAll();
	}

	@Override
	public int insert(Tag v) {
		// TODO Auto-generated method stub
		return Dao.insert(v);
	}

	@Override
	public int delete(String k) {
		// TODO Auto-generated method stub
		return Dao.delete(k);
	}

}
