package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.IssueDAO;
import com.ssafy.test.model.dto.Issue;

@Service
public class IssueServiceImpl implements IssueService{

	@Autowired
	IssueDAO Dao;

	@Override
	public List<Issue> selectAll(int k) {
		return Dao.selectAll(k);
	}

	@Override
	public Issue select(int k) {
		// TODO Auto-generated method stub
		return Dao.select(k);
	}

	@Override
	public int insert(Issue v) {
		// TODO Auto-generated method stub
		return Dao.insert(v);
	}

	@Override
	public int delete(int k) {
		// TODO Auto-generated method stub
		return Dao.delete(k);
	}

	@Override
	public int update(Issue v) {
		// TODO Auto-generated method stub
		return Dao.update(v);
	}

	@Override
	public int updateState(Issue v) {
		// TODO Auto-generated method stub
		return Dao.updateState(v);
	}

	@Override
	public int updateResponse(Issue v) {
		// TODO Auto-generated method stub
		return Dao.updateResponse(v);
	}

	@Override
	public int updatePriority(Issue v) {
		// TODO Auto-generated method stub
		return Dao.updatePriority(v);
	}

	@Override
	public List<Issue> selectStateAll(Issue v) {
		// TODO Auto-generated method stub
		return Dao.selectStateAll(v);
	}

	@Override
	public int deleteByProject(int k) {
		// TODO Auto-generated method stub
		return Dao.deleteByProject(k);
	}

	@Override
	public int updateByState(Issue v) {
		return Dao.updateByState(v);
	}
}
