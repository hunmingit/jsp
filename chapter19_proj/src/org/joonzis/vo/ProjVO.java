package org.joonzis.vo;

import java.sql.Date;

public class ProjVO {
	private int p_idx;
	private String pId, pPw;
	private Date pRegDate;
	
	public ProjVO() {}

	public ProjVO(int p_idx, String pId, String pPw, Date pRegDate) {
		super();
		this.p_idx = p_idx;
		this.pId = pId;
		this.pPw = pPw;
		this.pRegDate = pRegDate;
	}

	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
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

	public Date getpRegDate() {
		return pRegDate;
	}

	public void setpRegDate(Date pRegDate) {
		this.pRegDate = pRegDate;
	}
	
	

}