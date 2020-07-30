package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.ChatDao;
import com.ssafy.test.model.dto.Chat;

@Service
public class ChatServiceImpl implements ChatService{


	@Autowired
	ChatDao Dao;
	
	@Override
	public List<Chat> selectAll(String k) {
		// TODO Auto-generated method stub
		return Dao.selectAll(k);
	}

	@Override
	public int insert(Chat v) {
		// TODO Auto-generated method stub
		return Dao.insert(v);
	}

	@Override
	public int delete(String k) {
		// TODO Auto-generated method stub
		return Dao.delete(k);
	}

	@Override
	public int updateRead(Chat v) {
		// TODO Auto-generated method stub
		return Dao.updateRead(v);
	}


}
