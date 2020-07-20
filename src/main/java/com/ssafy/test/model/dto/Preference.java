package com.ssafy.test.model.dto;

public class Preference {
	
	private int pno;
	private String pwriter;
	private int pboardno;
	private boolean like;
	private boolean dislike;
	public Preference(int pno, String pwriter, int pboardno, boolean like, boolean dislike) {
		super();
		this.pno = pno;
		this.pwriter = pwriter;
		this.pboardno = pboardno;
		this.like = like;
		this.dislike = dislike;
	}
	public Preference() {
		
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getPwriter() {
		return pwriter;
	}
	public void setPwriter(String pwriter) {
		this.pwriter = pwriter;
	}
	public int getPboardno() {
		return pboardno;
	}
	public void setPboardno(int pboardno) {
		this.pboardno = pboardno;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public boolean isDislike() {
		return dislike;
	}
	public void setDislike(boolean dislike) {
		this.dislike = dislike;
	}

    
}
