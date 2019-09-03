package com.my.dto;

public class MyLoginDto {
	
	private int myno;
	private String myid;
	private String mypw;
	private String myname;
	
	public MyLoginDto() {

	}

	public MyLoginDto(int myno, String myid, String mypw, String myname) {
		
		this.myno = myno;
		this.myid = myid;
		this.mypw = mypw;
		this.myname = myname;
	}

	public int getMyno() {
		return myno;
	}

	public void setMyno(int myno) {
		this.myno = myno;
	}

	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	public String getMypw() {
		return mypw;
	}

	public void setMypw(String mypw) {
		this.mypw = mypw;
	}

	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}
	
}
