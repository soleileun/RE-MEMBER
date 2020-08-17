package com.ssafy.test.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.WaitMember;

@Repository
public class WaitMemberDAOImpl implements WaitMemberDAO {

	private static String ns = "com.ssafy.test.WaitMember.";
	@Autowired
	SqlSession template;
	
	@Override
	public int addWaitMember(WaitMember wm) {
		// TODO Auto-generated method stub
		return template.insert(ns+"insert", wm);
	}

	@Override
	public int deleteWM(int pid, String userId) {
		// TODO Auto-generated method stub
		WaitMember wm = new WaitMember();
		wm.setPid(pid);wm.setUserId(userId);
		return template.delete(ns+"delete", wm);
	}

	@Override
	public List<WaitMember> selectbyPid(int pid) {
		// TODO Auto-generated method stub
		return template.selectList(ns+"selectbyPid",pid);
	}

	@Override
	public List<WaitMember> selectbyuserId(String userId) {
		// TODO Auto-generated method stub
		return template.selectList(ns+"selectbyuserId",userId);
	}

}
