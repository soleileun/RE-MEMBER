package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.Project_memberDAO;
import com.ssafy.test.model.dto.Project_member;

@Service
public class Project_memberServiceImpl implements Project_memberService {
	@Autowired
	Project_memberDAO pmDao;

	@Override
	public List<Project_member> selectAll() {
		return pmDao.selectAll();
	}

	@Override
	public Project_member select(Project_member pm) {
		return pmDao.select(pm);
	}

	@Override
	public int insert(Project_member pm) {
		return pmDao.insert(pm);
	}

	@Override
	public int delete(Project_member pm) {
		return pmDao.delete(pm);
	}

	@Override
	public int update(Project_member pm) {
		return pmDao.update(pm);
	}

}
