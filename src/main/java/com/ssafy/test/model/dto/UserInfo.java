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
	private Date lastdate;
	private boolean state;
	private String responsibility;
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
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
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
			String git, int points, Date lastdate, boolean state, String responsibility) {
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
		this.lastdate = lastdate;
		this.state = state;
		this.responsibility = responsibility;
	}
	public UserInfo() {
	}


	
	
}
