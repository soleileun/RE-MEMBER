package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Pinterest;

public interface PinterestDAO {


	public List<Pinterest> selectAll();

	public List<Pinterest> select(int k);

	public int insert(Pinterest v);

	public int delete(Pinterest v);
	
	public Pinterest pick(Pinterest v);

}
