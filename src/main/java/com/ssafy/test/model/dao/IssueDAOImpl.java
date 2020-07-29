package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Issue;

@Repository
public class IssueDAOImpl implements IssueDAO{

	private static final String ns = "com.ssafy.test.Issue.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Issue> selectAll(int k) {
		return template.selectList(ns + "selectAll", k);
	}

	@Override
	public Issue select(int k) {
		return template.selectOne(ns + "select", k);
	}

	@Override
	public int insert(Issue v) {
		return template.insert(ns + "insert", v);
	}

	@Override
	public int delete(int k) {
		return template.delete(ns + "delete", k);
	}

	@Override
	public int update(Issue v) {
		// TODO Auto-generated method stub
		return template.update(ns + "update", v);
	}

	@Override
	public int updateState(Issue v) {
		// TODO Auto-generated method stub
		return template.update(ns + "updateState", v);
	}

	@Override
	public int updateResponse(Issue v) {
		// TODO Auto-generated method stub
		return template.update(ns + "updateResponse", v);
	}

	@Override
	public int updatePriority(Issue v) {
		// TODO Auto-generated method stub
		return template.update(ns + "updatePriority", v);
	}

	@Override
	public List<Issue> selectStateAll(Issue v) {
		return template.selectList(ns + "selectStateAll", v);
	}

	@Override
	public int deleteByProject(int k) {
		return template.delete(ns + "deleteByProject", k);
	}
}
