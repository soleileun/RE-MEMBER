package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Email;

public interface EmailDAO {
	public List<Email> selectAll();
	public int insert(Email email);

	public int delete(String userEmail);

	public int update(Email email);
	
	public Email selectByKey(String validKey);
	public Email selectById(String id);

}
