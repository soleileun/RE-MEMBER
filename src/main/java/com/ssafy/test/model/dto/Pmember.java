package com.ssafy.test.model.dto;

public class Pmember {
	private int pid;
	private String userId;
	private String state;
	private int priority;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Pmember(int pid, String userId, String state, int priority) {
		super();
		this.pid = pid;
		this.userId = userId;
		this.state = state;
		this.priority = priority;
	}

	public Pmember() {
		super();
	}

}
