package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Pinterest;

@Repository
public class PinterestDAOImpl implements PinterestDAO{

	
	
	private static final String ns = "com.ssafy.test.Pinterest.";

	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public List<Pinterest> selectAll() {
		// TODO Auto-generated method stub

		return template.selectList(ns + "selectAll");
	}

	@Override
	public List<Pinterest> select(int k) {
		// TODO Auto-generated method stub
		return template.selectList(ns + "select", k);
	}

	@Override
	public int insert(Pinterest v) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert", v);
	}

	@Override
	public int delete(Pinterest v) {
		// TODO Auto-generated method stub
		return template.delete(ns + "delete", v);
	}

	@Override
	public Pinterest pick(Pinterest v) {
		// TODO Auto-generated method stub
		return template.selectOne(ns + "pick", v);
	}

	
	
	
}
