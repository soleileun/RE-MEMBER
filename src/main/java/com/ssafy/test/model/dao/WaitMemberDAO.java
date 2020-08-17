package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.WaitMember;

public interface WaitMemberDAO {
	
	public int addWaitMember(WaitMember wm);
	public int deleteWM(int pid, String userId);
	public List<WaitMember> selectbyPid(int pid);
	public List<WaitMember> selectbyuserId(String userId);
	public WaitMember selectbyCode(String code);
	

}
