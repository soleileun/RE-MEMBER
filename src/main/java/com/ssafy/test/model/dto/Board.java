package com.ssafy.test.model.dto;

import java.util.Date;

public class Board {


	private int bno;	//update no
	private String bwriter;	//update no
	private String btitle;
	private String bcontent;
	private int bview;	//update no
	private String bfile;
	private String bstate;
	
	private Date makeday;	//update no
	private Date changeday;
	private String makeid;	//update no
	private String changeid;
	
	
	
	
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
		return "Board [bno=" + bno + ", bwriter=" + bwriter + ", btitle=" + btitle + ", bcontent=" + bcontent
				+ ", bview=" + bview + ", bfile=" + bfile + ", bstate=" + bstate + ", makeday=" + makeday
				+ ", changeday=" + changeday + ", makeid=" + makeid + ", changeid=" + changeid + "]";
	}
	
	public Board(int bno, String bwriter, String btitle, String bcontent, int bview, String bfile, String bstate,
			Date makeday, Date changeday, String makeid, String changeid) {
		super();
		this.bno = bno;
		this.bwriter = bwriter;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bview = bview;
		this.bfile = bfile;
		this.bstate = bstate;
		this.makeday = makeday;
		this.changeday = changeday;
		this.makeid = makeid;
		this.changeid = changeid;
	}
	public Board() {
		super();
	}
	
	
	
	
	
	
}
