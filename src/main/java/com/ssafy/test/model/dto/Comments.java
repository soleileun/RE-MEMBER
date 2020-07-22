package com.ssafy.test.model.dto;

import java.util.Date;

public class Comments {

	private int cno; // //update no
	private String cwriter; // update no
	private String ccontent;
	private int bno; // update no
	private Boolean isSelected; // update no pick이라고 따로 만들었음.

	private Date makeDay; // update no
	private Date changeDay;
	private String makeId; // update no
	private String changeId;

	public Comments() {
		super();
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

	public Date getMakeDay() {
		return makeDay;
	}

	public void setMakeDay(Date makeDay) {
		this.makeDay = makeDay;
	}

	public Date getChangeDay() {
		return changeDay;
	}

	public void setChangeDay(Date changeDay) {
		this.changeDay = changeDay;
	}

	public String getMakeId() {
		return makeId;
	}

	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}

	public String getChangeId() {
		return changeId;
	}

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	public Comments(int cno, String cwriter, String ccontent, int bno, Boolean isSelected, Date makeDay, Date changeDay,
			String makeId, String changeId) {
		super();
		this.cno = cno;
		this.cwriter = cwriter;
		this.ccontent = ccontent;
		this.bno = bno;
		this.isSelected = isSelected;
		this.makeDay = makeDay;
		this.changeDay = changeDay;
		this.makeId = makeId;
		this.changeId = changeId;
	}

}
