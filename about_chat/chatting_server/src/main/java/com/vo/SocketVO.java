package com.vo;

import java.util.Date;

public class SocketVO {
    // 유저의 이름을 저장하기 위한 변수
	private int chno;
    private String roomName;
	private String id;
	private String nickname;
    private String content;
    // 메시지의 내용을 저장하기 위한 변
	
	public int getChno() {
		return chno;
	}

	public void setChno(int chno) {
		this.chno = chno;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}



	@Override
	public String toString() {
		return "SocketVO [chno=" + chno + ", roomName=" + roomName + ", id=" + id + ", nickname=" + nickname
				+ ", content=" + content + ", makedate=" + makedate + "]";
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getMakedate() {
		return makedate;
	}

	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}
	
	public SocketVO(String roomName, String content, int chno, String nickname, String id, Date makedate) {
		super();
		this.roomName = roomName;
		this.content = content;
		this.chno = chno;
		this.nickname = nickname;
		this.id = id;
		this.makedate = makedate;
	}

	private Date makedate;

	/**
	 * @param userName
	 * @param content
	 */
	public SocketVO(String nickname, String content) {
		super();
		this.nickname = nickname;
		this.content = content;
	}

	/**
	 * 
	 */
	public SocketVO() {
		super();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
}
