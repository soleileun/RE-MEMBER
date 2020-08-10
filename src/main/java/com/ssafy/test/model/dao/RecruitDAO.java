package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.dto.AddrAndTag;
import com.ssafy.test.model.dto.Recruit;
import com.ssafy.test.model.dto.SearchParameter;
import com.ssafy.test.model.dto.TagList;

public interface RecruitDAO {
	public List<Recruit> selectAll();

	public Recruit select(int rnum);

	public int insert(Recruit r);

	public int delete(int rnum);

	public int update(Recruit r);
	
	public List<Recruit> selectByAddr(Addr a);
	
	public List<Recruit> selectSame(TagList tl);
	
	public List<Recruit> selectAddrAndTag(AddrAndTag aat);
	
	public List<Recruit> searchAll(SearchParameter sp);
}
