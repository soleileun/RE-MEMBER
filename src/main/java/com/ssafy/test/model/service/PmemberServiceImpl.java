package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.PmemberDAO;
import com.ssafy.test.model.dto.Pmember;

@Service
public class PmemberServiceImpl implements PmemberService {
	@Autowired
	PmemberDAO pmDao;

	@Override
	public List<Pmember> selectAll() {
		return pmDao.selectAll();
	}

	@Override
	public Pmember select(Pmember pm) {
		return pmDao.select(pm);
	}

	@Override
	public int insert(Pmember pm) {
		return pmDao.insert(pm);
	}

	@Override
	public int delete(Pmember pm) {
		return pmDao.delete(pm);
	}

	@Override
	public int update(Pmember pm) {
		return pmDao.update(pm);
	}

}
