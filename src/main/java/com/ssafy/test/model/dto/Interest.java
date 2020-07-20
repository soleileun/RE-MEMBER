package com.ssafy.test.model.dto;

public class Interest {

	private String id;
	private String interest;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Interest(String id, String interest) {
		super();
		this.id = id;
		this.interest = interest;
	}

	public Interest() {
		super();
	}

}
