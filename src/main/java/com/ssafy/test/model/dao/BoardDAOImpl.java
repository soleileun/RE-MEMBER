package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Board;
import com.ssafy.test.model.dto.Comments;

@Repository
public class BoardDAOImpl implements BoardDAO{

	

	private static final String ns = "com.ssafy.test.Board.";

	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public List<Board> selectAll() {
		// TODO Auto-generated method stub

		return template.selectList(ns + "selectAll");
	}

	@Override
	public Board select(int k) {
		// TODO Auto-generated method stub
		return template.selectOne(ns + "select", k);
	}

	@Override
	public int insert(Board v) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert", v);
	}

	@Override
	public int delete(int k) {
		// TODO Auto-generated method stub
		return template.delete(ns + "delete", k);
	}

	@Override
	public int update(Board v) {
		// TODO Auto-generated method stub
		return template.update(ns + "update", v);
	}

	@Override
	public List<Comments> getComments(int k) {
		return template.selectList(ns + "getComments", k);
	}

	@Override
	public List<Board> search(String s) {
		return template.selectList(ns + "getComments", s);
	}

}
