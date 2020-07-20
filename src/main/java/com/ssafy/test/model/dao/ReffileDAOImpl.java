package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Reffile;

@Repository
public class ReffileDAOImpl implements ReffileDAO{

	
	private static final String ns = "com.ssafy.test.Reffile.";

	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public List<Reffile> selectAll() {
		// TODO Auto-generated method stub

		return template.selectList(ns + "selectAll");
	}

	@Override
	public Reffile select(int k) {
		// TODO Auto-generated method stub
		return template.selectOne(ns + "select", k);
	}

	@Override
	public int insert(Reffile v) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert", v);
	}

	@Override
	public int delete(int k) {
		// TODO Auto-generated method stub
		return template.delete(ns + "delete", k);
	}

	@Override
	public int update(Reffile v) {
		// TODO Auto-generated method stub
		return template.update(ns + "update", v);
	}
	
	
	
}
