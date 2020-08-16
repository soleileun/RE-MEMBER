package com.ssafy.test.model.dto;

import java.util.Date;
import java.util.List;

public class Pools {

	
	private String id;
	private String nickname;
	private String name;
	private String address2;
	private String git;
	private Date lastDate;
	private boolean state;
	private String responsibility;
	private boolean isValid;
	private boolean leaveUser;
	private String intro;
	private String projects;
	private String interests;
	
	
	private List<PidPjt> project;
	private List<Inter> interest;
	private List<String> repository;
	
	public Pools(String id, String nickname, String name, String address2, String git, Date lastDate, boolean state,
			String responsibility, boolean isValid, boolean leaveUser, String intro, String projects, String interests,
			List<PidPjt> project, List<Inter> interest, List<String> repository) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.address2 = address2;
		this.git = git;
		this.lastDate = lastDate;
		this.state = state;
		this.responsibility = responsibility;
		this.isValid = isValid;
		this.leaveUser = leaveUser;
		this.intro = intro;
		this.projects = projects;
		this.interests = interests;
		this.project = project;
		this.interest = interest;
		this.repository = repository;
	}
	public List<String> getRepository() {
		return repository;
	}
	public void setRepository(List<String> repository) {
		this.repository = repository;
	}
	public Pools(String id, String nickname, String name, String address2, String git, Date lastDate, boolean state,
			String responsibility, boolean isValid, boolean leaveUser, String intro, String projects, String interests,
			List<PidPjt> project, List<Inter> interest) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.address2 = address2;
		this.git = git;
		this.lastDate = lastDate;
		this.state = state;
		this.responsibility = responsibility;
		this.isValid = isValid;
		this.leaveUser = leaveUser;
		this.intro = intro;
		this.projects = projects;
		this.interests = interests;
		this.project = project;
		this.interest = interest;
	}
	public String getProjects() {
		return projects;
	}
	public void setProjects(String projects) {
		this.projects = projects;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public Pools() {
		super();
	}
	public Pools(String id, String nickname, String name, String address2, String git, Date lastDate, boolean state,
			String responsibility, boolean isValid, boolean leaveUser, String intro, List<PidPjt> project,
			List<Inter> interest) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.address2 = address2;
		this.git = git;
		this.lastDate = lastDate;
		this.state = state;
		this.responsibility = responsibility;
		this.isValid = isValid;
		this.leaveUser = leaveUser;
		this.intro = intro;
		this.project = project;
		this.interest = interest;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getGit() {
		return git;
	}
	public void setGit(String git) {
		this.git = git;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public boolean isLeaveUser() {
		return leaveUser;
	}
	public void setLeaveUser(boolean leaveUser) {
		this.leaveUser = leaveUser;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public List<PidPjt> getProject() {
		return project;
	}
	public void setProject(List<PidPjt> project) {
		this.project = project;
	}
	public List<Inter> getInterest() {
		return interest;
	}
	public void setInterest(List<Inter> interest) {
		this.interest = interest;
	}
}
