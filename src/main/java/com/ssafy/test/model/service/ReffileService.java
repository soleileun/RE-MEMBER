package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Reffile;

public interface ReffileService {

	public List<Reffile> selectAll();

	public Reffile select(int k);

	public int insert(Reffile v);

	public int delete(int k);

	public int update(Reffile v);

}
