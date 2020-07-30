package com.ssafy.test.model.dto;

import java.util.Date;

public class ChatroomChat {
 	private String roomName;
 	private String id;
 	private String nickname;
 	private String content;
 	private Date makedate;
 	private int cnt; // 안 읽은 개수
 	private int membercnt;
	public ChatroomChat() {
		super();
	}
	public ChatroomChat(String roomName, String id, String nickname, String content, Date makedate, int cnt,
			int membercnt) {
		super();
		this.roomName = roomName;
		this.id = id;
		this.nickname = nickname;
		this.content = content;
		this.makedate = makedate;
		this.cnt = cnt;
		this.membercnt = membercnt;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getMakedate() {
		return makedate;
	}
	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getMembercnt() {
		return membercnt;
	}
	public void setMembercnt(int membercnt) {
		this.membercnt = membercnt;
	}
}
