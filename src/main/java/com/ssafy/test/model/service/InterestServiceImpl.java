package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.InterestDAO;
import com.ssafy.test.model.dto.Interest;

@Service
public class InterestServiceImpl implements InterestService{

	@Autowired
	InterestDAO iDao;

	
	@Override
	public List<Interest> selectAll() {
		return iDao.selectAll();
	}

	@Override
	public Interest select(Interest i) {
		return iDao.select(i);
	}

	@Override
	public int insert(Interest i) {
		return iDao.insert(i);
	}

	@Override
	public int delete(Interest i) {
		return iDao.delete(i);
	}

	@Override
	public int update(Interest i) {
		return iDao.update(i);
	}

	@Override
	public List<Interest> selectById(String id) {
		return iDao.selectById(id);
	}

}
