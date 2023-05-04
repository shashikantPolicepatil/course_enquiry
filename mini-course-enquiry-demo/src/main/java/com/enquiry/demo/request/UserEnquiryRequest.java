package com.enquiry.demo.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserEnquiryRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3961338210091191777L;
	private Integer id;
	private String studentName;
	private Integer contactNum;
	private String classMode;
	private Integer enquiryId;
	private Integer courseId;
	private Integer userId;
}
