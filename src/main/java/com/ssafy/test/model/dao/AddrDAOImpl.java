package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Addr;

@Repository
public class AddrDAOImpl implements AddrDAO{


	private static final String ns = "com.ssafy.test.Addr.";

	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public List<Addr> selectAll() {
		// TODO Auto-generated method stub
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Addr select(Addr v) {
		// TODO Auto-generated method stub
		return template.selectOne(ns + "select",v);
	}

	@Override
	public Addr selectByAnum(int k) {
		// TODO Auto-generated method stub
		return template.selectOne(ns + "selectByAnum",k);
	}

	@Override
	public List<String> getSido() {
		// TODO Auto-generated method stub
		return template.selectList(ns + "getSido");
	}

	@Override
	public List<String> getGugun(Addr v) {
		// TODO Auto-generated method stub
		return template.selectList(ns + "getGugun",v);
	}

	@Override
	public List<String> getDong(Addr v) {
		// TODO Auto-generated method stub
		return template.selectList(ns + "getDong",v);
	}

	@Override
	public int insert(Addr v) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert",v);
	}

	@Override
	public int delete(Addr v) {
		// TODO Auto-generated method stub
		return template.delete(ns + "delete",v);
	}

	@Override
	public int update(Addr v) {
		// TODO Auto-generated method stub
		return template.update(ns + "update",v);
	}

}
