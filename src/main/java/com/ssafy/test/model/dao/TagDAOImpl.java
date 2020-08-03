package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Tag;

@Repository
public class TagDAOImpl implements TagDAO{

	private static final String ns = "com.ssafy.test.Tag.";

	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public List<Tag> selectAll() {
		return template.selectList(ns + "selectAll");
	}

	@Override
	public int insert(Tag v) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert", v);
	}

	@Override
	public int delete(String k) {
		// TODO Auto-generated method stub
		return 0;
	}


}
