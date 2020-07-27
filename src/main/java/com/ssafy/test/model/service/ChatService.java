package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Chat;

public interface ChatService {

	public List<Chat> selectAll(int k);

	public int insert(Chat v);
	
	public int delete(int k);
}
