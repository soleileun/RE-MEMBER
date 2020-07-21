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
	
	public List<Board> search(String s);	// 게시글 타입으로 무슨 게시글인지 조회
	
	public List<Board> searchById(String s);	//	작성자 아이디로 검색
	
	public List<Board> searchByContents(String s);	// 게시글 내용으로 검색
	
	public List<Board> searchByTitle(String s);		//게시글 제목으로 검색
	
	public List<Board> searchByTitleAndContents(String s);	//게시글 내용과 제목으로 검색
	
	// 카테고리하고 같이 검색하는 파트
	
	public List<Board> typesearchById(Board v);	//	작성자 아이디로 검색
	
	public List<Board> typesearchByContents(Board v);	// 게시글 내용으로 검색
	
	public List<Board> typesearchByTitle(Board v);		//게시글 제목으로 검색
	
	public List<Board> typesearchByTitleAndContents(Board v);	//게시글 내용과 제목으로 검색
	
	
	
	// 잘라내야함
}
