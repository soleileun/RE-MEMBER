package com.ssafy.test.model.dto;

import java.util.Date;

public class Message {
	
	private int mnum;
	private String to_user;
	private String from_user;
	private String content;
	private Date time;
	private boolean is_read;
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getTo_user() {
		return to_user;
	}
	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}
	public String getFrom_user() {
		return from_user;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public boolean isIs_read() {
		return is_read;
	}
	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}
	public Message(int mnum, String to_user, String from_user, String content, Date time, boolean is_read) {
		super();
		this.mnum = mnum;
		this.to_user = to_user;
		this.from_user = from_user;
		this.content = content;
		this.time = time;
		this.is_read = is_read;
	}
	public Message() {
	}

	
	

	  
}
