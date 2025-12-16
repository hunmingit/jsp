package org.joonzis.vo;

import java.sql.Date;

public class ProjVO {
	private int pIdx;
	private String pId, pPw;
	private String role;       // USER / ADMIN
	private Date pRegDate;
		
	public ProjVO() {}

	public ProjVO(int pIdx, String pId, String pPw, String role, Date pRegDate) {
		super();
		this.pIdx = pIdx;
		this.pId = pId;
		this.pPw = pPw;
		this.role = role;
		this.pRegDate = pRegDate;
	}

	public int getpIdx() {
		return pIdx;
	}

	public void setpIdx(int pIdx) {
		this.pIdx = pIdx;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getpPw() {
		return pPw;
	}

	public void setpPw(String pPw) {
		this.pPw = pPw;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getpRegDate() {
		return pRegDate;
	}

	public void setpRegDate(Date pRegDate) {
		this.pRegDate = pRegDate;
	}

	
	

}