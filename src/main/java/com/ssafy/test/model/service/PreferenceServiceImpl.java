package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.PreferenceDao;
import com.ssafy.test.model.dto.Preference;


@Service
public class PreferenceServiceImpl implements PreferenceService{

	@Autowired
	PreferenceDao pDao;
	@Override
	public List<Preference> selectAll() {
		// TODO Auto-generated method stub
		return pDao.selectAll();
	}

	@Override
	public Preference select(int pno) {
		// TODO Auto-generated method stub
		return pDao.select(pno);
	}

	@Override
	public int insert(Preference p) {
		// TODO Auto-generated method stub
		return pDao.insert(p);
	}

	@Override
	public int delete(int pno) {
		// TODO Auto-generated method stub
		return pDao.delete(pno);
	}

	@Override
	public int update(Preference p) {
		// TODO Auto-generated method stub
		return pDao.update(p);
	}

}
