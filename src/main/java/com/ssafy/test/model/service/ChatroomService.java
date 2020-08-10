package com.ssafy.test.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.test.model.dto.Chatroom;
import com.ssafy.test.model.dto.ChatroomChat;

public interface ChatroomService {

	public List<Chatroom> selectAll(String k);

	public List<ChatroomChat> selectDetailAll(String k);	
	
	public List<Chatroom> selectMember(String k);

	public String selectOneToOne(Map<String,String> k); // 해당 유저끼리 단 둘이만 들어있는 방이 있는지 보는것

	public Chatroom select(Chatroom v);

	public int insert(Chatroom v);

	public int delete(Chatroom v);

	public int update(Chatroom v);
}
