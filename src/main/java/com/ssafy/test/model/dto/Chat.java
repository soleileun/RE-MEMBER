package com.ssafy.test.model.dto;

import java.util.Date;

public class Chat {

 	private int chno;
 	private int pid;
 	private String id;
 	private String nickname;
 	private String content;
 	private Date makedate;
	public Chat(int chno, int pid, String id, String nickname, String content, Date makedate) {
		super();
		this.chno = chno;
		this.pid = pid;
		this.id = id;
		this.nickname = nickname;
		this.content = content;
		this.makedate = makedate;
	}
	public int getChno() {
		return chno;
	}
	public void setChno(int chno) {
		this.chno = chno;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
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
	public Chat(int chno, int pid, String id, String nickname, String content) {
		super();
		this.chno = chno;
		this.pid = pid;
		this.id = id;
		this.nickname = nickname;
		this.content = content;
		this.makedate = new Date();
	}
	public Chat() {
		super();
	}
}
