package com.ssafy.test.model.dto;

public class Addr {
	private int anum;
	private String sido;
	private String gugun;
	private String dong;
	private int paging;
	private int pcnt;

	public Addr(int anum, String sido, String gugun, String dong, int paging, int pcnt) {
		super();
		this.anum = anum;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.paging = paging;
		this.pcnt = pcnt;
	}
	public int getPaging() {
		return paging;
	}
	public void setPaging(int paging) {
		this.paging = paging;
	}
	public int getPcnt() {
		return pcnt;
	}
	public void setPcnt(int pcnt) {
		this.pcnt = pcnt;
	}
	public Addr() {
		super();
	}
	public Addr(int anum, String sido, String gugun, String dong) {
		super();
		this.anum = anum;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
	}
	public int getAnum() {
		return anum;
	}
	public void setAnum(int anum) {
		this.anum = anum;
	}
	public Addr(String sido, String gugun, String dong) {
		super();
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
}
