package com.ssafy.test.model.dto;

import java.util.Date;

public class Comments {

	private int cno; // //update no
	private String cwriter;	//update no
	private String ccontent;
	private int bno;	//update no
	private Boolean isSelected;	//update no pick이라고 따로 만들었음.
	
	private Date makeday;	//update no
	private Date changeday;
	private String makeid;	//update no
	private String changeid;
	
	
	
	
	public Comments() {
		super();
	}
	public Comments(int cno, String cwriter, String ccontent, int bno, Boolean isSelected, Date makeday, Date changeday,
			String makeid, String changeid) {
		super();
		this.cno = cno;
		this.cwriter = cwriter;
		this.ccontent = ccontent;
		this.bno = bno;
		this.isSelected = false;
		this.makeday = makeday;
		this.changeday = changeday;
		this.makeid = makeid;
		this.changeid = changeid;
	}
	@Override
	public String toString() {
		return "Comments [cno=" + cno + ", cwriter=" + cwriter + ", ccontent=" + ccontent + ", bno=" + bno
				+ ", isSelected=" + isSelected + ", makeday=" + makeday + ", changeday=" + changeday + ", makeid="
				+ makeid + ", changeid=" + changeid + "]";
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCwriter() {
		return cwriter;
	}
	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public Date getMakeday() {
		return makeday;
	}
	public void setMakeday(Date makeday) {
		this.makeday = makeday;
	}
	public Date getChangeday() {
		return changeday;
	}
	public void setChangeday(Date changeday) {
		this.changeday = changeday;
	}
	public String getMakeid() {
		return makeid;
	}
	public void setMakeid(String makeid) {
		this.makeid = makeid;
	}
	public String getChangeid() {
		return changeid;
	}
	public void setChangeid(String changeid) {
		this.changeid = changeid;
	}
}
