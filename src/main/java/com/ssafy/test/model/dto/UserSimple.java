package com.ssafy.test.model.dto;

import java.util.Date;

public class UserSimple {
	
	
	private String id;
	private String nickname;
	private String git;
	private Date lastDate;
	private boolean state;
	private String responsibility;
	private boolean leaveUser;
	public UserSimple(String id, String nickname, String git, Date lastDate, boolean state, String responsibility,
			boolean leaveUser) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.git = git;
		this.lastDate = lastDate;
		this.state = state;
		this.responsibility = responsibility;
		this.leaveUser = leaveUser;
	}
	public UserSimple() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGit() {
		return git;
	}
	public void setGit(String git) {
		this.git = git;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public boolean isLeaveUser() {
		return leaveUser;
	}
	public void setLeaveUser(boolean leaveUser) {
		this.leaveUser = leaveUser;
	}
	

	
}
