package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Chatroom;

@Repository
public class ChatroomDaoImpl implements ChatroomDao{

	private static final String ns = "com.ssafy.test.Chatroom.";

	@Autowired
	SqlSessionTemplate template;
	@Override
	public List<Chatroom> selectAll(String k) {
		return template.selectList(ns + "selectAll", k);
	}

	@Override
	public int insert(Chatroom v) {
		return template.insert(ns + "insert", v);
	}

	@Override
	public int delete(Chatroom v) {
		return template.delete(ns + "delete", v);
	}

	@Override
	public int update(Chatroom v) {
		return template.update(ns + "update", v);
	}

	@Override
	public Chatroom select(Chatroom v) {
		return template.selectOne(ns + "select", v);
	}

	@Override
	public List<Chatroom> selectMember(String k) {
		return template.selectList(ns + "selectMember", k);
	}

}
