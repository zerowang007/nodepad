package com.zero.homework.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Nodepad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Date createDate;
	private String  title;
	private String content;
	private Date updateTime;
	private String del;
	public Nodepad() {
	}
	public Nodepad(Date createDate, String title, String content) {
		this.createDate = createDate;
		this.title = title;
		this.content = content;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
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
	

	
}
