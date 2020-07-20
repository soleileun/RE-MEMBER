package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.PinterestDAO;
import com.ssafy.test.model.dto.Pinterest;

@Service
public class PinterestServiceImpl implements PinterestService{

	@Autowired
	PinterestDAO Dao;

	@Override
	public List<Pinterest> selectAll() {

		return Dao.selectAll();
	}

	@Override
	public List<Pinterest> select(int k) {
		return Dao.select(k);
	}

	@Override
	public int insert(Pinterest v) {
		return Dao.insert(v);
	}

	@Override
	public Pinterest pick(Pinterest v) {
		return Dao.pick(v);
	}

	@Override
	public int delete(Pinterest v) {

		return Dao.delete(v);
	}

}
