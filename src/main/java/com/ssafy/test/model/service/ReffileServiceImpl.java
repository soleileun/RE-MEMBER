package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.ReffileDAO;
import com.ssafy.test.model.dto.Reffile;

@Service
public class ReffileServiceImpl implements ReffileService{


	@Autowired
	ReffileDAO Dao;

	@Override
	public List<Reffile> selectAll() {

		return Dao.selectAll();
	}

	@Override
	public Reffile select(int k) {
		return Dao.select(k);
	}

	@Override
	public int insert(Reffile v) {
		return Dao.insert(v);
	}

	@Override
	public int delete(int k) {
		return Dao.delete(k);
	}

	@Override
	public int update(Reffile v) {
		return Dao.update(v);
	}
}
