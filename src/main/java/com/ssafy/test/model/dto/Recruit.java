package com.ssafy.test.model.dto;

import java.util.Date;

public class Recruit {

	private int rnum;
	private int pid;
	private String title;
	private String contents;
	private Date endDate;
	private Date makeDay;
	private Date changeDay;
	private String makeId;
	private String changeId;

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Recruit(int rnum, int pid, String title, String contents, Date endDate, Date makeDay, Date changeDay,
			String makeId, String changeId) {
		super();
		this.rnum = rnum;
		this.pid = pid;
		this.title = title;
		this.contents = contents;
		this.endDate = endDate;
		this.makeDay = makeDay;
		this.changeDay = changeDay;
		this.makeId = makeId;
		this.changeId = changeId;
	}

	public Recruit() {
	}
}
