package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Board;
import com.ssafy.test.model.dto.Comments;

public interface BoardService {

	public List<Board> selectAll();

	public Board select(int k);

	public int insert(Board v);

	public int delete(int k);

	public int update(Board v);
	
	public List<Comments> getComments(int k);

	public List<Board> search(String s);
}
