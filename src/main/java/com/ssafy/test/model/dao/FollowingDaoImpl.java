package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Following;


@Repository

public class FollowingDaoImpl implements FollowingDao {
	private static final String ns = "com.ssafy.test.Following.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Following> selectAll() {
		// TODO Auto-generated method stub
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Following select(Following f) {
		// TODO Auto-generated method stub
		return template.selectOne(ns + "select", f);
	}

	@Override
	public int insert(Following f) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert", f);
	}

	@Override
	public int delete(Following f) {
		return template.delete(ns + "delete", f);
	}

	@Override
	public int update(Following f) {
		// TODO Auto-generated method stub
		return template.update(ns + "update", f);
	}
	

	
}
