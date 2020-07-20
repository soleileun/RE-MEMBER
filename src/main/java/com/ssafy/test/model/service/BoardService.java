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

	public List<Board> searchById(String s);	//	작성자 아이디로 검색
	
	public List<Board> searchByContents(String s);	// 게시글 내용으로 검색
	
	public List<Board> searchByTitle(String s);		//게시글 제목으로 검색
	
	public List<Board> searchByTitleAndContents(String s);	//게시글 내용과 제목으로 검색
}
