package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Reffile;

public interface ReffileDAO {

	public List<Reffile> selectAll();

	public Reffile select(int k);

	public int insert(Reffile v);

	public int delete(int k);

	public int update(Reffile v);
}
