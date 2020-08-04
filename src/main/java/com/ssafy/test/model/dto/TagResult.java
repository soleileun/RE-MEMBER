package com.ssafy.test.model.dto;

public class TagResult {

	private int same;
	private String uid;
	public TagResult() {
		super();
	}
	public TagResult(int same, String uid) {
		super();
		this.same = same;
		this.uid = uid;
	}
	public int getSame() {
		return same;
	}
	public void setSame(int same) {
		this.same = same;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
