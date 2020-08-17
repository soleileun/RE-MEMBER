package com.ssafy.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.dao.WaitMemberDAO;
import com.ssafy.test.model.dto.WaitMember;

@Service
public class WaitMemberServiceImpl implements WaitMemberService {

	@Autowired
	WaitMemberDAO wDao;
	@Override
	public int addWaitMember(WaitMember wm) {
		// TODO Auto-generated method stub
		return wDao.addWaitMember(wm);
	}

	@Override
	public int deleteWM(int pid, String userId) {
		// TODO Auto-generated method stub
		return wDao.deleteWM(pid, userId);
	}

	@Override
	public List<WaitMember> selectbyPid(int pid) {
		// TODO Auto-generated method stub
		return wDao.selectbyPid(pid);
	}

	@Override
	public List<WaitMember> selectbyuserId(String userId) {
		// TODO Auto-generated method stub
		return wDao.selectbyuserId(userId);
	}

	@Override
	public WaitMember selectbyCode(String code) {
		// TODO Auto-generated method stub
		return wDao.selectbyCode(code);
	}

}
