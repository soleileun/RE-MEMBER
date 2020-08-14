package com.ssafy.test.model.dto;

public class PidPjt {
	private int pid;
	private String pjtName;
	public PidPjt() {
		super();
	}
	public PidPjt(int pid, String pjtName) {
		super();
		this.pid = pid;
		this.pjtName = pjtName;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPjtName() {
		return pjtName;
	}
	public void setPjtName(String pjtName) {
		this.pjtName = pjtName;
	}
}
