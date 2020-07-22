package com.ssafy.test.model.dto;

public class Preference {

	private int pno;
	private String puser;
	private int pboardno;
	private int like;

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPuser() {
		return puser;
	}

	public void setPuser(String puser) {
		this.puser = puser;
	}

	public int getPboardno() {
		return pboardno;
	}

	public void setPboardno(int pboardno) {
		this.pboardno = pboardno;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public Preference(int pno, String puser, int pboardno, int like) {
		super();
		this.pno = pno;
		this.puser = puser;
		this.pboardno = pboardno;
		this.like = like;
	}

	public Preference() {
		super();
	}

}
