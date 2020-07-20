package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Board;
import com.ssafy.test.model.dto.Comments;

public interface BoardDAO {

	public List<Board> selectAll();

	public Board select(int k);

	public int insert(Board v);

	public int delete(int k);

	public int update(Board v);
	
	public List<Comments> getComments(int k); // 해당 글에 달린 커멘트들을 쫙 받아옴.
	
	public List<Board> search(String s);
	// 잘라내야함
}
