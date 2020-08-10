package com.ssafy.test.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.ChatroomDao;
import com.ssafy.test.model.dto.Chatroom;
import com.ssafy.test.model.dto.ChatroomChat;

@Service
public class ChatroomServiceImpl implements ChatroomService{

	@Autowired
	ChatroomDao Dao;
	
	@Override
	public List<Chatroom> selectAll(String k) {
		// TODO Auto-generated method stub
		return Dao.selectAll(k);
	}

	@Override
	public Chatroom select(Chatroom v) {
		// TODO Auto-generated method stub
		return Dao.select(v);
	}

	@Override
	public int insert(Chatroom v) {
		// TODO Auto-generated method stub
		return Dao.insert(v);
	}

	@Override
	public int delete(Chatroom v) {
		// TODO Auto-generated method stub
		return Dao.delete(v);
	}

	@Override
	public int update(Chatroom v) {
		// TODO Auto-generated method stub
		return Dao.update(v);
	}

	@Override
	public List<Chatroom> selectMember(String k) {
		// TODO Auto-generated method stub
		return Dao.selectMember(k);
	}

	@Override
	public List<ChatroomChat> selectDetailAll(String k) {
		return Dao.selectDetailAll(k);
	}

	@Override
	public String selectOneToOne(Map<String,String> k) {
		return Dao.selectOneToOne(k);
	}

}
