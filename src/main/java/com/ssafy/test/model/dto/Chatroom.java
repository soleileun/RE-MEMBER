package com.ssafy.test.model.dto;

public class Chatroom {
	private String roomName;
	private String uid;
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Chatroom(String roomName, String uid) {
		super();
		this.roomName = roomName;
		this.uid = uid;
	}
	public Chatroom() {
		super();
	}
	
	
}
