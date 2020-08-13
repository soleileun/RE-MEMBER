package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.ProjectDAO;
import com.ssafy.test.model.dto.Project;
import com.ssafy.test.model.dto.Projectcnt;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDAO pDao;

	@Override
	public List<Project> selectAll() {

		return pDao.selectAll();
	}

	@Override
	public Project select(int pid) {
		return pDao.select(pid);
	}

	@Override
	public int insert(Project p) {
		return pDao.insert(p);
	}

	@Override
	public int delete(int pid) {
		return pDao.delete(pid);
	}

	@Override
	public int update(Project p) {
		return pDao.update(p);
	}

	@Override
	public List<Projectcnt> searchByUserId(String userId) {
		return pDao.searchByUserId(userId);
	}

	@Override
	public Project searchByPJT(Project p) {
		// TODO Auto-generated method stub
		return pDao.searchByPJT(p);
	}

	@Override
	public Projectcnt searchByPID(int pid) {
		// TODO Auto-generated method stub
		return pDao.searchByPID(pid);
	}

}
