package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.AddrDAO;
import com.ssafy.test.model.dto.Addr;

@Service
public class AddrServiceImpl implements AddrService{

	@Autowired
	AddrDAO Dao;

	@Override
	public List<Addr> selectAll() {
		// TODO Auto-generated method stub
		return Dao.selectAll();
	}

	@Override
	public Addr select(Addr v) {
		// TODO Auto-generated method stub
		return Dao.select(v);
	}

	@Override
	public Addr selectByAnum(int k) {
		// TODO Auto-generated method stub
		return Dao.selectByAnum(k);
	}

	@Override
	public List<String> getSido() {
		// TODO Auto-generated method stub
		return Dao.getSido();
	}

	@Override
	public List<String> getGugun(Addr v) {
		// TODO Auto-generated method stub
		return Dao.getGugun(v);
	}

	@Override
	public List<String> getDong(Addr v) {
		// TODO Auto-generated method stub
		return Dao.getDong(v);
	}

	@Override
	public int insert(Addr v) {
		// TODO Auto-generated method stub
		return Dao.insert(v);
	}

	@Override
	public int delete(Addr v) {
		// TODO Auto-generated method stub
		return Dao.delete(v);
	}

	@Override
	public int update(Addr v) {
		// TODO Auto-generated method stub
		return Dao.update(v);
	}
	
}
