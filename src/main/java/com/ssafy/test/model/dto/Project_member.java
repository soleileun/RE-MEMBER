package com.ssafy.test.model.dto;

public class Project_member {
	private int pid;
	private String user_id;
	private String state;
	private int priority;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public Project_member(int pid, String user_id, String state, int priority) {
		super();
		this.pid = pid;
		this.user_id = user_id;
		this.state = state;
		this.priority = priority;
	}

	public Project_member() {
		super();
	}

}
