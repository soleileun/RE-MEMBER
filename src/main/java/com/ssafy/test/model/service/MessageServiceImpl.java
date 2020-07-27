package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.MessageDao;
import com.ssafy.test.model.dto.Message;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageDao mDao;
	
	@Override
	public List<Message> selectAll() {
		// TODO Auto-generated method stub
		return mDao.selectAll();
	}

	@Override
	public Message select(int mnum) {
		// TODO Auto-generated method stub
		return mDao.select(mnum);
	}

	@Override
	public int insert(Message msg) {
		// TODO Auto-generated method stub
		return mDao.insert(msg);
	}

	@Override
	public int delete(int mnum) {
		// TODO Auto-generated method stub
		return mDao.delete(mnum);
	}

	@Override
	public int update(Message msg) {
		// TODO Auto-generated method stub
		return mDao.update(msg);
	}

	@Override
	public List<Message> selectById(String id) {
		// TODO Auto-generated method stub
		return mDao.selectById(id);
	}

	@Override
	public List<Message> getContacts(String id, String other) {
		// TODO Auto-generated method stub
		return mDao.getContacts(id, other);
	}

}
