package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Interest;

@Repository
public class InterestDAOImpl implements InterestDAO {

	private static final String ns = "com.ssafy.test.Interest.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Interest> selectAll() {
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Interest select(Interest i) {
		return template.selectOne(ns + "select", i);
	}

	@Override
	public int insert(Interest i) {
		return template.insert(ns + "insert", i);
	}

	@Override
	public int delete(Interest i) {
		return template.delete(ns + "delete", i);
	}

	@Override
	public int update(Interest i) {
		return template.update(ns + "update", i);
	}

}
