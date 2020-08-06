package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.dto.AddrAndTag;
import com.ssafy.test.model.dto.Recruit;
import com.ssafy.test.model.dto.TagList;

@Repository
public class RecruitDAOImpl implements RecruitDAO {

	private static final String ns = "com.ssafy.test.Recruit.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Recruit> selectAll() {

		return template.selectList(ns + "selectAll");
	}

	@Override
	public Recruit select(int rnum) {
		return template.selectOne(ns + "select", rnum);
	}

	@Override
	public int insert(Recruit r) {
		return template.insert(ns + "insert", r);
	}

	@Override
	public int delete(int rnum) {
		return template.delete(ns + "delete", rnum);
	}

	@Override
	public int update(Recruit r) {
		return template.update(ns + "update", r);
	}

	@Override
	public List<Recruit> selectByAddr(Addr a) {		
		return template.selectList(ns + "selectByAddr", a);
	}

	@Override
	public List<Recruit> selectSame(TagList tl) {
		return template.selectList(ns + "selectSame", tl);
	}

	@Override
	public List<Recruit> selectAddrAndTag(AddrAndTag aat) {
		return template.selectList(ns + "selectAddrAndTag", aat);
	}

}
