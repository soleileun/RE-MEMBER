package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Message;


@Repository

public class MessageDaoImpl implements MessageDao{

	private static final String ns = "com.ssafy.test.Message.";

	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public List<Message> selectAll() {
		// TODO Auto-generated method stub
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Message select(int mnum) {
		// TODO Auto-generated method stub
		return template.selectOne(ns + "select", mnum);
	}
	@Override
	public List<Message> selectById(String id) {
		Message msg = new Message();
		msg.setFromUser(id);
		msg.setToUser(id);
		return template.selectList(ns + "selectById", msg);
	}

	@Override
	public int insert(Message msg) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert", msg);
	}

	@Override
	public int delete(int mnum) {
		// TODO Auto-generated method stub

		return template.delete(ns + "delete", mnum);
	}

	@Override
	public int update(Message msg) {
		// TODO Auto-generated method stub
		return template.update(ns + "update", msg);
	}

	@Override
	public List<Message> getContacts(String id, String other) {
		Message msg = new Message();
		msg.setToUser(id);
		msg.setFromUser(other);
		return template.selectList(ns+"getcontacts", msg);
	}

}
