package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Pmember;


public interface PmemberDAO {
	
	public List<Pmember> selectAll();

	public Pmember select(Pmember pm);

	public int insert(Pmember pm);

	public int delete(Pmember pm);

	public int update(Pmember pm);
	
	public List<Pmember> selectByUserId(String id);
	
	public List<Pmember> selectByPid(int pid);
	
	public int selectCntByPid(int pid);
}
