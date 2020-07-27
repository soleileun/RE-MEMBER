package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Chatroom;

public interface ChatroomDao {

	public List<Chatroom> selectAll(String k);
	
	public List<Chatroom> selectMember(String k);

	public Chatroom select(Chatroom v);
	
	public int insert(Chatroom v);

	public int delete(Chatroom v);

	public int update(Chatroom v);
}
