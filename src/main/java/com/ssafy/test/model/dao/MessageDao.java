package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Message;



public interface MessageDao {

	public List<Message> selectAll();

	public Message select(int mnum);

	public int insert(Message msg);

	public int delete(int mnum);

	public int update(Message msg);
	
	public List<Message> selectById(String id);
}
