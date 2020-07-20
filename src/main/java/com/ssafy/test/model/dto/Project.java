package com.ssafy.test.model.dto;

import java.util.Date;

public class Project {

	private int pid;
	private String pjtname;
	private String pjtcontent;
	private String pjtstate;
	private int pjtmember_cnt;
	private Date makeday;
	private Date changeday;
	private String makeid;
	private String changeid;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPjtname() {
		return pjtname;
	}

	public void setPjtname(String pjtname) {
		this.pjtname = pjtname;
	}

	public String getPjtcontent() {
		return pjtcontent;
	}

	public void setPjtcontent(String pjtcontent) {
		this.pjtcontent = pjtcontent;
	}

	public String getPjtstate() {
		return pjtstate;
	}

	public void setPjtstate(String pjtstate) {
		this.pjtstate = pjtstate;
	}

	public int getPjtmember_cnt() {
		return pjtmember_cnt;
	}

	public void setPjtmember_cnt(int pjtmember_cnt) {
		this.pjtmember_cnt = pjtmember_cnt;
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

	public Project(int pid, String pjtname, String pjtcontent, String pjtstate, int pjtmember_cnt, Date makeday,
			Date changeday, String makeid, String changeid) {
		super();
		this.pid = pid;
		this.pjtname = pjtname;
		this.pjtcontent = pjtcontent;
		this.pjtstate = pjtstate;
		this.pjtmember_cnt = pjtmember_cnt;
		this.makeday = makeday;
		this.changeday = changeday;
		this.makeid = makeid;
		this.changeid = changeid;
	}

	public Project() {
		super();
	}

}
