package com.ssafy.test.model.dto;

public class WaitMember {
	private int wno;
	private int pid;
	private String userId;
	private String comment;
	private String type;
	private String code;
	private String state;
	
	public WaitMember(int wno, int pid, String userId, String comment, String type, String code, String state) {
		super();
		this.wno = wno;
		this.pid = pid;
		this.userId = userId;
		this.comment = comment;
		this.type = type;
		this.code = code;
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public WaitMember(int wno, int pid, String userId, String comment, String type, String code) {
		super();
		this.wno = wno;
		this.pid = pid;
		this.userId = userId;
		this.comment = comment;
		this.type = type;
		this.code = code;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getWno() {
		return wno;
	}
	public void setWno(int wno) {
		this.wno = wno;
	}
	public WaitMember(int wno, int pid, String userId, String comment, String type) {
		super();
		this.wno = wno;
		this.pid = pid;
		this.userId = userId;
		this.comment = comment;
		this.type = type;
	}
	public WaitMember() {
		super();
	}

}
