package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Pmember;

public interface PmemberService {
	
	public List<Pmember> selectAll();

	public Pmember select(Pmember pm);

	public int insert(Pmember pm);

	public int delete(Pmember pm);

	public int update(Pmember pm);
	
	public List<Pmember> selectByUserId(String id);
}
