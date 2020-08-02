package com.ssafy.test.model.dto;

public class Usertag {
	private String tagvalue;
	private String uid;
	public Usertag() {
		super();
	}
	public Usertag(String tagvalue, String uid) {
		super();
		this.tagvalue = tagvalue;
		this.uid = uid;
	}
	public String getTagvalue() {
		return tagvalue;
	}
	public void setTagvalue(String tagvalue) {
		this.tagvalue = tagvalue;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
