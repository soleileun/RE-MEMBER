package com.ssafy.test.model.dto;

import java.util.Date;

public class Reffile {

	private int fno; // update no
	private int fboardno; // update no
	private String foname;
	private String fsname;
	private long fsize;

	private Date makeDay; // update no
	private Date changeDay;
	private String makeId; // update no
	private String changeId;

	public Reffile() {
		super();
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public int getFboardno() {
		return fboardno;
	}

	public void setFboardno(int fboardno) {
		this.fboardno = fboardno;
	}

	public String getFoname() {
		return foname;
	}

	public void setFoname(String foname) {
		this.foname = foname;
	}

	public String getFsname() {
		return fsname;
	}

	public void setFsname(String fsname) {
		this.fsname = fsname;
	}

	public long getFsize() {
		return fsize;
	}

	public void setFsize(long fsize) {
		this.fsize = fsize;
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

	public Reffile(int fno, int fboardno, String foname, String fsname, int fsize, Date makeDay, Date changeDay,
			String makeId, String changeId) {
		super();
		this.fno = fno;
		this.fboardno = fboardno;
		this.foname = foname;
		this.fsname = fsname;
		this.fsize = fsize;
		this.makeDay = makeDay;
		this.changeDay = changeDay;
		this.makeId = makeId;
		this.changeId = changeId;
	}

}
