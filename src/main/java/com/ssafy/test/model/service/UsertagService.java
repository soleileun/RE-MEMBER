package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.AddrAndTag;
import com.ssafy.test.model.dto.Interest;
import com.ssafy.test.model.dto.TagList;
import com.ssafy.test.model.dto.UserInfo;

public interface UsertagService {


	public List<Interest> selectAll(String k);
	
	public Interest select(Interest v);

	public int insert(Interest v);
	
	public int delete(Interest v);

	public List<UserInfo> selectSame(TagList v);
	
	public List<UserInfo> selectSimilar(TagList v);

	public List<UserInfo> selectAddrAndTag(AddrAndTag v);
}
