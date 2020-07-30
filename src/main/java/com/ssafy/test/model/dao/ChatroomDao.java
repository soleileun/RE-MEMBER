package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Chatroom;
import com.ssafy.test.model.dto.ChatroomChat;

public interface ChatroomDao {

	public List<Chatroom> selectAll(String k);

	public List<ChatroomChat> selectDetailAll(String k);	
	
	public List<Chatroom> selectMember(String k);

	public Chatroom select(Chatroom v);
	
	public int insert(Chatroom v);

	public int delete(Chatroom v);

	public int update(Chatroom v);
}
