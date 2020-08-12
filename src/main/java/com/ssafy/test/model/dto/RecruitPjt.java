package com.ssafy.test.model.dto;

import java.util.Date;

public class RecruitPjt {

	private int rnum;
	private int pid;
	private String title;
	private String contents;
	private Date endDate;
	private Date makeDay;
	private Date changeDay;
	private String makeId;
	private String changeId;
	private String pjtName;
	private int cnt;
	private int pjtMemberCnt;
	private String rstate;
	public RecruitPjt(int rnum, int pid, String title, String contents, Date endDate, Date makeDay, Date changeDay,
			String makeId, String changeId, String pjtName, int cnt, int pjtMemberCnt, String rstate) {
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
		this.pjtName = pjtName;
		this.cnt = cnt;
		this.pjtMemberCnt = pjtMemberCnt;
		this.rstate = rstate;
	}
	public String getRstate() {
		return rstate;
	}
	public void setRstate(String rstate) {
		this.rstate = rstate;
	}
	public RecruitPjt(int rnum, int pid, String title, String contents, Date endDate, Date makeDay, Date changeDay,
			String makeId, String changeId, String pjtName, int cnt, int pjtMemberCnt) {
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
		this.pjtName = pjtName;
		this.cnt = cnt;
		this.pjtMemberCnt = pjtMemberCnt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getPjtMemberCnt() {
		return pjtMemberCnt;
	}
	public void setPjtMemberCnt(int pjtMemberCnt) {
		this.pjtMemberCnt = pjtMemberCnt;
	}
	public RecruitPjt() {
		super();
	}
	public RecruitPjt(int rnum, int pid, String title, String contents, Date endDate, Date makeDay, Date changeDay,
			String makeId, String changeId, String pjtName) {
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
		this.pjtName = pjtName;
	}
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
	public String getPjtName() {
		return pjtName;
	}
	public void setPjtName(String pjtName) {
		this.pjtName = pjtName;
	}
}
