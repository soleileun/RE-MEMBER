package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Pmember;

@Repository
public class PmemberDAOImpl implements PmemberDAO {

	private static final String ns = "com.ssafy.test.pmember.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Pmember> selectAll() {
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Pmember select(Pmember pm) {
		return template.selectOne(ns + "select", pm);
	}

	@Override
	public int insert(Pmember pm) {
		return template.insert(ns + "insert", pm);
	}

	@Override
	public int delete(Pmember pm) {
		return template.delete(ns + "delete", pm);
	}

	@Override
	public int update(Pmember pm) {
		return template.update(ns + "update", pm);
	}

	@Override
	public List<Pmember> selectByUserId(String id) {

		return template.selectList(ns + "selectByUserId", id);
	}

	@Override
	public int selectCntByPid(int pid) {
		return template.selectOne(ns + "selectCntByPid", pid);
	}

	@Override
	public List<Pmember> selectByPid(int pid) {
		// TODO Auto-generated method stub
		return template.selectList(ns + "selectByPid", pid);
	}
}
