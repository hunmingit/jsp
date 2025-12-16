package org.joonzis.vo;

import java.sql.Date;

public class BoardVO {
	private int bIdx, views, likes;
	private String writer, title, content, category, isNotice;
	private Date regDate;
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}

	public BoardVO(int bIdx, int views, int likes, String writer, String title, String content, String category,
			String isNotice, Date regDate) {
		super();
		this.bIdx = bIdx;
		this.views = views;
		this.likes = likes;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.category = category;
		this.isNotice = isNotice;
		this.regDate = regDate;
	}

	public int getbIdx() {
		return bIdx;
	}

	public void setbIdx(int bIdx) {
		this.bIdx = bIdx;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(String isNotice) {
		this.isNotice = isNotice;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	

}





