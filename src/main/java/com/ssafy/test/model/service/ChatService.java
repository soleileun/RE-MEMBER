package com.ssafy.test.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.test.model.dto.Chat;

public interface ChatService {

	//public List<Chat> selectAll(String k);

	public List<Chat> selectAll(Map<String,String> k);

	public int insert(Chat v);
	
	public int delete(String k);
	
	public int updateRead(Chat v);
}
