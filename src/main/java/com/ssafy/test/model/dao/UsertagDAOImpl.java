package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Usertag;

@Repository
public class UsertagDAOImpl implements UsertagDAO{

	private static final String ns = "com.ssafy.test.Usertag.";

	@Autowired
	SqlSessionTemplate template;
	

	@Override
	public List<Usertag> selectAll(String k) {
		return template.selectList(ns + "selectAll",k);
	}

	@Override
	public Usertag select(Usertag v) {
		return template.selectOne(ns + "select", v);
	}

	@Override
	public int insert(Usertag v) {
		return template.insert(ns + "insert", v);
	}

	@Override
	public int delete(Usertag v) {
		return template.delete(ns + "delete", v);
	}

}
