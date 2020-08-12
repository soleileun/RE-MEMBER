package com.ssafy.test.model.dto;

import java.util.Date;

public class Projectcnt {
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
	
	private String userId;
	private int cnt;
	private int priority;
	private String state;
	private String tag1;
	private String tag2;
	private String tag3;
	private String tag4;
	private String tag5;
	
	public Projectcnt(int pid, String pjtName, String pjtContent, String pjtState, int pjtMemberCnt, Date makeDay,
			Date changeDay, String makeId, String changeId, String location, String userId, int cnt, int priority,
			String state, String tag1, String tag2, String tag3, String tag4, String tag5) {
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
		this.userId = userId;
		this.cnt = cnt;
		this.priority = priority;
		this.state = state;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
		this.tag4 = tag4;
		this.tag5 = tag5;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Projectcnt(int pid, String pjtName, String pjtContent, String pjtState, int pjtMemberCnt, Date makeDay,
			Date changeDay, String makeId, String changeId, String location, String userId, int cnt, int priority,
			String state) {
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
		this.userId = userId;
		this.cnt = cnt;
		this.priority = priority;
		this.state = state;
	}
	public Projectcnt() {
		super();
	}
	
	
}
