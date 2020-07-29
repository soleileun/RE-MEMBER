package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Issue;

public interface IssueService {

	public List<Issue> selectAll(int k);

	public List<Issue> selectStateAll(Issue v);

	public Issue select(int k);

	public int insert(Issue v);

	public int delete(int k);

	public int deleteByProject(int k);
	
	public int update(Issue v); // 이슈 전체를 업데이트함

	public int updateState(Issue v); // 진행중인지 끝났는지 이런거 업데이트함.
	
	public int updateResponse(Issue v); // 담당자를 업데이트함.
	
	public int updatePriority(Issue v); // 해당 이슈의 중요도를 업데이트함.
}
