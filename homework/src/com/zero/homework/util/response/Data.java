package com.zero.homework.util.response;

import java.io.Serializable;
import java.util.List;

/**
 * Common class for storing paging parameter and response paging info
 
 */
public class Data implements Serializable {

	private static final long serialVersionUID = 1L;

	private int recordCount;
	private boolean isLastPage;
	
	private int totalPage;
	private int currentPage;
	private int pageSize;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private List<?> list;

	public Data(){
	}

	public Data(List<?> list){
		this.list = list;
	}

	public Data(int recordCount, boolean isLastPage, List<?> list){
		this.recordCount = recordCount;
		this.isLastPage = isLastPage;
		this.list = list;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}
