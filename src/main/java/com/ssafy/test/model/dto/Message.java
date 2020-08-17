package com.ssafy.test.model.dto;

import java.util.Date;

public class Message {

	private int mnum;
	private String toUser;
	private String fromUser;
	private String content;
	private Date time;
	private boolean isRead;

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
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

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Message(int mnum, String toUser, String fromUser, String content, Date time, boolean isRead) {
		super();
		this.mnum = mnum;
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.content = content;
		this.time = time;
		this.isRead = isRead;
	}

	public Message(String toUser, String fromUser, String content, Date time, boolean isRead) {
		super();
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.content = content;
		this.time = time;
		this.isRead = isRead;
	}

	public Message() {
	}

	@Override
	public String toString() {
		return "Message [mnum=" + mnum + ", toUser=" + toUser + ", fromUser=" + fromUser + ", content=" + content
				+ ", time=" + time + ", isRead=" + isRead + "]";
	}

}
