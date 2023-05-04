package com.enquiry.demo.dto;

import java.util.Map;

public class ResponseData {

	String code;
	Map<String,Object> detail;
	String message;

	public ResponseData(String code, Map<String,Object> detail, String message) {
		this.detail = detail;
		this.code = code;
		this.message = message;
	}

	public ResponseData(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Map<String,Object> getDetail() {
		return detail;
	}

}
