package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.dto.Pools;
import com.ssafy.test.model.dto.Project;
import com.ssafy.test.model.dto.SearchParameter;
import com.ssafy.test.model.dto.Two;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.dto.UsersInterest;



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

	@Override
	public List<Pools> searchAll(SearchParameter sp) {
		int cnt = sp.getCnt();
		if(cnt == 0) {
			// tag 없음
			return template.selectList(ns + "searchAddr", sp);
		}else {
			// tag 있는 경우
			return template.selectList(ns + "searchAll", sp);
		}
		
	}

	@Override
	public List<UserInfo> getRecommendedUser(String id) {
		// TODO Auto-generated method stub
		return template.selectList(ns +"getRecommendedUser",id);
	}

	@Override
	public List<Project> getRecommendedPJT(String id) {
		// TODO Auto-generated method stub
		return template.selectList(ns +"getRecommendedPJT",id);
	}

	@Override
	public List<UserInfo> getAddressList(int pid) {
		return template.selectList(ns + "getAddressList", pid);
	}

	@Override
	public int updateLastDate(UserInfo user) {
		// TODO Auto-generated method stub
		return template.update(ns+"updateDate",user);
	}

	@Override
	public List<Pools> getPools() { // Two<Integer,Integer> v
		return template.selectList(ns+"getPools");
	}

	@Override
	public UserInfo loginForKakao(String kakaoId) {
		// TODO Auto-generated method stub
		UserInfo user= new UserInfo();
		user.setKakaoId(kakaoId);
		return template.selectOne(ns+"loginForKakao",user);
	}

	@Override
	public Pools searchPoolById(String id) {
		// TODO Auto-generated method stub
		System.out.println("id확인 :" +id);
		return template.selectOne(ns+"searchPoolById",id);
	}

	@Override
	public List<UsersInterest> getUserByInterest(String userid) {
		// TODO Auto-generated method stub
		return template.selectList(ns+"getUserByInterest",userid);
	}

}
