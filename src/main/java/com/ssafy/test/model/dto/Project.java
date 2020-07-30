package com.ssafy.test.model.dto;

import java.util.Date;

public class Project {

	private int pid;
	private String pjtName;
	private String pjtContent;
	private String pjtState;
	private int pjtMemberCnt;
	private Date makeDay;
	private Date changeDay;
	private String makeId;
	private String changeId;
	private String location;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPjtName() {
		return pjtName;
	}

	public void setPjtName(String pjtName) {
		this.pjtName = pjtName;
	}

	public String getPjtContent() {
		return pjtContent;
	}

	public void setPjtContent(String pjtContent) {
		this.pjtContent = pjtContent;
	}

	public String getPjtState() {
		return pjtState;
	}

	public void setPjtState(String pjtState) {
		this.pjtState = pjtState;
	}

	public int getPjtMemberCnt() {
		return pjtMemberCnt;
	}

	public void setPjtMemberCnt(int pjtMemberCnt) {
		this.pjtMemberCnt = pjtMemberCnt;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Project(int pid, String pjtName, String pjtContent, String pjtState, int pjtMemberCnt,
			Date makeDay, Date changeDay, String makeId, String changeId, String location) {
		super();
		this.pid = pid;
		this.pjtName = pjtName;
		this.pjtContent = pjtContent;
		this.pjtState = pjtState;
		this.pjtMemberCnt = pjtMemberCnt;
		this.makeDay = makeDay;
		this.changeDay = changeDay;
		this.makeId = makeId;
		this.changeId = changeId;
		this.location = location;
	}

	public Project() {
		super();
	}

}
