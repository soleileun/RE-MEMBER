package com.ssafy.test.model.dto;

public class Pinterest {

	private int pid;
	private String interest;
	
	
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Pinterest() {
		super();
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public Pinterest(int pid, String interest) {
		super();
		this.pid = pid;
		this.interest = interest;
	}
	@Override
	public String toString() {
		return "Pinterest [pid=" + pid + ", interest=" + interest + "]";
	}
	
	
	
}
