package com.ssafy.test.model.dto;

public class SearchParameter {
	private String sido;
	private String gugun;
	private String dong;
	private String tag1;
	private String tag2;
	private String tag3;
	private String tag4;
	private String tag5;
	private int cnt;
	private String by;
	private String keyword;
	private int paging;
	private int pcnt;
	
	

	public SearchParameter(String sido, String gugun, String dong, String tag1, String tag2, String tag3, String tag4,
			String tag5, int cnt, String by, String keyword, int paging, int pcnt) {
		super();
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
		this.tag4 = tag4;
		this.tag5 = tag5;
		this.cnt = cnt;
		this.by = by;
		this.keyword = keyword;
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

	@Override
	public String toString() {
		return "SearchParameter [sido=" + sido + ", gugun=" + gugun + ", dong=" + dong + ", tag1=" + tag1 + ", tag2="
				+ tag2 + ", tag3=" + tag3 + ", tag4=" + tag4 + ", tag5=" + tag5 + ", cnt=" + cnt + ", by=" + by
				+ ", keyword=" + keyword + "]";
	}

	public SearchParameter() {
		super();
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

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public String getTag3() {
		return tag3;
	}

	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}

	public String getTag4() {
		return tag4;
	}

	public void setTag4(String tag4) {
		this.tag4 = tag4;
	}

	public String getTag5() {
		return tag5;
	}

	public void setTag5(String tag5) {
		this.tag5 = tag5;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public SearchParameter(String sido, String gugun, String dong, String tag1, String tag2, String tag3, String tag4,
			String tag5, int cnt, String by, String keyword) {
		super();
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
		this.tag4 = tag4;
		this.tag5 = tag5;
		this.cnt = cnt;
		this.by = by;
		this.keyword = keyword;
	}

}
