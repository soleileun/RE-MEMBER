package com.ssafy.test.model.dto;

import java.util.Date;
import java.util.List;

public class RecruitPjtPinterest {
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
	private String interest;
	private int cnt;
	private int pjtMemberCnt;
	private String pjtContent;
	private String location;
	private String pjtState;
	private String rstate;
	private List<Inter> interests;
	
	
	public RecruitPjtPinterest(int rnum, int pid, String title, String contents, Date endDate, Date makeDay,
			Date changeDay, String makeId, String changeId, String pjtName, String interest, int cnt, int pjtMemberCnt,
			String pjtContent, String location, String pjtState, String rstate, List<Inter> interests) {
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
		this.interest = interest;
		this.cnt = cnt;
		this.pjtMemberCnt = pjtMemberCnt;
		this.pjtContent = pjtContent;
		this.location = location;
		this.pjtState = pjtState;
		this.rstate = rstate;
		this.interests = interests;
	}
	public String getRstate() {
		return rstate;
	}
	public void setRstate(String rstate) {
		this.rstate = rstate;
	}
	public RecruitPjtPinterest(int rnum, int pid, String title, String contents, Date endDate, Date makeDay,
			Date changeDay, String makeId, String changeId, String pjtName, String interest, int cnt, int pjtMemberCnt,
			String pjtContent, String location, String pjtState, List<Inter> interests) {
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
		this.interest = interest;
		this.cnt = cnt;
		this.pjtMemberCnt = pjtMemberCnt;
		this.pjtContent = pjtContent;
		this.location = location;
		this.pjtState = pjtState;
		this.interests = interests;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPjtState() {
		return pjtState;
	}
	public void setPjtState(String pjtState) {
		this.pjtState = pjtState;
	}
	public List<Inter> getInterests() {
		return interests;
	}
	public void setInterests(List<Inter> interests) {
		this.interests = interests;
	}
	public RecruitPjtPinterest(int rnum, int pid, String title, String contents, Date endDate, Date makeDay,
			Date changeDay, String makeId, String changeId, String pjtName, String interest, int cnt, int pjtMemberCnt,
			String pjtContent) {
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
		this.interest = interest;
		this.cnt = cnt;
		this.pjtMemberCnt = pjtMemberCnt;
		this.pjtContent = pjtContent;
	}
	public String getPjtContent() {
		return pjtContent;
	}
	public void setPjtContent(String pjtContent) {
		this.pjtContent = pjtContent;
	}
	public RecruitPjtPinterest(int rnum, int pid, String title, String contents, Date endDate, Date makeDay,
			Date changeDay, String makeId, String changeId, String pjtName, String interest, int cnt,
			int pjtMemberCnt) {
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
		this.interest = interest;
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
	@Override
	public String toString() {
		return "RecruitPjtPinterest [rnum=" + rnum + ", pid=" + pid + ", title=" + title + ", contents=" + contents
				+ ", endDate=" + endDate + ", makeDay=" + makeDay + ", changeDay=" + changeDay + ", makeId=" + makeId
				+ ", changeId=" + changeId + ", pjtName=" + pjtName + ", interest=" + interest + "]";
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
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public RecruitPjtPinterest(int rnum, int pid, String title, String contents, Date endDate, Date makeDay,
			Date changeDay, String makeId, String changeId, String pjtName, String interest) {
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
		this.interest = interest;
	}
	public RecruitPjtPinterest() {
		super();
	}
	
	
}
