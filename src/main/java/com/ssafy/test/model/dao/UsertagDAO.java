package com.ssafy.test.model.dao;

import java.util.List;


import com.ssafy.test.model.dto.Usertag;

public interface UsertagDAO {


	public List<Usertag> selectAll(String k);
	
	public Usertag select(Usertag v);

	public int insert(Usertag v);
	
	public int delete(Usertag v);
	
}
