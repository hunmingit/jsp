package org.joonzis.vo;

import java.sql.Date;

public class BoardLikeVO {
	private int bIdx, likeIdx;
	private String pId;
	private Date likeDate;
	
public BoardLikeVO() {
	// TODO Auto-generated constructor stub
}

public BoardLikeVO(int bIdx, int likeIdx, String pId, Date likeDate) {
	super();
	this.bIdx = bIdx;
	this.likeIdx = likeIdx;
	this.pId = pId;
	this.likeDate = likeDate;
}

public int getbIdx() {
	return bIdx;
}

public void setbIdx(int bIdx) {
	this.bIdx = bIdx;
}

public int getLikeIdx() {
	return likeIdx;
}

public void setLikeIdx(int likeIdx) {
	this.likeIdx = likeIdx;
}

public String getpId() {
	return pId;
}

public void setpId(String pId) {
	this.pId = pId;
}

public Date getLikeDate() {
	return likeDate;
}

public void setLikeDate(Date likeDate) {
	this.likeDate = likeDate;
}





}