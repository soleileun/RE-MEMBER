package com.ssafy.test.model.dto;

import java.util.Date;

public class Issue {
	private int issueid;
	private int pid; // 프로젝트 id
	private String uid; // 어떤 유저가 만들었는지
	private String response;  // 어떤 사람이 담당하는지
	private String issuetype; // 버그인지 일반 이슈인지
	private String issuestate; // 진행중인지 대기인지 끝났는지
	private String issuetitle; // 이슈 제목
	private String issuecontent; // 이슈 내용
	private int prework; // 선행되는 작업 정보 issueid
	private String priority; // 중요도  // 중요 // 일반 // 안 중요
	private Date makeDay;// 언제 작성된 이슈인지
	private Date startDay; // 언제 부터 시작했는지
	private Date endDay; // 언제 끝났는지
	private Date changeDay; // 언제 바꿨는지
	public Issue() {
		super();
	}
	
	@Override
	public String toString() {
		return "Issue [issueid=" + issueid + ", pid=" + pid + ", uid=" + uid + ", response=" + response + ", issuetype="
				+ issuetype + ", issuestate=" + issuestate + ", issuetitle=" + issuetitle + ", issuecontent="
				+ issuecontent + ", prework=" + prework + ", priority=" + priority + ", makeDay=" + makeDay
				+ ", startDay=" + startDay + ", endDay=" + endDay + ", changeDay=" + changeDay + "]";
	}
	public Issue(int issueid, int pid, String uid, String response, String issuetype, String issuestate,
			String issuetitle, String issuecontent, int prework, String priority) {
		super();
		this.issueid = issueid;
		this.pid = pid;
		this.uid = uid;
		this.response = response;
		this.issuetype = issuetype;
		this.issuestate = issuestate;
		this.issuetitle = issuetitle;
		this.issuecontent = issuecontent;
		this.prework = prework;
		this.priority = priority;
		this.makeDay = new Date();
		this.startDay = null;
		this.endDay = null;
		this.changeDay = null;
	}
	public Issue(int issueid, int pid, String uid, String response, String issuetype, String issuestate,
			String issuetitle, String issuecontent, int prework, String priority, Date makeDay, Date startDay,
			Date endDay, Date changeDay) {
		super();
		this.issueid = issueid;
		this.pid = pid;
		this.uid = uid;
		this.response = response;
		this.issuetype = issuetype;
		this.issuestate = issuestate;
		this.issuetitle = issuetitle;
		this.issuecontent = issuecontent;
		this.prework = prework;
		this.priority = priority;
		this.makeDay = new Date();
		this.startDay = startDay;
		this.endDay = endDay;
		this.changeDay = changeDay;
	}
	public int getIssueid() {
		return issueid;
	}
	public void setIssueid(int issueid) {
		this.issueid = issueid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}
	public String getIssuestate() {
		return issuestate;
	}
	public void setIssuestate(String issuestate) {
		this.issuestate = issuestate;
	}
	public String getIssuetitle() {
		return issuetitle;
	}
	public void setIssuetitle(String issuetitle) {
		this.issuetitle = issuetitle;
	}
	public String getIssuecontent() {
		return issuecontent;
	}
	public void setIssuecontent(String issuecontent) {
		this.issuecontent = issuecontent;
	}
	public int getPrework() {
		return prework;
	}
	public void setPrework(int prework) {
		this.prework = prework;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Date getMakeDay() {
		return makeDay;
	}
	public void setMakeDay(Date makeDay) {
		this.makeDay = makeDay;
	}
	public Date getStartDay() {
		return startDay;
	}
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	public Date getChangeDay() {
		return changeDay;
	}
	public void setChangeDay(Date changeDay) {
		this.changeDay = changeDay;
	}

}
