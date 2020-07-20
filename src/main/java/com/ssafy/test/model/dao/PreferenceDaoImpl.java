package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Preference;


@Repository

public class PreferenceDaoImpl implements PreferenceDao {

	private static final String ns = "com.ssafy.test.Preference.";
	
	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Preference> selectAll() {
		// TODO Auto-generated method stub
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Preference select(int pno) {
		// TODO Auto-generated method stub
		return template.selectOne(ns + "select", pno);
	}

	@Override
	public int insert(Preference p) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert", p);
	}

	@Override
	public int delete(int pno) {
		// TODO Auto-generated method stub
		return template.delete(ns + "delete", pno);
	}

	@Override
	public int update(Preference p) {
		// TODO Auto-generated method stub
		return template.update(ns + "update", p);
	}

}
