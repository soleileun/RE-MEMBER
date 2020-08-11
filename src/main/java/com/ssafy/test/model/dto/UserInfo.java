package com.ssafy.test.model.dto;

import java.util.Date;


public class UserInfo {
	
	private String id;
	private String nickname;
	private String name;
	private String pw;
	private String address1;
	private String address2;
	private String phone;
	private String git;
	private int points;
	private Date lastDate;
	private boolean state;
	private String responsibility;
	private boolean isValid;
	private boolean leaveUser;
	private String intro;
	
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGit() {
		return git;
	}
	public void setGit(String git) {
		this.git = git;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastdate(Date lastDate) {
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

	
	public UserInfo(String id, String nickname, String name, String pw, String address1, String address2, String phone,
			String git, int points, Date lastDate, boolean state, String responsibility, boolean isValid,
			boolean leaveUser, String intro) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.pw = pw;
		this.address1 = address1;
		this.address2 = address2;
		this.phone = phone;
		this.git = git;
		this.points = points;
		this.lastDate = lastDate;
		this.state = state;
		this.responsibility = responsibility;
		this.isValid = isValid;
		this.leaveUser = leaveUser;
		this.intro = intro;
	}
	public UserInfo() {
	}
	public UserInfo(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", nickname=" + nickname + ", name=" + name + ", pw=" + pw + ", address1="
				+ address1 + ", address2=" + address2 + ", phone=" + phone + ", git=" + git + ", points=" + points
				+ ", lastDate=" + lastDate + ", state=" + state + ", responsibility=" + responsibility + "]";
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public boolean isLeaveUser() {
		return leaveUser;
	}
	public void setLeaveUser(boolean leaveUser) {
		this.leaveUser = leaveUser;
	}


	
	
}
