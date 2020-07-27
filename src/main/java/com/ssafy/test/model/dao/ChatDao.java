package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Chat;


public interface ChatDao {

	public List<Chat> selectAll(int k);

	public int insert(Chat v);
	
	public int delete(int k);
}
