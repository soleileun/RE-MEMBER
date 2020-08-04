package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.dto.UserInfo;



@Repository

public class UserInfoDaoImpl implements UserInfoDao {

	private static String ns = "com.ssafy.test.UserInfo.";
	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public int insert(UserInfo info) {
		// TODO Auto-generated method stub
		return template.insert(ns + "insert",info);
	}

	@Override
	public int update(UserInfo info) {
		// TODO Auto-generated method stub
		return template.update(ns+"update",info);
	}

	@Override
	public int delete(String keyword) {
		// TODO Auto-generated method stub
		return template.delete(ns+"delete",keyword);
	}

	@Override
	public UserInfo select(String keyword) {
		// TODO Auto-generated method stub
		return template.selectOne(ns+"select",keyword);
	}

	@Override
	public List<UserInfo> selectAll() {
		// TODO Auto-generated method stub
		return template.selectList(ns+"selectAll");
	}

	@Override
	public UserInfo login(UserInfo info) {
		// TODO Auto-generated method stub
		return template.selectOne(ns+"login",info);
	}

	@Override
	public UserInfo signin(String id, String pw) {
		//System.out.println("설마 여기가문제인가");
		UserInfo ui = new UserInfo(id,pw);
		return template.selectOne(ns+"signin",ui);
	}

	@Override
	public int updateVal(String id) {
		// TODO Auto-generated method stub
		return template.update(ns+"updateVal",id);
	}

	@Override
	public List<UserInfo> getCurrList() {
		return template.selectList(ns + "getCurrList");
	}

	@Override
	public List<UserInfo> selectByAddr(Addr v) {
		// TODO Auto-generated method stub
		return template.selectList(ns + "selectByAddr",v);
	}

}
