package com.baidu.map.bean;

public class BaseResult {

	private String status;

	public BaseResult() {
	}

	public BaseResult(String status) {
		this.status = status;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
