package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Chat;

@Repository
public class ChatDaoImpl implements ChatDao{

	private static final String ns = "com.ssafy.test.Chat.";

	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public int delete(int k) {
		// TODO Auto-generated method stub
		return template.delete(ns + "delete", k);
	}

	@Override
	public List<Chat> selectAll(int k) {
		return template.selectList(ns + "selectAll", k);
	}

	@Override
	public int insert(Chat v) {
		return template.insert(ns + "insert", v);
	}
}
