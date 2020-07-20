package com.ssafy.test.model.dto;

import java.util.Date;

public class Recruit {

	private int rnum;
	private int pid;
	private String title;
	private String contents;
	private Date end_date;
	private Date makeday;
	private Date changeday;
	private String makeid;
	private String changeid;

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

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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


	public Recruit(int rnum, int pid, String title, String contents, Date end_date, Date makeday, Date changeday,
			String makeid, String changeid) {
		super();
		this.rnum = rnum;
		this.pid = pid;
		this.title = title;
		this.contents = contents;
		this.end_date = end_date;
		this.makeday = makeday;
		this.changeday = changeday;
		this.makeid = makeid;
		this.changeid = changeid;
	}

	public Recruit() {
	}
}
