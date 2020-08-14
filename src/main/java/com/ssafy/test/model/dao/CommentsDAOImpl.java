package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Comments;

@Repository
public class CommentsDAOImpl implements CommentsDAO{
	
	
	

	private static final String ns = "com.ssafy.test.Comments.";

	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public List<Comments> selectAll() {
		// TODO Auto-generated method stub

		return template.selectList(ns + "selectAll");
	}

	@Override
	public Comments select(int k) {
		// TODO Auto-generated method stub
		return template.selectOne(ns + "select", k);
	}

	@Override
	public int insert(Comments v) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert", v);
	}

	@Override
	public int delete(int k) {
		// TODO Auto-generated method stub
		return template.delete(ns + "delete", k);
	}

	@Override
	public int update(Comments v) {
		// TODO Auto-generated method stub
		return template.update(ns + "update", v);
	}

	@Override
	public Comments selectedComments(int k) {
		return template.selectOne(ns + "selectedComments", k);
	}

	@Override
	public List<Comments> searchById(String s) {
		return template.selectList(ns + "searchById", s);
	}

	@Override
	public List<Comments> searchSelectedComments(String s) {
		return template.selectList(ns + "searchSelectedComments", s);
	}

	@Override
	public int pick(int k) {
		// TODO Auto-generated method stub
		return template.update(ns + "pick", k);
	}

}
