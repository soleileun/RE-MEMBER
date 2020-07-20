package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Interest;

public interface InterestService {

	public List<Interest> selectAll();

	public Interest select(Interest i);

	public int insert(Interest i);

	public int delete(Interest i);

	public int update(Interest i);
}
