package com.ssafy.test.model.dao;

import java.util.List;


import com.ssafy.test.model.dto.Chat;


public interface ChatDao {

	public List<Chat> selectAll(String k);

	public int insert(Chat v);
	
	public int delete(String k);
	
	public int updateRead(Chat v);
}
