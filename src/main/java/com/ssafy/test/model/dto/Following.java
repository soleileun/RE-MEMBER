package com.ssafy.test.model.dto;

public class Following {
	
	private String uid;
	private String target;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Following(String uid, String target) {
		super();
		this.uid = uid;
		this.target = target;
	}
	public Following() {
		
	}

	
	
}
