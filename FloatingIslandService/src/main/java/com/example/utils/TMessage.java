package com.example.utils;

public class TMessage<T> {

	private int code;
	private String message;
	private String picDomain;
	public String getPicDomain() {
		return picDomain;
	}
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setPicDomain(String picDomain2) {
		this.picDomain=picDomain2;
	}
}
