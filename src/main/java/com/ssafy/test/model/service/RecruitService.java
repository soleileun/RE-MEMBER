package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Recruit;

public interface RecruitService {

	public List<Recruit> selectAll();

	public Recruit select(int rnum);

	public int insert(Recruit r);

	public int delete(int rnum);

	public int update(Recruit r);
}
