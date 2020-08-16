package com.ssafy.test.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.dto.AddrAndTag;
import com.ssafy.test.model.dto.Recruit;
import com.ssafy.test.model.dto.RecruitPjt;
import com.ssafy.test.model.dto.RecruitPjtPinterest;
import com.ssafy.test.model.dto.SearchParameter;
import com.ssafy.test.model.dto.TagList;

@Repository
public class RecruitDAOImpl implements RecruitDAO {

	private static final String ns = "com.ssafy.test.Recruit.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<RecruitPjt> selectAll() {

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

	@Override
	public List<RecruitPjtPinterest> searchAll(SearchParameter sp) {
		String by = sp.getBy();		
		System.out.println("by is " + by);
		
		if (by == null) {
			AddrAndTag aat = new AddrAndTag();
			aat.setCnt(sp.getCnt());
			aat.setDong(sp.getDong());
			aat.setGugun(sp.getGugun());
			aat.setSido(sp.getSido());
			aat.setTag1(sp.getTag1());
			aat.setTag2(sp.getTag2());
			aat.setTag3(sp.getTag3());
			aat.setTag4(sp.getTag4());
			aat.setTag5(sp.getTag5());
			aat.setPaging(sp.getPaging());
			aat.setPcnt(sp.getCnt());
			return template.selectList(ns + "selectAddrAndTag", aat);
		} else if (by.equals("writer")) {
			if (sp.getCnt() == 0) {				
				return template.selectList(ns + "selectByAddrWriter", sp);
			} else {
				return template.selectList(ns + "selectAllByWriter", sp);
			}
			
		} else if (by.equals("title")) {
			if (sp.getCnt() == 0) {
				return template.selectList(ns + "selectByAddrTitle", sp);
			} else {
				return template.selectList(ns + "selectAllByTitle", sp);
			}			
		}

		return null;
	}

	@Override
	public List<RecruitPjtPinterest> selectAllRecruitPjtPinterest() {
		return template.selectList(ns + "selectAllRecruitPjtPinterest");
	}

}
