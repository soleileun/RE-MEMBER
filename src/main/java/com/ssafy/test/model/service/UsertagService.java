package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.AddrAndTag;
import com.ssafy.test.model.dto.TagList;
import com.ssafy.test.model.dto.TagResult;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.dto.Usertag;

public interface UsertagService {


	public List<Usertag> selectAll(String k);
	
	public Usertag select(Usertag v);

	public int insert(Usertag v);
	
	public int delete(Usertag v);

	public List<UserInfo> selectSame(TagList v);
	
	public List<UserInfo> selectSimilar(TagList v);

	public List<UserInfo> selectAddrAndTag(AddrAndTag v);
}
