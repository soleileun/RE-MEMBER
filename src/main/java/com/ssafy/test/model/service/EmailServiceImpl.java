package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.EmailDAO;
import com.ssafy.test.model.dto.Email;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	EmailDAO eDao;
	@Override
	public int insert(Email email) {
		// TODO Auto-generated method stub
		return eDao.insert(email);
	}

	@Override
	public int delete(String userEmail) {
		// TODO Auto-generated method stub
		return eDao.delete(userEmail);
	}

	@Override
	public int update(Email email) {
		// TODO Auto-generated method stub
		return eDao.update(email);
	}

	@Override
	public Email selectByKey(String validKey) {
		// TODO Auto-generated method stub
		return eDao.selectByKey(validKey);
	}

	@Override
	public Email selectById(String id) {
		// TODO Auto-generated method stub
		return eDao.selectById(id);
	}

	@Override
	public List<Email> selectAll() {
		// TODO Auto-generated method stub
		return eDao.selectAll();
	}

}
