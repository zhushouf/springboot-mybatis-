package com.example.springboot.vo;

public class ResultVO {

	private String message;
	
	private String code;
	
	private Object data;

	public ResultVO(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}

	public ResultVO(String message, String code, Object data) {
		super();
		this.message = message;
		this.code = code;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
