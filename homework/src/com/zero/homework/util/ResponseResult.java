package com.zero.homework.util;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;




public class ResponseResult {
	private static final long serialVersionUID = 1L;
	/* 是否成功 */
	private boolean success = true;

	/* 返回码 */
	private String resultCode = "0";

	/* 返回信息描述 */
	private String message = "Success";

	private List obj;
	private Object object;

	/**
	 * 构造方法
	 */

	public ResponseResult() {
	}


	public ResponseResult(List obj) {
		this.obj = obj;
	}
	

	public ResponseResult(boolean success, String resultCode, String message,
			List obj) {
		super();
		this.success = success;
		this.resultCode = resultCode;
		this.message = message;
		this.obj = obj;
	}
	

	public ResponseResult(Object object) {
		this.object = object;
	}
	

	public ResponseResult(boolean success, String resultCode, String message,
			Object object) {
		this.success = success;
		this.resultCode = resultCode;
		this.message = message;
		this.object = object;
	}



	/**
	 * Getter method for property <tt>success</tt>.
	 * 
	 * @return property value of success
	 */
	public boolean isSuccess() {
		return success;
	}



	/**
	 * Setter method for property <tt>success</tt>.
	 * 
	 * @param success
	 *            value to be assigned to property success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List getObj() {
		return obj;
	}

	public void setObj(List obj) {
		this.obj = obj;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
