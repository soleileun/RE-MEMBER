package com.ssafy.test.model.dao;

import java.util.List;

import com.ssafy.test.model.dto.TagList;
import com.ssafy.test.model.dto.TagResult;
import com.ssafy.test.model.dto.Usertag;

public interface UsertagDAO {


	public List<Usertag> selectAll(String k);
	
	public Usertag select(Usertag v);

	public int insert(Usertag v);
	
	public int delete(Usertag v);
	
	public List<TagResult> selectSame(TagList v);
	
	public List<TagResult> selectSimilar(TagList v);
}
