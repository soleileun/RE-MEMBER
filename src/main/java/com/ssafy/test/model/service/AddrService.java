package com.ssafy.test.model.service;

import java.util.List;

import com.ssafy.test.model.dto.Addr;

public interface AddrService {

	public List<Addr> selectAll();

	public Addr select(Addr v);

	public Addr selectByAnum(int k);

	public List<String> getSido();

	public List<String> getGugun(Addr v);

	public List<String> getDong(Addr v);

	public int insert(Addr v);
	
	public int delete(Addr v);
	
	public int update(Addr v);
}
