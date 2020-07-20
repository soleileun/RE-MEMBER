package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Project_member;

@Repository
public class Project_memberDAOImpl implements Project_memberDAO{
	
	private static final String ns = "com.ssafy.test.Project_member.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Project_member> selectAll() {
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Project_member select(Project_member pm) {
		return template.selectOne(ns + "select", pm);
	}

	@Override
	public int insert(Project_member pm) {
		return template.insert(ns + "insert", pm);
	}

	@Override
	public int delete(Project_member pm) {
		return template.delete(ns + "delete", pm);
	}

	@Override
	public int update(Project_member pm) {
		return template.update(ns + "update", pm);
	}
}
