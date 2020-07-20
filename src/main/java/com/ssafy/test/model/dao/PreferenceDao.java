package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Preference;


public interface PreferenceDao {

	public List<Preference> selectAll();

	public Preference select(int pno);

	public int insert(Preference p);

	public int delete(int pno);

	public int update(Preference p);
	
}
