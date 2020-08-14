package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.BoardDAO;
import com.ssafy.test.model.dto.Board;
import com.ssafy.test.model.dto.Comments;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDAO Dao;

	@Override
	public List<Board> selectAll() {

		return Dao.selectAll();
	}

	@Override
	public Board select(int k) {
		return Dao.select(k);
	}

	@Override
	public int insert(Board v) {
		return Dao.insert(v);
	}

	@Override
	public int delete(int k) {
		return Dao.delete(k);
	}

	@Override
	public int update(Board v) {
		return Dao.update(v);
	}

	@Override
	public List<Comments> getComments(int k) {
		
		return Dao.getComments(k);
	}

	@Override
	public List<Board> search(String s) {

		return Dao.search(s);
	}

	@Override
	public List<Board> searchById(String s) {
		return Dao.searchById(s);
	}

	@Override
	public List<Board> searchByContents(String s) {
		return Dao.searchByContents(s);
	}

	@Override
	public List<Board> searchByTitle(String s) {
		return Dao.searchByTitle(s);
	}

	@Override
	public List<Board> searchByTitleAndContents(String s) {
		return Dao.searchByTitleAndContents(s);
	}

	
	//type search
	@Override
	public List<Board> typesearchById(Board v) {
		return Dao.typesearchById(v);
	}

	@Override
	public List<Board> typesearchByContents(Board v) {
		return Dao.typesearchByContents(v);
	}

	@Override
	public List<Board> typesearchByTitle(Board v) {
		return Dao.typesearchByTitle(v);
	}

	@Override
	public List<Board> typesearchByTitleAndContents(Board v) {
		return Dao.typesearchByTitleAndContents(v);
	}

	@Override
	public int deletecomments(int k) {
		return Dao.deletecomments(k);
	}

	@Override
	public int read(int k) {
		// TODO Auto-generated method stub
		return Dao.read(k);
	}

	@Override
	public String isSelect(int k) {
		// TODO Auto-generated method stub
		return Dao.isSelect(k);
	}

}
