package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	private static final String ns = "com.ssafy.test.Project.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Project> selectAll() {

		return template.selectList(ns + "selectAll");
	}

	@Override
	public Project select(int pid) {

		return template.selectOne(ns + "select", pid);
	}

	@Override
	public int insert(Project p) {
		return template.insert(ns + "insert", p);
	}

	@Override
	public int delete(int pid) {

		return template.delete(ns + "delete", pid);
	}

	@Override
	public int update(Project p) {

		return template.update(ns + "update", p);
	}

}
