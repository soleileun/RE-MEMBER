package com.ssafy.test.model.dto;

public class PidPjt {
	private int pid;
	private String pjtName;
	private String pjtContent;
	
	public PidPjt(int pid, String pjtName, String pjtContent) {
		super();
		this.pid = pid;
		this.pjtName = pjtName;
		this.pjtContent = pjtContent;
	}
	public String getPjtContent() {
		return pjtContent;
	}
	public void setPjtContent(String pjtContent) {
		this.pjtContent = pjtContent;
	}
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
