package com.ssafy.test.model.dto;

public class Email {
	private String userEmail;
	private String validKey;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getKey() {
		return validKey;
	}
	public void setKey(String key) {
		this.validKey = key;
	}
	public Email(String userEmail, String key) {
		super();
		this.userEmail = userEmail;
		this.validKey = key;
	}
	public Email() {
	}

	
}
