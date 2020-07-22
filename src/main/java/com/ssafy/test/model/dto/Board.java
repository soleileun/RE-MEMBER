package com.ssafy.test.model.dto;

import java.util.Date;

public class Board {

	private int bno; // update no
	private String bwriter; // update no
	private String btitle;
	private String bcontent;
	private int bview; // update no
	private String bfile;
	private String bstate;

	private Date makeDay; // update no
	private Date changeDay;
	private String makeId; // update no
	private String changeId;

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public int getBview() {
		return bview;
	}

	public void setBview(int bview) {
		this.bview = bview;
	}

	public String getBfile() {
		return bfile;
	}

	public void setBfile(String bfile) {
		this.bfile = bfile;
	}

	public String getBstate() {
		return bstate;
	}

	public void setBstate(String bstate) {
		this.bstate = bstate;
	}

	public Date getMakeDay() {
		return makeDay;
	}

	public void setMakeDay(Date makeDay) {
		this.makeDay = makeDay;
	}

	public Date getChangeDay() {
		return changeDay;
	}

	public void setChangeDay(Date changeDay) {
		this.changeDay = changeDay;
	}

	public String getMakeId() {
		return makeId;
	}

	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}

	public String getChangeId() {
		return changeId;
	}

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	public Board(int bno, String bwriter, String btitle, String bcontent, int bview, String bfile, String bstate,
			Date makeDay, Date changeDay, String makeId, String changeId) {
		super();
		this.bno = bno;
		this.bwriter = bwriter;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bview = bview;
		this.bfile = bfile;
		this.bstate = bstate;
		this.makeDay = makeDay;
		this.changeDay = changeDay;
		this.makeId = makeId;
		this.changeId = changeId;
	}

	public Board() {
		super();
	}

}
