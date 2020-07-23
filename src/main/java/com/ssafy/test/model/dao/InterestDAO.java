package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Interest;



public interface InterestDAO {
	public List<Interest> selectAll();

	public Interest select(Interest i);

	public int insert(Interest i);

	public int delete(Interest i);

	public int update(Interest i);
	
	public List<Interest> selectById(String id);
}
