package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Message;


public interface MessageService {
	public List<Message> selectAll();

	public Message select(int mnum);

	public int insert(Message msg);

	public int delete(int mnum);

	public int update(Message msg);
	
	public List<Message> selectById(String id);
}
