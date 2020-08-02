package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Tag;

public interface TagDAO {

	public List<Tag> selectAll();

	public int insert(String k);
	
	public int delete(String k);
}
