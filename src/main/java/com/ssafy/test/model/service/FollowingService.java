package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Following;


public interface FollowingService {

	public List<Following> selectAll();

	public Following select(Following f);

	public int insert(Following f);

	public int delete(Following f);

	public int update(Following f);
	
}
