package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Project;
import com.ssafy.test.model.dto.Projectcnt;

public interface ProjectService {

	public List<Project> selectAll();

	public Project select(int pid);

	public int insert(Project p);

	public int delete(int pid);

	public int update(Project p);
	
	public List<Projectcnt> searchByUserId(String userId);
	public Project searchByPJT(Project p);
}
