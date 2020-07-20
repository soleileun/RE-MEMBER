package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.RecruitDAO;
import com.ssafy.test.model.dto.Recruit;

@Service
public class RecruitServiceImpl implements RecruitService {

	@Autowired
	RecruitDAO rDao;

	@Override
	public List<Recruit> selectAll() {
		return rDao.selectAll();
	}

	@Override
	public Recruit select(int rnum) {
		return rDao.select(rnum);
	}

	@Override
	public int insert(Recruit r) {
		return rDao.insert(r);
	}

	@Override
	public int delete(int rnum) {
		return rDao.delete(rnum);
	}

	@Override
	public int update(Recruit r) {
		return rDao.update(r);
	}

}
