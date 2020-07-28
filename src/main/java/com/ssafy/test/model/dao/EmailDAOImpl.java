package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Email;

@Repository
public class EmailDAOImpl implements EmailDAO{
	private static final String ns = "com.ssafy.test.Email.";

	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public int insert(Email email) {
		// TODO Auto-generated method stub
		return template.insert(ns+"insert", email);
	}

	@Override
	public int delete(String validKey) {
		// TODO Auto-generated method stub
		return template.delete(ns+"delete",validKey);
	}

	@Override
	public int update(Email email) {
		// TODO Auto-generated method stub
		return template.update(ns+"update",email);
	}

	@Override
	public Email selectByKey(String validKey) {
		// TODO Auto-generated method stub
		return template.selectOne(ns+"selectByKey" , validKey);
	}

	@Override
	public Email selectById(String id) {
		// TODO Auto-generated method stub
		return template.selectOne(ns+"selectById" , id);
	}

	@Override
	public List<Email> selectAll() {
		// TODO Auto-generated method stub
		return template.selectList(ns+"selectAll");
	}


}
