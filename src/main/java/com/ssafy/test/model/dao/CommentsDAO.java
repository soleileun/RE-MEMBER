package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Comments;

public interface CommentsDAO {

	public List<Comments> selectAll();

	public Comments select(int k);

	public int pick(int k);

	public int insert(Comments v);

	public int delete(int k);

	public int update(Comments v);
	
	public Comments selectedComments(int k); // 특정 게시글에 채택된 글 반환 k에 boardno를 넣음
	
	public List<Comments> searchById(String s);
	
	public List<Comments> searchSelectedComments(String s);
	
}
