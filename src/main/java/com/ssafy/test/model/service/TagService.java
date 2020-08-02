package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Tag;

public interface TagService {


	public List<Tag> selectAll();

	public int insert(String k);
	
	public int delete(String k);
	
}
