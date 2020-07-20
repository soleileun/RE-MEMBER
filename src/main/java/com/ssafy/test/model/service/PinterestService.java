package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Pinterest;

public interface PinterestService {

	public List<Pinterest> selectAll(); // 전체 

	public List<Pinterest> select(int k); // 특정 project의 모든 관심사를 출력함

	public Pinterest pick(Pinterest v); // 특정 project의 특정 interest를 추출함.
	
	public int insert(Pinterest v);

	public int delete(Pinterest v); // 특정 project의 특정 interest를 삭제함

}
