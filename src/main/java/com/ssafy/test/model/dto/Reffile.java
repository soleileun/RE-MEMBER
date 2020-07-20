package com.ssafy.test.model.dto;

import java.util.Date;

public class Reffile {

	
	private int fno;	//update no
	private int fboardno;	//update no
	private String foname;
	private String fsname;
	private int fsize;
	
	private Date makeday;	//update no
	private Date changeday;
	private String makeid;	//update no
	private String changeid;
	
	
	
	
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
	public int getFsize() {
		return fsize;
	}
	public void setFsize(int fsize) {
		this.fsize = fsize;
	}
	public Date getMakeday() {
		return makeday;
	}
	public void setMakeday(Date makeday) {
		this.makeday = makeday;
	}
	public Date getChangeday() {
		return changeday;
	}
	public void setChangeday(Date changeday) {
		this.changeday = changeday;
	}
	public String getMakeid() {
		return makeid;
	}
	public void setMakeid(String makeid) {
		this.makeid = makeid;
	}
	public String getChangeid() {
		return changeid;
	}
	public void setChangeid(String changeid) {
		this.changeid = changeid;
	}
	@Override
	public String toString() {
		return "Reffile [fno=" + fno + ", fboardno=" + fboardno + ", foname=" + foname + ", fsname=" + fsname
				+ ", fsize=" + fsize + ", makeday=" + makeday + ", changeday=" + changeday + ", makeid=" + makeid
				+ ", changeid=" + changeid + "]";
	}
	public Reffile(int fno, int fboardno, String foname, String fsname, int fsize, Date makeday, Date changeday,
			String makeid, String changeid) {
		super();
		this.fno = fno;
		this.fboardno = fboardno;
		this.foname = foname;
		this.fsname = fsname;
		this.fsize = fsize;
		this.makeday = makeday;
		this.changeday = changeday;
		this.makeid = makeid;
		this.changeid = changeid;
	}
	
}
