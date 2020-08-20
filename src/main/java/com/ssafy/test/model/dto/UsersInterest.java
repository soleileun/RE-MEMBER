package com.ssafy.test.model.dto;

public class UsersInterest {
	
	private String id;
	private Integer cnt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public UsersInterest(String id, Integer cnt) {
		super();
		this.id = id;
		this.cnt = cnt;
	}
	public UsersInterest() {
		super();
	}
	

}
