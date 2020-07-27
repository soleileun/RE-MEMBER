package com.ssafy.test.model.dto;

public class Chatroom {
	private String roomName;
	private int uid;
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Chatroom(String roomName, int uid) {
		super();
		this.roomName = roomName;
		this.uid = uid;
	}
	public Chatroom() {
		super();
	}
	
	
}
