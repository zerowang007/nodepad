package com.zero.homework.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Nodepad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Timestamp createDate;
	private String  title;
	private String content;
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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
	
	public Nodepad() {
	}
	public Nodepad(Timestamp createDate, String title, String content) {
		this.createDate = createDate;
		this.title = title;
		this.content = content;
	}
	
	
}
