package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Comments;

public interface CommentsService {

	public List<Comments> selectAll();

	public Comments select(int k);

	public int insert(Comments v);

	public int delete(int k);

	public int update(Comments v);
}
